package deepspace;

public class Weapon implements Copyable<Weapon>, CombatElement{
    private String name;
    private WeaponType type;
    private int uses;
    
    Weapon(String name, WeaponType type, int uses){
        this.name = name;
        this.type = type;
        this.uses = uses;
    }
    
    Weapon(Weapon s){
        this.name = s.getName();
        this.type = s.getType();
        this.uses = s.getUses();
    }

    public String getName() {
        return name;
    }
    
    //This one doesn't appear in the project guide.
    public WeaponType getType() {
        return type;
    }
    
    @Override
    public int getUses() {
        return uses;
    }
    
    public float power(){
        return type.getPower();
    }
    
    @Override
    public float useIt(){
        if (uses > 0){
            uses--;
            return power();
        }else
            return 1.0f;
    }
    
    public WeaponToUI getUIversion(){
        return new WeaponToUI(this);
    }

    @Override
    public Weapon copy() {
        return new Weapon(this);
    }
}
