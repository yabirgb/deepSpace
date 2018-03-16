require_relative 'Damage'
require_relative 'Hangar'
require_relative 'SuppliesPackage'
require_relative 'ShotResult'
require_relative 'CardDealer'
require_relative 'Loot'
require_relative 'Weapon'

    
#When a attribute doesn't exists we get nil trying to get.
#On set it works as expected

module Deepspace
  class SpaceStation
    
    attr_reader :hangar
    attr_reader :ammoPower
    attr_reader :fuelUnits
    attr_reader :name
    attr_reader :nMedals
    attr_reader :pendingDamage
    attr_reader :shieldBoosters
    attr_reader :weapons
    attr_reader :shieldPower 
    attr_reader :speed
    attr_reader :UIVersion
    

    attr_writer :pendingDamage
    attr_writer :loot
    
    def initialize(n, supplies)
      @MAXFUEL = 100
      @SHIELDLOSSPERUNITSHOT = 0.1
      @name = n
      @supplies = supplies
      @nMedals = 0
    end
    
    private
    def assignFuelValue(f)
      @fuelUnits = f
    end
    
    def cleanPendingDamage
      #We have to check if exits because if it's nil we are doing nil.hasNoEffect
      if pendingDamage != nil 
        @pendingDamage = nil if @pendingDamage.hasNoEffect
      end
    end
    
    public
    
    def discardShieldBoosterInHangar(i)
      @hangar.removeShieldBooster(i) if hangar != nil
    end
    
    def discardWeaponInHangar(i)
      @hangar.removeWeapon(i) if hangar != nil
    end
    
    def receiveWeapon(w)
      if hangar != nil
        @hangar.addWeapon(w)
      else
        false
      end
    end
    
    def receiveShieldBooster(s)
      if hangar != nil
        @hangar.addShieldBooster(s)
      else
        false
      end
    end
    
    def receiveHangar(h)
      if hangar == nil
        @hangar = h
      end
    end
    
    def discardHangar
      @hangar = nil
    end
    
    def move
      if fuelUnits - speed > 0
        fuelUnits -= speed
      end
    end
    
    def receiveSupplies(s)
      if @fuelUnits != nil
        @fuelUnits += s.fuelUnits
      end
    end
    
    def mountShieldBooster(i)
      if @hangar != nil
        @hangar.addShieldBooster(shieldBoosters[i])
        @shieldBoosters.delete_at(i)
      end
    end
    
    def mountWeapon(i)
      if @hangar != nil
        @hangar.addWeapon(weapons[i])
        @weapons.delete_at(i)
      end
    end
    
    def validState
      if pendingDamage == nil || pendingDamage.hasNoEffect
        true
      else  
        false
      end
    end
    
    def receiveSupplies(s)
      @ammoPower += s.ammoPower
      @fuelUnits += s.fuelUnits
      @shieldPower += s.shieldPower
    end
    
    def cleanUpMountedItems
      @shieldBoosters.delete_if{|x| x.uses == 0}
      @weapons.delete_if{|x| x.uses == 0}
    end
    
    def fire
      factor = 1
      @weapons.each{|x|
        factor *= x.useIt
      }
      
      factor*@ammoPower
    end
    
    def protection
      factor = 1
      @shieldsBoosters.each { |x|
        factor *= x.useIt()
      }
      
      @shieldPower *factor
    end
    
    def receiveShoot(shot)
      myProtection = protection
      
      if myProtection >= shot
        @shieldPower -= @SHIELDLOSSPERUNITSHOT*shot
        @shieldPower = [0, shieldPower].max
        ShotResult::RESIST
      else
        shieldPower = 0
        ShotResult::DONOTRESIST
      end 
    end
    
    def setLoot(loot)
      dealer = CardDealer.instance
      h = loot.nHangars
      
      if h > 0
        hangar = dealer.nextHangar
        receiveHangar(dealer.nextHangar)
      end
      
      (1..loot.nSupplies).each { |s|
        receiveSupplies(dealer.nextSuppliesPackage)
      }
      
      (1..loot.nSupplies).each { |s|
        receiveWeapon(dealer.nextWeapon)
      }
      
      (1..loot.nShields).each { |s|
        receiveShieldBooster(dealer.nextShieldBooster)
      }
      
      @nMedals += loot.nMedals
      
    end
    
    def discardWeapon(i)
      size = weapons.length
      
      if i > 0 && i < size
        w = @weapons.delete_at(i)
        
        if pendingDamage != nil
          @pendingDamage.discardWeapon(w)
          cleanPendingDamage
        end
        
      end
      
    end
    
    def discardShieldBooster(i)
      size = shieldBoosters.length
      
      if i > 0 && i < size
        @shieldBoosters.delete_at(i)
        
        if pendingDamage != nil
          @pendingDamage.discardShieldBooster
          cleanPendingDamage
        end
        
      end
      
    end
    
    def setPendingDamage(d)
      @pendingDamage = d.adjust(@weapons,@shieldsBoosters)
    end
    
  end
end
