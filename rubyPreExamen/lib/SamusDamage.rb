# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

module Deepspace
  class SamusDamage < SpecificDamage
    
    def adjust(w,s)
      d = super
      if(true)
        
        if(d.weapons != nil)
          weapons = Array.new(d.weapons)
          weapons.delete_at(0)
          return SamusDamage.new(weapons, d.nShields)
        end
      end
      
      return SamusDamage.new(d.weapons, d.nShields)
    end
  end
end