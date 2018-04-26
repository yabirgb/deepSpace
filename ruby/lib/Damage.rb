# coding: utf-8
require_relative 'DamageToUI'
require_relative 'WeaponType'

module Deepspace
  class Damage

    attr_reader :nShields # Número de escudos a descartar
    attr_reader :nWeapons #Número de armas a descartar
    attr_reader :weapons #Vector de weaponType

    def initialize(nweapons, nshields, wl)
      @nShields = nshields
      @nWeapons = nweapons
      if wl != nil
        @weapons = Array.new(wl)
      else
        @weapons = nil
      end
      
    end

    def self.newNumericWeapons(w, s)
      new(w, s, [])
    end
    
    def self.newSpecificWeapons(wl, s)
      new(-1, s, wl)
    end

    def getUIversion
      DamageToUI.new(self)
    end

        
    def adjust(wl, sl)
      #wl tiene las armas de la nave
      #sl tiene los escudos de la nave
      if nWeapons == -1
        
        result = Array.new
        toRemove = Array.new(weapons)
        
        wl.each{|weapon|
          if toRemove.include?(weapon.type)
            result.push(weapon.type)
            toRemove.delete_at(toRemove.index(weapon.type))
          end
        }
        
        Damage.newSpecificWeapons(result, [nShields,sl.length].min) 
      else
        Damage.newNumericWeapons([nWeapons,wl.length].min, [nShields,sl.length].min)
      end
    end
        
    def arrayContainsType(w, t)
      index=-1
      for i in (0...w.length) do
        if(t == w[i].type)
          index = i
          break
        end
      end
      return index
    end    

    def discardWeapon(w)
      #Si borramos pero no está nos devuelve nil
      if weapons != nil
        @weapons.delete(w.type)
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
        nShields == 0 && nWeapons == 0
      end
    end

    def to_s
      "nWeapons: #{nWeapons}; Shields: #{nShields}; Weapons #{weapons}"
    end
    
    private_class_method :new
    public :adjust
    private :arrayContainsType
  end
end
