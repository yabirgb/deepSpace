# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.  
require_relative 'GameCharacter'

module Deepspace
  class Dice
    def initialize
      @NHANGARSPROB = 0.25
      @NSHIELDSPROB = 0.25
      @NWEAPONSPROB = 0.33
      @FIRSTSHOTPROB = 0.5
      @EXTRAEFFICIENCYPROB = 0.8
      @generator = Random.new
    end 

    def initWithNHangars
      if @generator.rand <= @NHANGARSPROB
        0
      else
        1
      end
    end

    def initWithNWeapons
      #I use && instead of because of the precedence of operators
      #https://stackoverflow.com/questions/1426826/difference-between-and-and-in-ruby

      gen = @generator.rand
      if gen < @NWEAPONSPROB
        1
      elsif gen > @NWEAPONSPROB && gen <= 2*@NWEAPONSPROB
        2
      else
        3
      end
    end

    def initWithNShields
      if @generator.rand < @NSHIELDSPROB
        0
      else
        1
      end
    end

    def whoStarts(nPlayers)
      #Dos puntos es [a,b]
      #Tres puntos es [a,b)
      @generator.rand(0...nPlayers)
    end

    def firstShot
      if @generator.rand < @FIRSTSHOTPROB
        GameCharacter::SPACESTATION
      else  
        GameCharacter::ENEMYSTARSHIP
      end
    end

    def spaceStationMoves(speed)
      #We assume 0 <= speed < 1 

      if @generator.rand < speed
        true
      else
        false
      end
    end
    
    def extraEfficiency
      return @generator.rand < @EXTRAEFFICIENCYPROB
    end
  end
end