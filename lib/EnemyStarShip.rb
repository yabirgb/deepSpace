require_relative 'ShotResult'
module Deepspace
  class EnemyStarShip
    
    attr_reader :ammoPower
    attr_reader :damage
    attr_reader :loot
    attr_reader :name
    attr_reader :shieldPower
    
    
    public

    def initialize(name, ammoPower, shieldPower, loot, damage)
      @ammoPower = ammoPower
      @name = name
      @shieldPower = shieldPower
      @loot = loot
      @damage = damage
    end
    
    def newCopy(e)
      EnemyStarShip.new(e.name, e.ammoPower, e.shieldPower, e.loot, e.damage)
    end
    
    def UIversion
      
    end

    def fire
      @ammoPower
    end
    
    def protection
      @shieldPower
    end

    def receiveShot(shot)
      if protection < shot
        Deepspace::ShotResult::DONOTRESIST
      else  
        Deepspace::ShotResult::RESIST
      end
    end
  end
end