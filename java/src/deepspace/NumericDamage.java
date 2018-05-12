/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package deepspace;
import java.util.ArrayList;
/**
 *
 * @author yabir
 */
public class NumericDamage extends Damage{
    private int nWeapons;
    
    NumericDamage(int w, int s){
        super(s);
        nWeapons = w;
    }
    
    NumericDamage(NumericDamage d){
        this(d.getNWeapons(), d.getNShields());
    }
    
    public int getNWeapons(){
	return nWeapons;
    }
    
    @Override
    public NumericDamage adjust(ArrayList<Weapon> wl,  ArrayList<ShieldBooster> sl){
        return new NumericDamage(Math.min(getNWeapons(), wl.size() ), Math.min(getNShields(), sl.size() ));
    }
    

    
    @Override
    public boolean hasNoEffect(){
        return super.hasNoEffect() && nWeapons == 0;
    }
    
    @Override
    NumericDamageToUI getUIversion(){
        return new NumericDamageToUI(this);
    }
    
    @Override
    public void discardWeapon(Weapon w){
        nWeapons -= 1;
    }
    
    @Override 
    NumericDamage copy(){
        return new NumericDamage(this);
    }
    
}
