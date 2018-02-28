require_relative 'weapon'
require_relative 'weapon_type'
require_relative 'dice'
require_relative 'game_character'
require_relative 'loot'

class TestP1
  
  def initialize(alltests=true)
    @dice = Dice.new
    if(alltests)
      puts "Test weapon: #{testWeapon}"
      puts "=============="
      testDiceProb
      puts "=============="
      puts "Test loot: #{testLoot}"
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
    weapon = Weapon.new("test", WeaponType::LASER, 3)
    uses = weapon.uses == 3
    type = weapon.type == WeaponType::LASER
    return uses && type
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
    
  end
end

testweapon = TestP1.new