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
      w_min = Array.new([@nWeapons, wl.length]).min
      s_min = Array.new([@nShields, sl.length]).min
      
      if @weapons != nil
        aux = Array.new
        w_aux = Array.new(@weapons)
        
        for i in 0...wl.length
          if w_aux.include?(wl[i].type)
            aux.push(wl[i].type)
            w_aux.delete(wl[i].type)
          end
        end
        
        return Damage.newSpecificWeapons(aux, s_min)
      end
      
      Damage.newNumericWeapons(w_min, s_min)
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