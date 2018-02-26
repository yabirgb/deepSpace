# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module WeaponType
  class Type
    def initialize p
      @power = p
    end
    
    def power
      @power
    end
  end
  
  LASER = Type.new(2.0)
  MISSILE = Type.new(3.0) 
  PLASMA = Type.new(4.0)
end
