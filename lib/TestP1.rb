require_relative 'weapon'
require_relative 'weapon_type'
require_relative 'dice'
require_relative 'game_character'
require_relative 'loot'
require_relative 'shield_booster'

class TestP1
  
  def initialize(alltests=true)
    @dice = Dice.new
    if(alltests)
      puts "Test weapon: #{testWeapon}"
      puts "=============="
      testDiceProb
      puts "=============="
      puts "Test loot: #{testLoot}"
      puts "=============="
      puts "Test ShieldBooster: #{testShieldBooster}"
      
    end
  end
  
  def testLoot
    supplies = 10
    weapons = 2
    shields = 0
    hangars = 3
    medals = 1
    loot = Loot.new(supplies, weapons, shields, hangars, medals)
    
    tsupplies = loot.nSupplies == supplies
    tweapons = loot.nWeapons == weapons
    tshields = loot.nShields == shields
    thangars = loot.nHangars == hangars
    tmedals = loot.nMedals == medals
    
    return tsupplies && tweapons && tshields && thangars && tmedals
    
  end
  
  def testWeapon
    #Creamos un objeto de tipo laser con 3 usos
    
    # Test methods
    weapon = Weapon.new("test", WeaponType::LASER, 3)
    uses = weapon.uses == 3
    type = weapon.type == WeaponType::LASER
    
    weapon.useIt
    
    newuses = weapon.uses == 2
    testpower = weapon.useIt == WeaponType::LASER.power
    weapon.useIt
    
    wasted = weapon.useIt == 1
    
    power = weapon.power == 2 #The power of a laser
    
    #Test copy
    
    newWeapon = Weapon.newCopy(weapon)
    
    sameUses = weapon.uses == newWeapon.uses
    sameType = weapon.type == newWeapon.type
    sameName = weapon.name == newWeapon.name
    testNueva = sameUses && sameType && sameName
    
    return uses && type && newuses && wasted && testpower && power && testNueva
  end
  
  def testDiceProb
    iterations = 10000
    puts "Testing Dice: "
    #=============
    results = Hash.new(0)
    (0..iterations).each do |i|
      results[@dice.initWithNHangars] += 1
    end
    
    puts "Test initWithNHangars (expected 0->0.25): #{results}"
    
    #==============
    
    results = Hash.new(0)
    (0..iterations).each do |i|
      results[@dice.initWithNShields] += 1
    end
    
    puts "Test initWithNShields (expected 0->0.25): #{results}"
    
    #==============
    
    results = Hash.new(0)
    (0..iterations).each do |i|
      results[@dice.initWithNWeapons] += 1
    end
    
    puts "Test initWithNWeapons (expected 1->0.33, 2->0.33): #{results}"
    
    #==============
    
    results = Hash.new(0)
    (0..iterations).each do |i|
      results[@dice.firstShot] += 1
    end
    
    puts "Test firstShot (expected SPACESTATION->0.5): #{results}"
    
    #==============
    
    results = Hash.new(0)
    (0..iterations).each do |i|
      results[@dice.spaceStationMoves(0.4)] += 1
    end
    
    puts "Test spaceStationMoves (expected true->0.4): #{results}"
    
  end
  
  def testShieldBooster
    # Test methods
    booster = ShieldBooster.new("test", 4.1, 3)
    uses = booster.uses == 3
    boost = booster.boost == 4.1
    
    booster.useIt
    
    newuses = booster.uses == 2
    booster.useIt
    boostUsing = booster.useIt == 4.1
    
    wasted = booster.useIt == 1
        
    #Test copy
    
    newWeapon = ShieldBooster.newCopy(booster)
    
    sameUses = booster.uses == newWeapon.uses
    sameBoost = booster.boost == newWeapon.boost
    sameName = booster.name == newWeapon.name
    testNueva = sameUses && sameBoost && sameName
    
    return uses && boost && newuses && wasted && testNueva && boostUsing
  end
  
  def testSuppliePackage
    
  end
end

testweapon = TestP1.new