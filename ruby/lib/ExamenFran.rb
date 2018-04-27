#encoding:utf-8

# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'Weapon'
require_relative 'WeaponType'
require_relative 'Damage'
require_relative 'ShieldBooster'

module Test
  
  class Examen
    
    def initialize(c)
      @contador = c
    end
    
    def self.newNoParam
      return Examen.new(0)
    end
    
    #El consultor es de instancia pues eswtamos consultando un atributo de instancia
    def contador
      @contador
    end
    
    #Como contador es de instancia, cada instancia tendrá su propio contador, luego
    #vuelve a ser de instancia
    def contabilizar
      @contador += 1
      if (@contador > 10)
        @contador = 10
      end
    end
    
    def principal
      contabilizar
      puts contador
      
      w1 = Deepspace::Weapon.new("weapon1", Deepspace::WeaponType::LASER, 0)
      w2 = Deepspace::Weapon.new("weapon2", Deepspace::WeaponType::MISSILE, 1)
      w3 = Deepspace::Weapon.new("weapon3", Deepspace::WeaponType::PLASMA, 2)
      
      array_weapons = Array.new([w1, w2, w3])
     
      media = 0.0
      for w in array_weapons
        media += w.power
      end
      
      media = media/array_weapons.size
      
      puts "La media es #{media}"
      
      d = Deepspace::Damage.newSpecificWeapons([Deepspace::WeaponType::LASER, Deepspace::WeaponType::PLASMA, Deepspace::WeaponType::LASER, Deepspace::WeaponType::MISSILE ], 4)
      
      d_ajustado = d.adjust([w1,w2],[Deepspace::ShieldBooster.new("escudo", 3, 3)])
      
      puts "El daño ajustado es:"
      puts d_ajustado.to_s
      
    end
  end
  
  Examen.newNoParam.principal
    
end
