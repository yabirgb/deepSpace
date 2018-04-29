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
    
    NumericDamage(int w, int s){
        super(w,s, null);
    }
    
    NumericDamage(NumericDamage d){
        this(d.nWeapons, d.nShields);
    }
    
    @Override
    public NumericDamage adjust(ArrayList<Weapon> wl,  ArrayList<ShieldBooster> sl){
        return new NumericDamage(Math.min(getNWeapons(), wl.size() ), Math.min(getNShields(), sl.size() ));
    }
    
    @Override
    public boolean hasNoEffect(){
        return nShields == 0 && nWeapons == 0;
    }
    
    @Override
    NumericDamageToUI getUIversion(){
        return new NumericDamageToUI(this);
    }
    
}
