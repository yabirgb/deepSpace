package deepspace;
import java.util.ArrayList;
import java.lang.Math;

abstract public class Damage{


    protected int nShields;
    protected int nWeapons;
    protected ArrayList<WeaponType> weapons;
    

    Damage(int w, int s, ArrayList<WeaponType> wl){
	nShields = s;
	nWeapons = w;
	
	if (wl != null){
	    weapons = new ArrayList<>(wl);
	}else{
	    weapons = null;
	}
    }
    
    abstract DamageToUI getUIversion();

    Damage(Damage d){
	this(d.getNWeapons(), d.getNShields(), d.getWeapons());
    }

    abstract public Damage adjust(ArrayList<Weapon> wl,  ArrayList<ShieldBooster> sl);
    abstract public void discardWeapon(Weapon w);
    
    public void discardShieldBooster(){
	if (getNShields() > 0){
	    nShields -= 1;
	}
    }

    abstract public boolean hasNoEffect();

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
