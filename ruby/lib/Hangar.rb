require_relative 'HangarToUI'

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
      Hangar.new(h.maxElements)
    end
    
    def spaceAvailable
        @maxElements - @weapons.length - @shieldBoosters.length
    end

    def addWeapon(w)
      if spaceAvailable > 0
        @weapons.push(w)
        true
      else  
        false
      end
    end

    def addShieldBooster(s)
      if spaceAvailable > 0
        @shieldBoosters.push(s)
        true
      else  
        false
      end
    end
    
    def removeShieldBooster(s)
      @shieldBoosters.delete_at(s)
    end
    
    def removeWeapon(w)
      @shieldBoosters.delete_at(w)
    end
    
    def getUIversion
      HangarToUI.new(self)
    end
    
    private :spaceAvailable

  end
end