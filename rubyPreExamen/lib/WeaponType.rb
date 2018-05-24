# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Deepspace
  module WeaponType
    class Type
      def initialize(p,s)
        @name = s
        @power = p
      end

      def power
        @power
      end
      
      def to_s
        "#{@name}"
      end
    end
    
    def to_s
      "Weapon #{@name } with #{@power} units of power"
    end 

    LASER = Type.new(2.0, "LASER")
    MISSILE = Type.new(3.0, "MISSILE") 
    PLASMA = Type.new(4.0, "PLASMA")
  end
end
