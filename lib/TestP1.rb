require_relative 'weapon'
require_relative 'weapon_type'

class TestP1
  
  def initialize(alltests=true)
    if(alltests)
      puts testWeapon
    end
  end
  
  def testWeapon
    #Creamos un objeto de tipo laser con 3 usos
    weapon = Weapon.new("test", WeaponType::LASER, 3)
    uses = weapon.uses == 3
    type = weapon.type == WeaponType::LASER
    return uses && type
  end
  
  def testDice
    
  end
end

testweapon = TestP1.new