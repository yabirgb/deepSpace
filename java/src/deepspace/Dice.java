/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package deepspace;
import java.util.Random;

/**
 *
 * @author yabir
 */
public class Dice {
    private final float NHANGARSPROB;
    private final float NSHIELDSPROB;
    private final float NWEAPONSPROB;
    private final float FIRSTSHOTPROB;
    
    Random generator;
    
    Dice(){
        this.NHANGARSPROB = 0.25f;
        this.NSHIELDSPROB = 0.25f;
        this.NWEAPONSPROB = 0.33f;
        this.FIRSTSHOTPROB = 0.5f;
        
        this.generator = new Random();
    }
    
    GameCharacter firstShot(){
        //generate a random value (double) between 0 and 1
        double random = generator.nextDouble();
        
        if(random < FIRSTSHOTPROB )
            return GameCharacter.SPACESTATION;
        else
            return GameCharacter.ENEMYSTARSHIP;
    }
    
    
    /*
    *   Number of hangars for a spacial station when 
    *   it's created
    *   @return Integers 1 or 0
    */
    public int initWithNHangars(){
        
        if (generator.nextDouble() < NHANGARSPROB)
            return 0;
        else
            return 1;
    }
    
    /*
    *   Number of weapons for a spacial station when
    *   it's created
    *   @return Integer 1,2 or 3
    */
    public int initWithNWeapons(){
        double random = generator.nextDouble();
        int result;
        
        if(random < NWEAPONSPROB)
            result = 1;
        else if (random > NWEAPONSPROB && random < 2*NWEAPONSPROB)
            result = 2;
        else
            result = 3;
        
        return result;
    }
    
    /*
    *   Number of shield's power ups that a spacial 
    *   station will receive.
    *   @return Integer 1 or 0
    */
    int initWithNShields(){
        int result = 0;
        
        if(generator.nextDouble() > NSHIELDSPROB)
            result = 1;
        
        return result;
    }
    
    /*
    *   Player that will start the game
    *   @param nPlayers Number of players
    *   @return Integer in the range [0, nPlayers-1]
    */
    
    int whoStarts(int nPlayers){
        return generator.nextInt(nPlayers); 
    }
    
    
    /*
    *   Determinate if the space station will avoid a shot.
    *   Prob of avoiding the shot will be greater if the speed is nearest the
    *   potential speed.
    *   @param A float value between 0 and 1
    */
    
    boolean spaceStationMoves(float speed){
    
        double random = generator.nextDouble();
        boolean result = true;
        
        if(random > speed)
            result = false;
        
        return result;
        
    }
}
