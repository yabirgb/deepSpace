require_relative 'SpaceStationToUI'
require_relative 'ShieldBooster'
require_relative 'Hangar'
require_relative 'CardDealer'
require_relative 'Transformation'
    
#When a attribute doesn't exists we get nil trying to get.
#On set it works as expected

module Deepspace
  class SpaceStation
    @@MAXFUEL = 100
    @@SHIELDLOSSPERUNITSHOT = 0.1
     
    attr_reader :hangar
    attr_reader :ammoPower
    attr_reader :fuelUnits
    attr_reader :name
    attr_reader :nMedals
    attr_reader :pendingDamage
    attr_reader :shieldBoosters
    attr_reader :weapons
    attr_reader :shieldPower     
    attr_reader :supplies
    
    attr_writer :pendingDamage
    attr_writer :loot
    
    def initialize(n, supplies)
      @name = n
      @supplies = supplies
      @nMedals = 0
      @weapons = Array.new
      @fuelUnits = 0
      @shieldBoosters = Array.new
      @ammoPower = supplies.ammoPower
      @shieldPower = supplies.shieldPower
      assignFuelValue(supplies.fuelUnits)
    end
    
    def self.newCopy(station)
      #Crear un supplies package
      SpaceStation.new(station.name, station.supplies)
    end
    
    def assignFuelValue(f)
      if f == nil
        @fuelUnits = 0
      else
        if f < @@MAXFUEL
          @fuelUnits = f
        else
          @fuelUnits == @@MAXFUEL
        end
      end
    end
    
    def cleanPendingDamage
      #We have to check if exits because if it's nil we are doing nil.hasNoEffect
      @pendingDamage = nil if @pendingDamage != nil && @pendingDamage.hasNoEffect
    end

    # =================
    # Discard things
    # =================
    
    def discardShieldBooster(i)
      size = @shieldBoosters.length
      
      if i >= 0 && i < size
        @shieldBoosters.delete_at(i)
        if @pendingDamage != nil
          @pendingDamage.discardShieldBooster
          cleanPendingDamage
        end
      end
    end
        
    def discardShieldBoosterInHangar(i)
      @hangar.removeShieldBooster(i) if hangar != nil
    end

    def discardWeapon(i)
      size = @weapons.length
      if i >= 0 && i < size
        w = @weapons.delete_at(i)
        if @pendingDamage != nil
          @pendingDamage.discardWeapon(w)
          cleanPendingDamage
        end
      end
    end
    
    def discardWeaponInHangar(i)
      @hangar.removeWeapon(i) if hangar != nil
    end

    # =================
    # Receive things
    # =================

    def fire
      factor = 1
      @weapons.each{|x|
        factor *= x.useIt
      }
      
      factor*@ammoPower
    end

    def mountShieldBooster(i)
      if @hangar != nil && i >= 0 && i < @hangar.shieldBoosters.length
        @shieldBoosters.push(ShieldBooster.newCopy(@hangar.removeShieldBooster(i)))  
      end
    end
    
    def mountWeapon(i)
      if @hangar != nil && i >= 0 && i < @hangar.weapons.length
        @weapons.push(Weapon.newCopy(@hangar.removeWeapon(i)))
      end
    end
    
    def receiveWeapon(w)
      if @hangar != nil
        @hangar.addWeapon(w)
      else
        false
      end
    end
    
    def receiveShieldBooster(s)
      if @hangar != nil
        @hangar.addShieldBooster(s)
      else
        false
      end
    end
    
    def receiveHangar(h)
      if @hangar == nil
        @hangar = Hangar.newCopy(h)
      end
    end
    
    def discardHangar
      @hangar = nil
    end
    
    def move
      if @fuelUnits - speed > 0
        @fuelUnits -= speed
      end
    end
    
    def speed
      @fuelUnits*1.0/@@MAXFUEL
    end
    
    def receiveSupplies(s)
      @fuelUnits += s.fuelUnits
      if @fuelUnits > @@MAXFUEL
        @fuelUnits = @@MAXFUEL
      end
      @shieldPower += s.shieldPower
      @ammoPower += s.ammoPower
    end
    
    def validState
      @pendingDamage == nil || @pendingDamage.hasNoEffect
    end
    
    def cleanUpMountedItems
      @shieldBoosters.delete_if{|x| x.uses == 0}
      @weapons.delete_if{|x| x.uses == 0}
    end
    
    def protection
      factor = 1
      @shieldBoosters.each { |x|
        factor *= x.useIt()
      }
      
      @shieldPower *factor
    end
    
    def receiveShot(shot)
      myProtection = protection
      
      if myProtection >= shot
        @shieldPower -= @@SHIELDLOSSPERUNITSHOT*shot
        @shieldPower = [0, @shieldPower].max
        return ShotResult::RESIST
      else
        shieldPower = 0
        return ShotResult::DONOTRESIST
      end 
    end
    
    def setLoot(loot)
      dealer = CardDealer.instance
      h = loot.nHangars
      
      if h > 0
        @hangar = dealer.nextHangar
        receiveHangar(dealer.nextHangar)
      end
      
      (0...loot.nSupplies).each { |s|
        receiveSupplies(dealer.nextSuppliesPackage)
      }
      
      (0...loot.nWeapons).each { |s|
        receiveWeapon(dealer.nextWeapon)
      }
      
      (0...loot.nShields).each { |s|
        receiveShieldBooster(dealer.nextShieldBooster)
      }
      
      @nMedals += loot.nMedals
      
      if loot.efficient
        Transoformation::GETEFFICIENT
      elsif loot.spaceCity  
        Transoformation::SPACECITY
      else  
        Transformation::NOTRANSFORM
      end
      
    end
         
    def setPendingDamage(d)
      @pendingDamage = d.adjust(@weapons,@shieldBoosters)
    end
    
    def getUIversion
      SpaceStationToUI.new(self)
    end
    
    def to_s
      out="Space Station:\n-Name: #{@name}"
      out+="\n-Medals: #{@nMedals}, Fuel units: #{@fuelUnits.round(2)}, Power: #{@ammoPower}, Shields: #{@shieldPower}"
      out+="\n-Weapons: [#{@weapons.join(', ')}]\n"
      out+="\n-ShieldBooster: [#{@shieldBoosters.join(', ')}]\n"
      out+="\n-Hangars: #{@hangar}\n"
      out+="\n-PendingDamage: #{@pendingDamage}\n" 
      out+="------- end of Space Station >> #{@name} << -------"
      return out
    end
  
    private :assignFuelValue, :cleanPendingDamage
  end
end
