# coding: utf-8
require_relative 'DamageToUI'
require_relative 'WeaponType'

module Deepspace
  class Damage

      attr_reader :nShields # Número de escudos a descartar
     #Vector de weaponType

    def initialize(nshields)
      @nShields = nshields 
    end
    
    def self.newCopy
      rause "ERROR: This method has not been implemented yet!"
    end

    def getUIversion
      DamageToUI.new(self)
    end
 
    def adjust(wl, sl)
      raise "ERROR: This method has not been implemented yet!"
    end
    
    def adjustShields(sl)
      [nShields,sl.length].min
    end

    def discardWeapon(w)
      raise "ERROR: This method has not been implemented yet!"
    end
    
    def discardShieldBooster
      @nShields -= 1 if nShields > 0
    end
    
    def hasNoEffect
      raise "ERROR: This method has not been implemented yet!"
    end
    
    def getWeaponInfo
      raise "ERROR: This method has not been implemented yet!"
    end

    def to_s
      "Shields: #{nShields};"
    end
    
    private_class_method :new
  end
end
