# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'SpecificDamage'
require_relative 'WeaponType'
require_relative 'Weapon'
require_relative 'ShieldBooster'
require_relative 'SamusDamage'

module Yabir
    class Samu
      def main
        d1 = Deepspace::SpecificDamage.new([Deepspace::WeaponType::LASER, Deepspace::WeaponType::LASER, Deepspace::WeaponType::PLASMA], 2)
        d2 = Deepspace::SpecificDamage.new([Deepspace::WeaponType::LASER, Deepspace::WeaponType::MISSILE, Deepspace::WeaponType::PLASMA], 3)
      
        arma1 = Deepspace::Weapon.new("arma1", Deepspace::WeaponType::MISSILE,2);
        arma2 = Deepspace::Weapon.new("arma2", Deepspace::WeaponType::LASER,2);

        escudo1 = Deepspace::ShieldBooster.new("Escudo ACME",1.5,2)
        escudo2 = Deepspace::ShieldBooster.new("Escudo ACME2",1.5,2)
        
        d3 = d1.adjust([arma1, arma2], [escudo1])
        d4 = d2.adjust([arma1, arma2], [escudo1])
        
        
        puts("d3")
        puts(d3.getUIversion().to_s)
        puts("d4")
        puts(d4.getUIversion().to_s)
        
        d5 = Deepspace::SamusDamage.new([Deepspace::WeaponType::LASER, Deepspace::WeaponType::MISSILE, Deepspace::WeaponType::PLASMA], 3)
        d6 = d5.adjust([arma1, arma2], [escudo1])
        
        puts("d6")
        puts(d6.getUIversion())
      end
    end
    
    d = Samu.new
    d.main()
    
end

  
