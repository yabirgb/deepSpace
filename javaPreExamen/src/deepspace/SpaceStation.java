package deepspace;

import java.util.ArrayList;

class SpaceStation implements SpaceFighter{

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
    
    private int seeEnemyCounter = 2;
    private boolean canCheat = true;
    
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
    
    SpaceStation(SpaceStation s){
        this(s.name, new SuppliesPackage(s.ammoPower, s.fuelUnits, s.shieldPower));
        weapons = new ArrayList(s.weapons);
        shieldBoosters = new ArrayList(s.shieldBoosters);

        hangar=null;
        if (s.hangar != null)
            hangar = new Hangar(s.hangar);
        
        pendingDamage = null;
        if(s.pendingDamage != null)
            pendingDamage = s.pendingDamage.copy();
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
    
    public void cleanUpMountedItems(){
        weapons.removeIf(x->x.getUses() <= 0);
        shieldBoosters.removeIf(x->x.getUses() <= 0);
    }

    // =================
    // Discard things
    // =================
    
    public float getShieldPower(){
        return shieldPower;
    }
    
    public int getNMedals(){
        return nMedals;
    }
    
    public SpaceStationToUI getUIversion(){
        return new SpaceStationToUI(this);
    }
    
    public String getName(){
        return name;
    }
    
    public Hangar getHangar(){
        return hangar;
    }
    
    public float getFuelUnits(){
        return fuelUnits;
    }
    
    public float getAmmoPower(){
        return ammoPower;
    }
    
    public Damage getPendingDamage(){
        return pendingDamage;
    }
    
    public ArrayList<Weapon> getWeapons(){
        return weapons;
    }
    
    public ArrayList<ShieldBooster> getShieldBoosters(){
        return shieldBoosters;
    }
    
    
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
    
    @Override
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
    
    @Override
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
    
    public boolean receiveShieldBooster(ShieldBooster s){
        if (hangar != null)
            return hangar.addShieldBooster(s);
        else
            return false;
    }
    
    @Override
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
    
    public Transformation setLoot(Loot loot){
        CardDealer dealer = CardDealer.getInstance();
                    
        if (loot.getNHangars() > 0){
            receiveHangar(dealer.nextHangar());
        }
        
        for(int i = 0; i < loot.getNSupplies(); i++){
            receiveSupplies(dealer.nextSuppliesPackage());
        }
        
        for(int i = 0; i < loot.getNShields(); i++){
            receiveShieldBooster(dealer.nextShieldBooster());
        }
        
        for(int i = 0; i < loot.getNWeapons(); i++){
            receiveWeapon(dealer.nextWeapon());
        }
        
        nMedals += loot.getNMedals(); 
        
        Transformation t = Transformation.NOTRANSFORM;
        
        if (loot.getEfficient()){
            t = Transformation.GETEFFICIENT;
        }else if(loot.spaceCity()){
            t =  Transformation.SPACECITY;
        }
        
        return t;
    }
    
    public void setPendingDamage(Damage d){
        pendingDamage = d.adjust(weapons, shieldBoosters);
    }
    
    public boolean validState(){
        return pendingDamage == null || pendingDamage.hasNoEffect();
    }
    
    public boolean canSeeEnemy(){
        return seeEnemyCounter > 0;
    }
    
    public void seeEnemy(){
        seeEnemyCounter -= 1;
    }
    
    public int timesLeft(){
        return seeEnemyCounter;
    }
    
    public boolean canCheat(){
        return canCheat;
    }
    
    public boolean cheat(){
        if (canCheat){
            canCheat = false;
        }
        
        return !canCheat;
    }
    
}
