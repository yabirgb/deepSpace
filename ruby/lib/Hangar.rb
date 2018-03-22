require_relative 'HangarToUI'
require_relative 'Weapon'
require_relative 'ShieldBooster'

module Deepspace
  class Hangar

    attr_reader :maxElements
    attr_reader :weapons
    attr_reader :shieldBoosters
      
    public
    
    def initialize(capacity)
      @maxElements = capacity
      @weapons = Array.new()
      @shieldBoosters = Array.new()
    end

    def newCopy(h)
      h = Hangar.new(h.maxElements)
      weapons.each{|w|
        h.addWeapon(Weapon.newCopy(w))
      }
      
      shieldBoosters.each{|s|
        h.addShieldBooster(ShieldBooster.newCopy(s))
      }
      
      h  
    end
    
    def spaceAvailable
        @maxElements - @weapons.length - @shieldBoosters.length > 0
    end

    def addWeapon(w)
      if spaceAvailable
        @weapons.push(w)
        true
      else  
        false
      end
    end

    def addShieldBooster(s)
      if spaceAvailable
        @shieldBoosters.push(s)
        true
      else  
        false
      end
    end
    
    def removeShieldBooster(s)
      if s >= 0 && s < @shieldBoosters.length
        @shieldBoosters.delete_at(s)
      end
    end
    
    def removeWeapon(w)
      if w >= 0 && w < @weapons.length
        @weapons.delete_at(w)
      end
    end
    
    def getUIversion
      HangarToUI.new(self)
    end
    
    private :spaceAvailable

  end
end