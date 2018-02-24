# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

class Weapon
  
  attr_reader :type
  attr_reader :uses
  
  def initialize(name, type, uses)
    @name = name
    @type = type
    @uses = uses
  end
  
  def newCopy(s)
    return Weapon.new(s.name, s.type, s.uses)
  end
  
  def power
    @type.power
  end
  
  def useIt
    if uses > 0
      uses -= 1
      power
    else
      1.0
    end
  end
  
end
