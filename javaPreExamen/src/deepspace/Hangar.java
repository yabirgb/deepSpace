/*
 * @author Yábir García Benchakhtir
 */
package deepspace;
import java.util.ArrayList;
        
public class Hangar{
    
    private int maxElements;
    private ArrayList<Weapon> weapons;
    private ArrayList<ShieldBooster> shieldBoosters;
    
    Hangar(int capacity){
        maxElements = capacity;
        weapons = new ArrayList<>();
        shieldBoosters = new ArrayList<>();
    }
    
    Hangar(Hangar h){
        maxElements = h.getMaxElements();
        weapons = new ArrayList<>();
        shieldBoosters = new ArrayList<>();
        
        for (Weapon w: h.getWeapons()){
            weapons.add(new Weapon(w));
        }
        
        for (ShieldBooster s: h.getShieldBoosters()){
            shieldBoosters.add(new ShieldBooster(s));
        }
    }

    HangarToUI getUIversion(){
	return new HangarToUI(this);
    }
    
    private boolean spaceAvailable(){
        return getMaxElements() - weapons.size() - shieldBoosters.size() > 0;
    }
    
    public boolean addWeapon(Weapon w){
        if(spaceAvailable()){
            weapons.add(w);
            return true;
        }
        
        return false;
    }
    
    public boolean addShieldBooster(ShieldBooster s){
        if(spaceAvailable()){
            shieldBoosters.add(s);
            return true;
        }
        
        return false;
    }
    
    public int getMaxElements(){
        return maxElements;
    }
    
    public ArrayList<ShieldBooster> getShieldBoosters(){
        return shieldBoosters;
    }
    
    public ArrayList<Weapon> getWeapons(){
        return weapons;
    }

    ShieldBooster removeShieldBooster( int s ){
	if (s >= 0 && s < shieldBoosters.size())
	    return shieldBoosters.remove(s);
        
        return null;
    }

    Weapon removeWeapon(int w){
	if (w >= 0 && w < weapons.size())
	    return weapons.remove(w);
        
        return null;
    }
    
}
