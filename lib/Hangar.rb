module Deepspace
  class Hangar

    attr_reader maxElements
    
    public
    
    def initialize(capacity)
      @maxElements = capacity
      @weapons = Array.new()
      @shields = Array.new()
    end

    def newCopy(h)
      Hangar.new(h.maxElements)
    end
    
    def spaceAvailable
      return @maxElements - @weapons.length - @shields.length
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
        @weapons.push(s)
        true
      else  
        false
      end
    end

    def maxElements
      @maxElements
    end

    def shieldBoosters
      @shields
    end

    def weapons
      @weapons
    end
    
    def removeShieldBooster(s)
      @shields.delete_at(s)
    end
    
    def removeWeapon(w)
      @shields.delete_at(w)
    end
    
    private :spaceAvailable

  end
end