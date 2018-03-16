package deepspace;

public class Weapon {
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
    private WeaponType getType() {
        return type;
    }

    public int getUses() {
        return uses;
    }
    
    public float getPower(){
        return type.getPower();
    }
    
    public float useIt(){
        if (uses > 0){
            uses--;
            return getPower();
        }else
            return 1.0f;
    }
}
