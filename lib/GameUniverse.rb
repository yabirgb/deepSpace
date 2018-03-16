require_relative 'Dice'
require_relative 'SpaceStation'
require_relative 'GameStateController'
require_relative 'GameUniverseToUI'
require_relative 'GameState'
require_relative 'GameCharacter'
require_relative 'ShotResult'
require_relative 'CombatResult'
require_relative 'Loot'

module Deepspace
  class GameUniverse
    
    def initialize()
      @WIN = 10
      @currentStationIndex = nil
      @gameState = GameStateController.new
      @currentEnemy = nil
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
      if state == GameState::INIT or state == GameState::AFTERCOMBAT
        @spaceStations[@currentStationIndex].discardShieldBoosterInHangar(i)
      end
    end
    
    def mountWeapon(i)
      if state == GameState::INIT or state == GameState::AFTERCOMBAT
        @spaceStations[@currentStationIndex].mountWeapon(i)
      end
    end
    
    def mountShieldBooster(i)
      if state == GameState::INIT or state == GameState::AFTERCOMBAT
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
    
    def state
      @gameState.state
    end
    
    def getUIversion
      GameUniverseToUI.new(@currentStation, @currentEnemy)
    end
    
    def init(names)
      state = @gameState.state
      
      if state == GameState::CANNOTPLAY
        @spaceStations = Array.new
        dealer = CardDealer.instance 
        
        names.each{|name|
          supplies = dealer.nextSuppliesPackage
          station = SpaceStation.new(name, supplies)
          nh = @dice.initWithNHangars
          nw = @dice.initWithNWeapons
          ns = @dice.initWithNShields
          
          l = Loot.new(0, nh, nw, ns, 0)
          
          station.setLoot(l)
          @spaceStations.push(station)
          
        }
        
        @currentStationIndex = @dice.whoStarts(names.length)
        @currentStation = @spaceStations[@currentStationIndex]
        @currentEnemy = dealer.nextEnemy()
        @gameState.next(@turns, @spaceStations.length)
      end
      
    end
    
    def nextTurn
      gameState = @gameState.state
      if gameState == GameState::AFTERCOMBAT
        stationState = currentStation.validState
        
        if stationState
          @currentStationIndex = (@currentStationIndex +1) % @spaceStation.length
          @turns += 1
          
          @currentStation = @spaceStations[@currentStationIndex]
          @currentStation.cleanUpMountedItems
          dealer = CardDealer.instance 
          @currentEnemy = dealer.nextEnemy
          @gameState.next(@turns, @spaceStation.length)
          true
        end
        
        false
      end
      
    end
    
    def combat
      state = @gameState.state
      
      if (state == GameState::BEFORECOMBAT) || (state == GameState::INIT)
        combatGo(@currentStation, @currentEnemy)
      else
        CombatResult::NOCOMBAT
      end
      
    end
    
    def combatGo(station, enemy)
      
      state = @gameState.state
      
      if (state == GameState::BEFORECOMBAT) || (state == GameState::INIT)
      
        ch = @dice.firstShot
        
        #Establecemos el combate
        
        if ch == GameCharacter::ENEMYSTARSHIP
          fire = enemy.fire
          result = station.receiveShot(fire)
          
          if result == ShotResult::RESIST
            fire = station.fire
            result = enemy.receiveShot(fire)
            enemyWins = result == ShotResult::RESIST
          else  
            enemyWins = true
          end
        else
          fire = station.fire
          result = enemy.receiveShot(fire)
          enemyWins = result==ShotResult::RESIST
        end
        
        #Analizamos resultado del combate
        
        if enemyWins
          s = station.speed
          moves = @dice.spaceStationMoves(s)
          
          if moves 
            station.move
            combatResult=CombatResult::STATIONESCAPES
          else
            damage = enemy.damage
            station.setPendingDamage(damage)
            combatResult=CombatResult::ENEMYWINS
          end
        else
          aLoot = enemy.loot
          station.setLoot(aLoot)
          combatResult=CombatResult::STATIONWINS
        end
        
        combatResult
        
      end 
      
    end
    
  end
end