require_relative 'Damage'
require_relative 'Hangar'
require_relative 'SuppliesPackage'

    
#When a attribute doesn't exists we get nil trying to get.
#On set it works as expected

module DeepSpace
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
    
      
  end
end
