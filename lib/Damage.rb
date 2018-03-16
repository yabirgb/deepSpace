require_relative 'DamageToUI'
require_relative 'WeaponType'

module Deepspace
  class Damage

    attr_reader :nShields
    attr_reader :nWeapons
    attr_reader :weapons

    def initialize(nweapons, nshields, wl)
      @nShields = nshields
      @nWeapons = nweapons
      @weapons = wl
    end

    def self.newNumericWeapons(w, s)
      new(w, s, nil)
    end
    
    def self.newSpecificWeapons(wl, s)
      new(nil, s, wl)
    end

    private
    
    def arrayContainsType(w, t)
      pos = w.index{|x| x.weapon.getType() == t}
      if pos == nil
        -1
      end
    end

    public
    
    
    def adjust(wl, sl)
      #Comrprobamos si es de tipo numerico
      
      if wl == nil
        Damage.newNumericWeapons(Array.new([wl.length, @nWeapons].min), Array.new([sl.length, @nShields].min))
      else  
        if weapons != nil
          copy = Array.new(wl)
          @weapons.each{|x|
            copy.delete_at(copy.find_index(x))
          }

          Damage.newSpecificWeapons(copy, @nShields)
        else
          Damage.newSpecificWeapons(Array.new, @nshields)
        end
        
      end
      
    end

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
      if @nShields == 0 && (@nWeapons == 0 || @weapons.length == 0)
        true
      else
        false
      end 
    end

    def getUIversion
      DamageToUI.new(self)
    end
    
    def to_s
      "Weapons: #{@nWeapons}; Shields: #{@nShields}; Keep: #{@weapons}"
    end

  end
end