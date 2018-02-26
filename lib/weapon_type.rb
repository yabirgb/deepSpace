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
  
<<<<<<< HEAD
  LASER =   Type.new(2.0)
=======
  LASER = Type.new(2.0)
>>>>>>> 666d3ab2b6d674d873579a815407c4e9b297b01b
  MISSILE = Type.new(3.0) 
  PLASMA = Type.new(4.0)
end
