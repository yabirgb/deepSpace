package deepspace;
import java.util.ArrayList;
import java.lang.Math;

public class Damage implement Copyable<Hangar>{


    private int nShields;
    private int nWeapons;
    private ArrayList<WeaponType> weapons;
    

    Damage(int w, int s, ArrayList<WeaponType> wl){
	nShields = s;
	nWeapons = w;
	
	if (wl != null){
	    weapons = new ArrayList<>(wl);
	}else{
	    weapons = null;
	}
    }
    
    Damage(int w, int s){
        this(w, s, null);
    }

    Damage(ArrayList<WeaponType> wl, s int){
	this(-1, s, wl);
    }

    DamageToUI getUIversion(){
	return new DamageToUI(this);
    }

    Damage(Damage d){
	this(d.getNWeapons(), d.getNShields(), d.getWeapons());
    }

    private Damage adjust(ArrayList<Weapon> wl,  ArrayList<ShieldBooster> sl){

	if (nWeapons == -1){
	    result = new ArrayList<WeaponType>;
	    toRemove = new ArrayList<>(wl);

	    for (WeaponType weapon: wl){
		if (arrayContainsType(toRemove, weapon)){
		    result.add(weapon.getType());
		    toRemove.remove(weapon.getType());
		}
	    }
	}
	else{
	    new Damage(Math.min(getNWeapons(), wl.size() ), Math.min(getNShields(), sl.size() ))
	}
	
    }

    private arrayContainsType(ArrayList<Weapon> w, WeaponType t){
	boolean done = false;
	int position = -1;

	for (int i =0; i < w.size() && !done ; i++){
	    if (w.get(i).getType() == t){
		position = i;
		found = true;
	    }
	}

	return position;
	
    }

    public void discardWeapon(Weapon w){
	if (weapons != nil){
	    weapons.remove(w.getYpe());
	}
	else if (getNWeapons() > 0){
	    weapons -= 1;
	}
    }

    public void discardShieldBooster(){
	if (getNShields() > 0){
	    nShields -= 1;
	}
    }

    public hasNoEffect(){
	if (weapons != nill){
	    return nShields == 0 && weapons.size() == 0;
	}
	else{
	    return nShields == 0 && nWeapons == 0;
	}
    }

    public int getNShields(){
	return nShields;
    }

    public int getNWeapons(){
	return nWeapons;
    }

    public ArrayList<WeaponType> getWeapons(){
	return weapons;
    }
    
}
