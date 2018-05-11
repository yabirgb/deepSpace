# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require_relative 'Dice'
require_relative 'PowerEfficientSpaceStation'
require_relative 'PowerEfficientSpaceStationToUI'

module Deepspace
class BetaPowerEfficientSpaceStation < PowerEfficientSpaceStation
  @@EXTRAEFFICIENCY = 1.2
  
  def initialize(station)
    super
    @dice = Dice.new()
  end
  
  def fire
    shot = super
    
    if @dice.extraEfficiency
      shot *= @@EXTRAEFFICIENCY
    end
    
    shot
      
  end
  
  def getUIversion
    BetaPowerEfficientSpaceStationToUI.new(self)
  end
  
end
end