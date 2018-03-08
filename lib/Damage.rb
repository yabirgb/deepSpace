require_relative 'DamageToUI'
require_relative 'WeaponType'

module Deepspace
  class Damage

    attr_getter :nShields
    attr_getter :nWeapons
    attr_getter :weapons

    def initialize(nshields, nweapons)
      if
        @nShields = nshields
        @nWeapons = nweapons
      end
    end

    def newCopy(wl, s)
      @weapons = wl
      @nshields = s
    end

    private

    def adjust(w, s)
      #Toma como argumento dos arrays
      
    end
    
    def arrayContainsType(w, t)
      pos = w.detect{|x| x.weapon.getType() == t}
      if pos == nil
        -1
      end
    end

    public

    def discardWeapon(w)
      #Si borramos pero no está nos devuelve nil
      if defined? @weapons then
        @weapons.delete(w)
      elsif @nWeapons > 0
        @nWeapons -= 1
      end 
    end
    
    def discardShieldBooster
      @nShields -= 1 if @nShields > 0
    end
    
    def hasNoEffect
      if @nShields == 0 && @nWeapons == 0
        true
      else
        false
      end 
    end

    def UIVersion
      DamageToUI(self)
    end
    
    def to_s
      "Weapons: #{@nWeapons}; Shields: #{@nShields}; Keep: #{@weapons}"
    end

  end
end