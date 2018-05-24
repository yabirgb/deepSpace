require_relative 'Damage'
require_relative 'SpaceStation'
require_relative 'CardDealer'
require_relative 'Loot'

module Tests
  class Test2
    
    def main
      #Creamos los laser
      w1 = Deepspace::Weapon.new("laser1", Deepspace::WeaponType::LASER, 0)
      w2 = Deepspace::Weapon.new("laser2", Deepspace::WeaponType::LASER, 1)
      w4 = Deepspace::Weapon.new("misil1", Deepspace::WeaponType::MISSILE, 1)
      w3 = Deepspace::Weapon.new("plasma3", Deepspace::WeaponType::PLASMA, 3)
      
      #Creamos escudos
      
      s1 = Deepspace::ShieldBooster.new("shield1", 5, 6)
      s2 = Deepspace::ShieldBooster.new("shield2", 3, 0) 
      
      # Creamos algunos tipos de daño
      
      dn = Deepspace::Damage.newNumericWeapons(2, 3)
      
      arrayDamage = Array.new
      
      arrayDamage.push(Deepspace::WeaponType::LASER)
      #arrayDamage.push(Deepspace::WeaponType::LASER)
      #arrayDamage.push(Deepspace::WeaponType::LASER)
      arrayDamage.push(Deepspace::WeaponType::PLASMA)
      arrayDamage.push(Deepspace::WeaponType::PLASMA)
      
      ds = Deepspace::Damage.newSpecificWeapons(arrayDamage, 1)
      
      #Creamos una estructura de recursos
      
      weapons = Array.new([w1,w2,w3,w4])
      shields = Array.new([s1,s2])
      
      #Ajustamos el daño con valor numérico
      adn1 = dn.adjust(weapons, shields)
      puts "Expects 2 and 2: #{adn1}"
      
      #Ajustamos el daño con un valor especifico
      puts "Quitamos dos armas de tipo laser:"
      puts "=============="
      puts ds
      puts "=============="
      puts ds.adjust(weapons, shields)
      puts "=============="
      puts weapons
    end
    
  end
end

Tests::Test2.new.main