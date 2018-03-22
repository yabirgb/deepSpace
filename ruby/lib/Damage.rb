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
      w_min = Array.new([nWeapons, wl.length].delete_if{|x| x == nil}).min
      s_min = [nShields, sl.length].min
      
      if weapons != nil
        aux = Array.new
        w_aux = Array.new(@weapons)
        
        wl.each{|w|
          if w_aux.include?(w.type)
            aux.push(w_aux.delete_at(w_aux.index(w.type)))
          end
        }
        
        return Damage.newSpecificWeapons(aux, s_min)
      end
      
      Damage.newNumericWeapons(w_min, s_min)
    end


    def discardWeapon(w)
      #Si borramos pero no está nos devuelve nil
      if weapons != nil
        @weapons.delete(w)
      elsif nWeapons > 0
        @nWeapons -= 1
      end 
    end
    
    def discardShieldBooster
      @nShields -= 1 if nShields > 0
    end
    
    def hasNoEffect
      if weapons != nil
        nShields == 0 && weapons.length == 0
      else
        nShields && nWeapons == 0
      end
    end

    def getUIversion
      DamageToUI.new(self)
    end
    
    def to_s
      "nWeapons: #{nWeapons}; Shields: #{nShields}; Weapons #{weapons}"
    end
    
    private_class_method :new

  end
end