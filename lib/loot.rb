# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

class Loot
  
  #Clase que representa el botín que se obtiene la vencer una nave enemiga
  
  attr_reader :nSupplies   #Paquetes de suministros
  attr_reader :nWeapons   #Paquetes de armas
  attr_reader :nShields   #Potenciadores de escudos
  attr_reader :nHangars   #Hangares
  attr_reader :nMedals    #Medallas
  
  def initialize(nSupplies, nWeapons, nShields, nHangars, nMedals)
    @nSupplies = nSupplies
    @nWeapons = nWeapons
    @nShields = nShields
    @nHangars = nHangars
    @nMedals = nMedals
  end
end
