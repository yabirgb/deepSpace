#encoding:utf-8


# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'EnemyStarShip'
require_relative 'SuppliesPackage'
require_relative 'WeaponType'
require_relative 'Loot'
require_relative 'Damage'
require_relative 'SpaceStation'
require_relative 'GameUniverse'

module TestP3
  
  class Examen
    def self.principal
      pdice = Deepspace::Dice.new
      suma1 = 0
      suma2 = 0
      i = 1
      while i<=1000 do
        aux = pdice.initWithNWeapons
        if (aux == 1)
          suma1 = suma1 + 1
        elsif (aux == 2)
            suma2 = suma2 + 1
        end
        i = i+1
      end
      puts "1: #{suma1}; 2: #{suma2}; 3: #{1000 - suma1 - suma2}"
      puts "======"
      
      
      sp1 = Deepspace::SuppliesPackage.new(1,1,1)
      loot = Deepspace::Loot.new(0, 3, 2, 1, 0)
      d = Deepspace::Damage.newSpecificWeapons([Deepspace::WeaponType::PLASMA, Deepspace::WeaponType::MISSILE, Deepspace::WeaponType::LASER, Deepspace::WeaponType::PLASMA], 10)
      enemy = Deepspace::EnemyStarShip.new("nombre", 500000, 500000, loot, d)
      #puts enemy.to_s
      
      #El paquete de suministros puede ser obtenido creando una instancia o usando la clase Dado.
      station1 = Deepspace::SpaceStation.new("station1",sp1)
      #El botín que me piden es loot, creado antes.
      station1.setLoot(loot) 
      puts "Antes de montar:"
      puts station1.to_s
      #Montamos 1 escudo y 3 armas (no caben dos escudos porque el hangar es de 4)
      station1.mountShieldBooster(0)
      station1.mountWeapon(0)
      station1.mountWeapon(0)
      station1.mountWeapon(0)
      puts "Después de montar:"
      puts station1.to_s
      puts "======"
      
      universe = Deepspace::GameUniverse.new
      universe.init(["a"])
      
      #Para usar este método hay que cambiar su visibilidad de "clase" a pública
      universe.combatGo(station1, enemy)
      
      puts station1.to_s
      puts station1.validState

    end
  end
  
  Examen.principal
    
end
