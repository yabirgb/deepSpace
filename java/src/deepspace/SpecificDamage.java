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
public class SpecificDamage extends Damage{
    
    SpecificDamage(ArrayList<WeaponType> wl, int s){
	super(-1, s, wl);
    }
    
    SpecificDamage(SpecificDamage d){
        this(d.weapons, d.nShields);
    }
    
    private int arrayContainsType(ArrayList<Weapon> w, WeaponType t){
	boolean done = false;
	int position = -1;

	for (int i =0; i < w.size() && !done ; i++){
	    if (w.get(i).getType() == t){
		position = i;
		done = true;
	    }
	}

	return position;
	
    }
    
    @Override
    public SpecificDamage adjust(ArrayList<Weapon> wl,  ArrayList<ShieldBooster> sl){
    
        ArrayList<WeaponType> result = new ArrayList<WeaponType>();
        ArrayList<Weapon> toRemove = new ArrayList<>(wl);

        for (Weapon weapon: wl){
            if (arrayContainsType(toRemove, weapon.getType()) != -1){
                result.add(weapon.getType());
                toRemove.remove(weapon.getType());
            }
        }
            
        return new SpecificDamage(result, Math.min(getNShields(), sl.size()));

    }
    
    @Override
    public boolean hasNoEffect(){
	return nShields == 0 && weapons.isEmpty();

    }
    
    @Override
    SpecificDamageToUI getUIversion(){
        return new SpecificDamageToUI(this);
    }
    
    @Override
    public void discardWeapon(Weapon w){
        weapons.remove(w.getType());
    }
    
}
