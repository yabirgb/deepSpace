package deepspace;

import java.util.ArrayList;

class SpaceStation{

    private static final float MAXFUEL = 100;
    private static final double SHIELDLOSSPERUNITSHOT = 0.1;

    private float ammoPower;
    private float fuelUnits;
    private String name;
    private int nMedals;
    private float shieldPower;

    private Damage pendingDamage;
    private ArrayList<Weapon> weapons;
    private ArrayList<ShieldBooster> shieldBoosters;
    private Hangar hangar;
    private SuppliesPackage supplies;

    SpaceStation(String n, SuppliesPackage s){
	name = n;
	supplies = s;
	ammoPower = s.getAmmoPower();
	shieldPower = s.getShieldPower();
	assignFuelValue(s.getFuelUnits());

	nMedals = 0;
	hangar = null;
	weapons = new ArrayList<>();
	shieldBoosters = new ArrayList<>();
	pendingDamage = null;
    }

    private void assignFuelValue(float f){
	if (f < MAXFUEL)
	    fuelUnits = f;
	else
	    fuelUnits = MAXFUEL;
    }

    private void cleanPendingDamage(){
	if (pendingDamage != null && pendingDamage.hasNoEffect())
	    pendingDamage = null;
    }


    // =================
    // Discard things
    // =================
    
    public void discardHangar(){
	hangar = null;
    }

    public void discardShieldBooster(int i){
        if (i >= 0 && i < shieldBoosters.size()){
	    shieldBoosters.remove(i);
	    if (pendingDamage != null){
		pendingDamage.discardShieldBooster();
		cleanPendingDamage();
	    }
	    
	}
    }

    public void discardShieldBoosterInHangar(int i){
	if (hangar != null)
	    hangar.removeShieldBooster(i);
    }

    public void discardWeapon(int i){
	if (i >= 0 && i < weapons.size()){
	    Weapon w = weapons.remove(i);
	    if (pendingDamage != null){
		pendingDamage.discardWeapon(w);
		cleanPendingDamage();
	    }
	    
	}
    }

    public void discardWeaponInHangar(int i){
	if (hangar != null)
	    hangar.removeWeapon(i);
    }

    public float fire(){
	float factor = 1;
	for(Weapon w: weapons){
	    factor *= w.useIt(); 
	}

	return factor*ammoPower;
	
    }

    // =================
    // getters
    // =================

    

    
    public float getSpeed(){
        return fuelUnits/MAXFUEL;
    }

    public void mountShieldBooster(int i){
	if (hangar != null && i >= 0 && i < hangar.getShieldBoosters().size()){
	    shieldBoosters.add(new ShieldBooster(hangar.removeShieldBooster(i)));
	}
    }

    public void mountWeapon(int i){
	if (hangar != null && i >= 0 && i < hangar.getWeapons().size()){
	    weapons.add(new Weapon(hangar.removeWeapon(i)));
	}
    }

    public void move(){
        if (fuelUnits - getSpeed() > 0)
            fuelUnits -= getSpeed();
    }
    
    public float protection(){
        float factor = 1;
        
        for(ShieldBooster s: shieldBoosters){
	    factor *= s.useIt(); 
	}

	return factor*shieldPower;
    }
    
    
    // =================
    // Receive things
    // =================

    public void receiveHangar(Hangar h){
        if (hangar == null)
            hangar = new Hangar(h);
    }
    
    public boolean receiveShieldBoosters(ShieldBooster s){
        if (hangar != null)
            return hangar.addShieldBooster(s);
        else
            return false;
    }
    
    public ShotResult receiveShot(float shot){
        float myProtection = protection();
        
        if (myProtection >= shot){
            shieldPower -= SHIELDLOSSPERUNITSHOT*shot;
            shieldPower = Math.max(0, shieldPower);
            return ShotResult.RESIST;
        }
        else{
            shieldPower = 0;
            return ShotResult.DONOTRESIST;
        }        
    }
    
    public void receiveSupplies(SuppliesPackage s){
        fuelUnits += s.getFuelUnits();
        if (fuelUnits > MAXFUEL)
            fuelUnits = MAXFUEL;
        
        shieldPower += s.getShieldPower();
        ammoPower += s.getAmmoPower();
    }
    
    public boolean receiveWeapon(Weapon w){
        if (hangar != null)
            return hangar.addWeapon(w);
        else
            return false;
    }
    
    public void setPendingDamage(Damage d){
        pendingDamage = d.adjust(weapons, shieldBoosters);
    }
    
    public void setLoot(Loot loot){
        CardDealer dealer = CardDealer.getInstance();
        
        int h = loot.getNHangars();
            
        if (h > 0){
        
        }
        //Complete
    }
    
    
    
}