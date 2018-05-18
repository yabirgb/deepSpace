package deepspace;
import java.util.ArrayList;
import java.lang.Math;

abstract public class Damage{


    private int nShields;
    protected ArrayList<WeaponType> weapons;
    

    Damage(int s){
	nShields = s;
    }
    
    abstract DamageToUI getUIversion();


    abstract public Damage adjust(ArrayList<Weapon> wl,  ArrayList<ShieldBooster> sl);
    abstract public void discardWeapon(Weapon w);
    abstract Damage copy();
    
    public void discardShieldBooster(){
	if (getNShields() > 0){
	    nShields -= 1;
	}
    }

    public boolean hasNoEffect(){
        return nShields == 0;
    };

    public int getNShields(){
	return nShields;
    }


    public ArrayList<WeaponType> getWeapons(){
	return weapons;
    }
    
    public String toString() {
        return "weapons = " + weapons + "\n" +super.toString();
    }

}
