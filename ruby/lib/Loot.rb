require_relative 'LootToUI'

module Deepspace
  class Loot

    #Loot obtained after defeating an enemy ship

    attr_reader :nSupplies  #int Paquetes de suministros
    attr_reader :nWeapons   #int Paquetes de armas
    attr_reader :nShields   #int Potenciadores de escudos
    attr_reader :nHangars   #int Hangares
    attr_reader :nMedals    #int Medallas

    def initialize(nSupplies, nWeapons, nShields, nHangars, nMedals)
      @nSupplies = nSupplies
      @nWeapons = nWeapons
      @nShields = nShields
      @nHangars = nHangars
      @nMedals = nMedals
    end
    
    def getUIversion
      LootToUI.new(self)
    end
    
    def to_s
      "Supplies: #{@nSupplies}"
    end
  end
end