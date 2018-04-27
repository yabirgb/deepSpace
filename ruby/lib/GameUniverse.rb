require_relative 'Dice'
require_relative 'SpaceStation'
require_relative 'GameStateController'
require_relative 'GameUniverseToUI'
require_relative 'GameState'
require_relative 'GameCharacter'
require_relative 'ShotResult'
require_relative 'CombatResult'
require_relative 'Loot'
require_relative 'SpaceCity'
require_relative 'BetaPowerEfficientSpaceStation'
require_relative 'PowerEfficientSpaceStation'

module Deepspace
  class GameUniverse
    @@WIN = 10
    
    def initialize()
      @currentStationIndex = nil
      @gameState = GameStateController.new
      @currentEnemy = nil
      @turns = 0
      @dice = Dice.new()
      @haveSpaceCity = false
    end
    
    def discardHangar
      if @gameState.state == GameState::INIT || @gameState.state == GameState::AFTERCOMBAT
        @spaceStations[@currentStationIndex].discardHangar
      end
    end
    
    def discardWeapon(i)
      if @gameState.state == GameState::INIT || @gameState.state == GameState::AFTERCOMBAT
        @spaceStations[@currentStationIndex].discardWeapon(i)
      end
    end
    
    def discardWeaponInHangar(i)
      if @gameState.state == GameState::INIT || @gameState.state == GameState::AFTERCOMBAT
        @spaceStations[@currentStationIndex].discardWeaponInHangar(i)
      end
    end
    
    def discardShieldBooster(i)
      if @gameState.state == GameState::INIT || @gameState.state == GameState::AFTERCOMBAT
        @spaceStations[@currentStationIndex].discardShieldBooster(i)
      end
    end
    
    def discardShieldBoosterInHangar(i)
      if state == GameState::INIT || state == GameState::AFTERCOMBAT
        @spaceStations[@currentStationIndex].discardShieldBoosterInHangar(i)
      end
    end
    
    def mountWeapon(i)
      if state == GameState::INIT or state == GameState::AFTERCOMBAT
        @spaceStations[@currentStationIndex].mountWeapon(i)
      end
    end
    
    def mountShieldBooster(i)
      if state == GameState::INIT || state == GameState::AFTERCOMBAT
        @spaceStations[@currentStationIndex].mountShieldBooster(i)
      end
    end
    
    
    def haveAWinner
      @spaceStations[@currentStationIndex].nMedals == @@WIN
    end
    
    def state
      @gameState.state
    end
    
    def getUIversion
      GameUniverseToUI.new(@currentStation, @currentEnemy)
    end
    
    def init(names)
      
      if state == GameState::CANNOTPLAY
        @spaceStations = Array.new
        dealer = CardDealer.instance 
        
        names.each{|name|
          supplies = dealer.nextSuppliesPackage
          station = SpaceStation.new(name, supplies)
          nh = @dice.initWithNHangars
          nw = @dice.initWithNWeapons
          ns = @dice.initWithNShields
          
          l = Loot.new(0, nw, ns, nh, 0)
          
          station.setLoot(l)
          @spaceStations.push(station)
          
        }
        
        @currentStationIndex = @dice.whoStarts(names.length)
        @currentStation = @spaceStations[@currentStationIndex]
        @currentEnemy = dealer.nextEnemy
        @gameState.next(@turns, @spaceStations.length)
      end
      
    end
    
    def nextTurn
      gameState = @gameState.state
      if gameState == GameState::AFTERCOMBAT
        stationState = @currentStation.validState
        if stationState
          @currentStationIndex = (@currentStationIndex +1) % @spaceStations.length
          @turns += 1
          
          @currentStation = @spaceStations[@currentStationIndex]
          @currentStation.cleanUpMountedItems
          dealer = CardDealer.instance 
          @currentEnemy = dealer.nextEnemy
          @gameState.next(@turns, @spaceStations.length)
          return true
        end
      end
      false
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
        combatResult=CombatResult::STATIONWINSANDCONVERTS
        
        if aLoot.efficient
          makeStationEfficient
        elsif aLoot.spaceCity  
          createSpaceCity
        else  
          combatResult=CombatResult::STATIONWINSANDCONVERTS
        end
        
        
      end
      @gameState.next(@turns, @spaceStations.length)
      return combatResult
      
    end
    
    def createSpaceCity
      if not @haveSpaceCity
        city = SpaceCity(@spaceStation[@currentStationIndex], Array.new(@spaceStation).delete_at(@currentStationIndex))
        @spaceStation[@currentStationIndex] = city
        @haveSpaceCity = true
        puts "TRANSFORMATION TO SPACECITY FINISHED"
      end
    end
    
    def makeStationEfficient
      if @dice.extraEfficiency
        station = BetaPowerEfficientSpaceStation.new(@spaceStations[@currentStationIndex])
        puts "TRANSPORMATED TO BETAEFFICIENT"
      else
        station = PowerEfficientSpaceStation.new(@spaceStations[@currentStationIndex])
        puts "TRANSPORMATED TO EFFICIENT"
      end
      
      @spaceStation[@currentStationIndex] = station
      
    end
    
  end
end
