package deepspace;

class EnemyStarShip implements Copyable<EnemyStarShip> {

    private float ammoPower;
    private float shieldPower;
    private String name;
    private Loot loot;
    private Damage damage;
    
    
    EnemyStarShip(String n, float a, float s, Loot l, Damage d){
	name = n;
	ammoPower = a;
	shieldPower = s;
	loot = new Loot(l.getNSupplies(), l.getNWeapons(), l.getNShields(), l.getNHangars(), l.getNMedals());
	damage = new Damage(d);
    }

    EnemyStarShip(EnemyStarShip e){
	this(e.getName(), e.getAmmoPower(), e.getShieldPower(), e.getLoot(), e.getDamage());
    }

    EnemyToUI getUIversion(){
	return new EnemyToUI(this);
    }

    public float fire(){
	return ammoPower;
    }
    
    public float getAmmoPower(){
	return ammoPower;
    }

    public Damage getDamage(){
	return damage;
    }

    public Loot getLoot(){
	return loot;
    }

    public String getName(){
	return name;
    }
    
    public float getShieldPower(){
	return shieldPower;
    }

    public float protection(){
	return shieldPower;
    }

    public ShotResult receiveShot(float shot){
	if (protection() < shot){
	    return ShotResult.DONOTRESIST;
	}
	else{
	    return ShotResult.RESIST;
	}
    }

    @Override
    public EnemyStarShip copy() {
        return new EnemyStarShip(this);
    }

}
