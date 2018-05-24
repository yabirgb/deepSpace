require_relative "SpaceStation"
require_relative "SuppliesPackage"
require_relative "EnemyStarShip"
require_relative "Loot"
require_relative "Damage"

module Pre_examen
  class Examen
    
    def self.main
      #Mostrar las armas
      s = Deepspace::SuppliesPackage.new(1,1,1)
      ship = Deepspace::SpaceStation.new("Mi supernave",s)
      
      (0...5).each{|_|
        puts(ship.weapons.length)
      }
      
      #Crear un enemigo
      l = Deepspace::Loot.new(0, 3, 3, 3, 1)
      d = Deepspace::Damage.newSpecificWeapons([Deepspace::WeaponType::LASER],1)
      enemy = Deepspace::EnemyStarShip.new("Mi enemigo", 3, 2, l, d)
      
      puts("Enemy #{enemy.name}:\nLoot: #{enemy.loot}\nDamage: #{enemy.damage}")
      
    end
  end
  
  Examen.main
end

