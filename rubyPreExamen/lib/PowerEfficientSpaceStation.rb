# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'SpaceStation'
require_relative 'PowerEfficientSpaceStationToUI'
require_relative 'SuppliesPackage'

module Deepspace
class PowerEfficientSpaceStation < SpaceStation
  
  @@EFFICIENCYFACTOR = 1.10
  
  def initialize(station)
    s = SuppliesPackage.new(station.ammoPower, station.fuelUnits, station.shieldPower)
    super(station.name, s, station.weapons, station.shieldBoosters,
      station.nMedals)
  end
  
  def self.newCopy(station)
    h = super
    return h
  end
  
  def setLoot(loot)
    super
    return Transformation::NOTRANSFORM
  end
  
  def fire
    shot = super
    shot*@@EFFICIENCYFACTOR
  end
  
  def protection
    shield = super
    shield*@@EFFICIENCYFACTOR
  end
  
  def getUIversion
    return PowerEfficientSpaceStation.new(self)
  end
end
end