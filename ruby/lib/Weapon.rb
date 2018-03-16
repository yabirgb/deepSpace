require_relative 'WeaponToUI'
module Deepspace
  class Weapon

    attr_reader :name
    attr_reader :type
    attr_reader :uses

    def initialize(name, type, uses)
      @name = name
      @type = type
      @uses = uses
    end

    def self.newCopy(s)
      return Weapon.new(s.name, s.type, s.uses)
    end

    def power
      @type.power
    end

    def useIt
      if uses > 0
        @uses -= 1
        power
      else
        1.0
      end
    end
    
    def getUIversion
      WeaponToUI.new(self)
    end
    
    def to_s
      "Name: #{@name} - type: #{@type} - uses: #{@uses}"
    end
  end
end
