# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
module Deepspace
class SpaceCity < SpaceStation
  
  attr_reader :collaborators
  
  def initialize(base, rest)
    super(base.name, base.supplies)
    @base = base
    @collaborators = Array.new 
    
    rest.each{|station|
      @collaborators.push(SpaceStation.newCopy(station))
    }
  end
  
  
  def fire
    shot = super
    
    @collaborators.each{|station|
      shot *= station.fire
    }
    
    shot
  end
  
  def protection
    prot = super
    
    @collaborators.each{|station|
      prot *= station.protection
    }
    
    prot
  end
  
  def setLoot(loot)
    super
    return nil
  end
end
end