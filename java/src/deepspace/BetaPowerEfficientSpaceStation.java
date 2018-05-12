/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package deepspace;

/**
 *
 * @author yabir
 */
public class BetaPowerEfficientSpaceStation extends PowerEfficientSpaceStation{

    private static final float EXTRAEFFICIENCY = 1.2f;
    Dice dice;
    
    BetaPowerEfficientSpaceStation(SpaceStation s){
        super(s);
        dice = new Dice();
    }
    
    @Override
    public float fire(){
        float factor = 1.0f;
        if(dice.extraEfficiency())
            factor = EXTRAEFFICIENCY;
        
        return super.fire()*factor;
    }
    
    @Override
    public BetaPowerEfficientSpaceStationToUI getUIversion(){
        return new BetaPowerEfficientSpaceStationToUI(this);
    }
    
    
    
}
