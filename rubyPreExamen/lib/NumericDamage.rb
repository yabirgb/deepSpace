# coding: utf-8
require_relative 'NumericDamageToUI'
require_relative 'WeaponType'
require_relative 'Damage'

module Deepspace
  class NumericDamage < Damage

    attr_reader :nWeapons #Número de armas a descartar
    
    def initialize(w, s)
      super(s)
      @nWeapons = w
    end

    def getUIversion
      NumericDamageToUI.new(self)
    end
    
    def self.newCopy
      new(nShields, nWeapons)
    end
        
    def adjust(wl, sl)
      NumericDamage.new([nWeapons,wl.length].min, adjustShields(sl))
    end
        
    def discardWeapon(w)
      @nWeapons -= 1
    end
    
    
    def hasNoEffect
      nShields == 0 && nWeapons == 0
    end

    def to_s
      "nWeapons: #{nWeapons};" + super
    end
    
    public :adjust
    public_class_method :new
  end
end
