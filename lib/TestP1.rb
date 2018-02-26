require_relative 'weapon'
require_relative 'weapon_type'
require_relative 'dice'
require_relative 'game_character'

class TestP1
  
  def initialize(alltests=true)
    @dice = Dice.new
    if(alltests)
      puts testWeapon
      testDiceProb
    end
  end
  
  def testWeapon
    #Creamos un objeto de tipo laser con 3 usos
    weapon = Weapon.new("test", WeaponType::LASER, 3)
    uses = weapon.uses == 3
    type = weapon.type == WeaponType::LASER
    return uses && type
  end
  
  def testDiceProb
    
    #=============
    results = Hash.new(0)
    (0..1000).each do |i|
      results[@dice.initWithNHangars] += 1
    end
    
    puts "Test initWithNHangars (expected 0->0.25): #{results}"
    
    #==============
    
    results = Hash.new(0)
    (0..1000).each do |i|
      results[@dice.initWithNShields] += 1
    end
    
    puts "Test initWithNShields (expected 0->0.25): #{results}"
    
    #==============
    
    results = Hash.new(0)
    (0..1000).each do |i|
      results[@dice.initWithNWeapons] += 1
    end
    
    puts "Test initWithNWeapons (expected 1->0.33, 2->0.33): #{results}"
    
    #==============
    
    results = Hash.new(0)
    (0..1000).each do |i|
      results[@dice.firstShot] += 1
    end
    
    puts "Test firstShot (expected SPACESTATION->0.5): #{results}"
    
    #==============
    
    results = Hash.new(0)
    (0..1000).each do |i|
      results[@dice.spaceStationMoves(0.4)] += 1
    end
    
    puts "Test spaceStationMoves (expected true->0.4): #{results}"
    
  end
end

testweapon = TestP1.new