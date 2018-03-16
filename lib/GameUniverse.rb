require 'singleton'


require_relative 'Dice'
require_relative 'SpaceStation'
require_relative 'GameStateController'
require_relative 'GameUniverseToUI'
require_relative 'GameState'
require_relatice 'GameCharacter'

module Deepspace
  class GameUniverse
    
    def initialize()
      @WIN = 10
      @currentStationIndex = nil
      @gameState = GameStateController.new
      @turns = 0
      @dice = Dice.new()
    end
    
    def discardHangar
      if @gameState.state == GameState::INIT or @gameState.state == GameState::AFTERCOMBAT
        @spaceStations[@currentStationIndex].discardHangar
      end
    end
    
    def discardWeapon(i)
      if @gameState.state == GameState::INIT or @gameState.state == GameState::AFTERCOMBAT
        @spaceStations[@currentStationIndex].discardWeapon(i)
      end
    end
    
    def discardWeaponInHangar(i)
      if @gameState.state == GameState::INIT or @gameState.state == GameState::AFTERCOMBAT
        @spaceStations[@currentStationIndex].discardWeaponInHangar(i)
      end
    end
    
    def discardShieldBooster(i)
      if @gameState.state == GameState::INIT or @gameState.state == GameState::AFTERCOMBAT
        @spaceStations[@currentStationIndex].discardShieldBooster(i)
      end
    end
    
    def discardShieldBoosterInHangar(i)
      if getState == GameState::INIT or getState == GameState::AFTERCOMBAT
        @spaceStations[@currentStationIndex].discardShieldBoosterInHangar(i)
      end
    end
    
    def mountWeapon(i)
      if getState == GameState::INIT or getState == GameState::AFTERCOMBAT
        @spaceStations[@currentStationIndex].mountWeapon(i)
      end
    end
    
    def mountShieldBooster(i)
      if getState == GameState::INIT or getState == GameState::AFTERCOMBAT
        @spaceStations[@currentStationIndex].mountShieldBooster(i)
      end
    end
    
    
    def haveAWinner
      if @spaceStations[@currentStationIndex].nMedals == @WIN
        true
      else  
        false
      end
      
    end
    
    def getState
      @gameState.state
    end
    
    def getUIversion
      GameUniverseToUI.new()
    end
    
    def init(names)
      state = @gameState.getState
      
      if state == GameState::CANNOTPLAY
        @spaceStations = Array.new
        @dealer = CardDealer.instance 
        
        names.each{|name|
          supplies = @dealer.nextSuppliesPackage
          station = SpaceStation.new(name, supplies)
          nh = @dice.initWithNHangars
          nw = @dice.initWithWeapons
          ns = @dice.initWithNShields
          
          l = loot.new(0, nh, nw, ns, 0)
          
          station.setLoot(l)
          @spaceStations.push(station)
          
        }
        
        @currentStationIndex = @dice.whoStarts(names.length)
        @currentStation = @spaceStations[@currentStationIndex]
        @currentEnemy = @dealer.nextEnemy()
        @gameState.next(@turns, @spaceStation.length)
      end
      
    end
    
    def nextTurn
      gameState = @gameState.getState
      if gameState == GameState::AFTERCOMBAT
        stationState = currentStation.validState
        
        if stationState
          @currentStationIndex = (@currentStationIndex +1) % @spaceStation.length
          @turns += 1
          
          @currentStation = @spaceStations[@currentStationIndex]
          @currentStation.cleanUpMountedItems
          dealer = @dealer.instance
          @currentEnemy = dealer.nextEnemy
          @gameState.next(@turns, @spaceStation.length)
          true
        end
        
        false
      end
      
    end
    
    def combat
      
      state = @gameState.getState
      
      if (state == GameState::BEFORECOMBAT) || (state == GameState.INIT)
      
        ch = @dice.firstShot
        
        if ch == GameCharacter.ENEMYSTARSHIP
          fire = @enemy
        end
        
      end 
      
    end
    
  end
end