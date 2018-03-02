class EnemyStarShip
  public
  
  def initialize(name, ammoPower, shieldPower, loot, damage)
    @ammoPower = ammoPower
    @name = name
    @shieldPower = shieldPower
    @loot = loot
    @damage = damage
  end
  
  def fire
    @ammoPower
  end
  
  def protection
    @shieldPower
  end
  
  def receiveShot(shot)
    if protection < shot
      
    end
  end
end
