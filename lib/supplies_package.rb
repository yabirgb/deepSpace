# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

class SuppliesPackage
  # Paquete de suministros para una estación espacial
  
  attr_reader :ammoPower
  attr_reader :fuelUnits
  attr_reader :shieldPower
  
  def initialize(ammoPower, fuelUnits, shieldPower)
    @ammoPower = ammoPower
    @fuelUnits = fuelUnits
    @shieldPower = shieldPower
  end
  
  def self.newCopy(s)
    SuppliesPackage.new(s.ammoPower, s.fuelUnits, s.shieldPower)
  end
end
