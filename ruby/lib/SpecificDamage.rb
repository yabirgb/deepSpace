# coding: utf-8
require_relative 'SpecificDamageToUI'
require_relative 'WeaponType'
require_relative 'Damage'

module Deepspace
  class SpecificDamage < Damage


    def initialize(nshields, wl)
      super(-1, nshields, wl)
    end

    def getUIversion
      SpecificDamageToUI.new(self)
    end
    
    def self.newCopy
      new(nShields, weapons)
    end
        
    def adjust(wl, sl)
      result = Array.new
      toRemove = Array.new(weapons)

      wl.each{|weapon|
        if toRemove.include?(weapon.type)
          result.push(weapon.type)
          toRemove.delete_at(toRemove.index(weapon.type))
        end
      }

      SpecificDamage.new(result, super.adjustShields(sl)) 
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
      @weapons.delete(w.type)
    end
    
    def hasNoEffect
      nShields == 0 && weapons.length == 0
    end

    def to_s
      "Weapons #{weapons}; Shields: #{nShields};"
    end
    
    public :adjust
    private :arrayContainsType
    public_class_method :new
  end
end
