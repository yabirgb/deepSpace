module Deepspace
  class Hangar

    attr_reader maxElements

    def initialize(capacity)
      @maxElements = capacity
      @weapons = Array.new()
      @shields = Array.new()
    end

    def newCopy(h)
      Hangar.new(h.maxElements)
    end

    def addWeapon(w)
      if @elements < getMaxElements()
        @weapons.push(w)
        @elements += 1
        true
      else  
        false
      end
    end

    def addShieldBooster(s)
      if @elements < getMaxElements()
        @weapons.push(s)
        @elements += 1
        true
      else  
        false
      end
    end

    def getMaxElements
      @maxElements
    end

    def getShieldBoosters
      @shields
    end

    def getWeapons
      @weapons
    end

  end
end