# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
require_relative 'SuppliesPackage'
require_relative 'WeaponType'
require_relative 'Damage'
require_relative 'SpaceStation'
require_relative 'Weapon'
module Test
    class Examen3
      
      def principal
        sp1 = Deepspace::SuppliesPackage.new(2,5,3)
        yabir = Deepspace::SpaceStation.new("yabir",sp1)
        puts "2a) #{yabir}"
        
        hangar = Deepspace::Hangar.new(3)
        
        yabir.receiveHangar(hangar)
        
        puts "2b) #{yabir}"
        
        espada = Deepspace::Weapon.new("Espada",Deepspace::WeaponType::LASER,1)
        yabir.receiveWeapon(espada)
        
        puts "2c) #{yabir}"
        
        yabir.receiveWeapon(espada)
        yabir.receiveWeapon(espada)
        yabir.receiveWeapon(espada)
        
        puts "2d) #{yabir}"
        
        yabir.mountWeapon(0)
        yabir.mountWeapon(0)
        
        puts "2e) #{yabir}"
        
        escudo = Deepspace::ShieldBooster.new("Escudo",1.5,2)
        yabir.receiveShieldBooster(escudo)
        
        puts "2f) #{yabir}"
        
        yabir.receiveShieldBooster(escudo)
        yabir.receiveShieldBooster(escudo)
        puts "2g) #{yabir}"
        
        yabir.mountShieldBooster(1)
        puts "2h) #{yabir}"
        
        d = Deepspace::Damage.newSpecificWeapons([Deepspace::WeaponType::PLASMA,Deepspace::WeaponType::LASER,Deepspace::WeaponType::MISSILE],3)
        puts "3b) #{d}\n\n"
        
        dd = d.adjust([Deepspace::WeaponType::MISSILE,Deepspace::WeaponType::LASER ,Deepspace::WeaponType::LASER], [escudo, escudo])
        puts "3c) #{dd}\n"
      end
    end
    
    examen = Examen3.new
    examen.principal
end
