package deepspace;
import java.util.ArrayList;
import java.lang.Math;

class Damage{


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

    Damage(ArrayList<WeaponType> wl, int s){
	this(-1, s, wl);
    }

    DamageToUI getUIversion(){
	return new DamageToUI(this);
    }

    Damage(Damage d){
	this(d.getNWeapons(), d.getNShields(), d.getWeapons());
    }

    public Damage adjust(ArrayList<Weapon> wl,  ArrayList<ShieldBooster> sl){

	if (nWeapons == -1){
	    ArrayList<WeaponType> result = new ArrayList<WeaponType>();
	    ArrayList<Weapon> toRemove = new ArrayList<>(wl);

	    for (Weapon weapon: wl){
		if (arrayContainsType(toRemove, weapon.getType()) != -1){
		    result.add(weapon.getType());
		    toRemove.remove(weapon.getType());
		}
	    }
            
            return new Damage(result, Math.min(getNShields(), sl.size()));
	}
	else{
	    return new Damage(Math.min(getNWeapons(), wl.size() ), Math.min(getNShields(), sl.size() ));
	}
	
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

    public void discardWeapon(Weapon w){
	if (weapons != null){
	    weapons.remove(w.getType());
	}
	else if (getNWeapons() > 0){
	    nWeapons -= 1;
	}
    }

    public void discardShieldBooster(){
	if (getNShields() > 0){
	    nShields -= 1;
	}
    }

    public boolean hasNoEffect(){
	if (weapons != null){
	    return nShields == 0 && weapons.isEmpty();
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
