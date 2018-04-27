require_relative 'ShotResult'
require_relative 'EnemyToUI'

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
    
    def self.newCopy(e)
      EnemyStarShip.new(e.name, e.ammoPower, e.shieldPower, e.loot, e.damage)
    end

    def fire
      @ammoPower
    end
    
    def protection
      @shieldPower
    end

    def receiveShot(shot)
      if protection < shot
        return ShotResult::DONOTRESIST
      else  
        return ShotResult::RESIST
      end
    end
    
    def to_s
      "Name: #{@name}; Damage: #{@damage}; Loot: #{@loot}; AmmoPower: #{@ammoPower}; ShieldPower: #{@shieldPower} "
    end
    
    def getUIversion
      EnemyToUI.new(self)
    end
    
  end
end