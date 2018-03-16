/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package deepspace;



/**
 *
 * @author yabir
 */

class TestP1 {
    
    // Test para ShieldBooster
    
    private static boolean classShieldBoosterTest(){
        boolean test = false;
        
        ShieldBooster testObj = new ShieldBooster("tester", 1.0f, 3);
        
        if(testObj.getBoost() == 1.0f && "tester".equals(testObj.getName())
                && testObj.getUses() == 3)
            test = true;
        
        
        
        return test;
    }
    
    private static boolean classWeaponTest(){
        boolean part1 = true;
        boolean part2 = true;
        
        Weapon test = new Weapon("test", WeaponType.PLASMA, 2);
        Weapon clon = new Weapon(test);
        
        if(!test.getName().equals("test"))
            part1 = false;
        
        if(!(test.getUses() == 2))
            part1 = false;
        
        test.useIt();
        test.useIt();
                
        boolean using = test.useIt() == 1.0f;
        
        if(WeaponType.PLASMA.getPower() != test.getPower())
            part1 = false;
        
        if(!clon.getName().equals("test"))
            part2 = false;
        
        if(!(clon.getUses() == 2))
            part2 = false;    
        
        boolean power = test.getPower() == WeaponType.PLASMA.getPower();
        
        return part1 && part2 && using && power;
    }
    
    private static boolean classDiceTest(){
    
        int iterations = 1000;
        Dice dice = new Dice();
        
        System.out.println("Testing Dice");
        
        float total = 0;
        
        //=======
        
        for(int i= 0; i < iterations; i++){
            if(dice.initWithNShields() == 0)
                total += 1.0/iterations;
        }
        
        System.out.println("Test initWithNHangars (expected 0->0.25): "+ total);
        
        //=======
        total = 0;
        for(int i= 0; i < iterations; i++){
            if(dice.initWithNWeapons() == 1)
                total += 1.0/iterations;
        }
        
        System.out.println("Test initWithNWeapons (expected 1->0.33, 2->0.33): "+ total);
        
        //=======
        total = 0;
        for(int i= 0; i < iterations; i++){
            if(dice.firstShot() == GameCharacter.SPACESTATION)
                total += 1.0/iterations;
        }
        
        System.out.println("Test firstShot (expected SPACESTATION->0.5): "+ total);
        
        //=======
        total = 0;
        for(int i= 0; i < iterations; i++){
            if(dice.spaceStationMoves(0.4f) == true)
                total += 1.0/iterations;
        }
        
        System.out.println("Test spaceStationMoves (expected true->0.4):  "+ total);
        
        
        //=======
        total = 0;
        for(int i= 0; i < iterations; i++){
            if(dice.initWithNShields() == 0)
                total += 1.0/iterations;
        }
        
        System.out.println("Test initWithNShields (expected 0->0.25):  "+ total);
        
        //=======
        
        total = 0;
        for(int i= 0; i < iterations; i++){
            if(dice.whoStarts(6) == 1)
                total += 1.0/iterations;
        }
        
        System.out.println("Test whoStarts (expected 0->0.166):  "+ total);
        
        return true;
    }
    
    //Crear una objeto
    
    public static void main(String arg[]){
        System.out.println("Testint ShieldBooster: " + classShieldBoosterTest() );    
        System.out.println("Testint classWeaponTest: " + classWeaponTest() );
        classDiceTest();
    }
    
    
}
