/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

/**
 *  Shield boosters that a space station can have
 *  @author yabir
 */
public class ShieldBooster implements Copyable<ShieldBooster> {
    private String name;
    private float boost;
    private int uses;
    
    /*
    *   Class constructor
    *   @param name Name of the booster
    *   @param boost Float representing the boost amount
    *   @param uses Int representing the left amount of uses
    */
    ShieldBooster(String name, float boost, int uses){
        this.name = name;
        this.boost = boost;
        this.uses = uses;
    }
    
    /*
    *   Copy constructor. Copy attributes from another object
    *   @param ShieldBooster The other object to copy
    */
    ShieldBooster(ShieldBooster s){
        this.name = s.getName();
        this.boost = s.getBoost();
        this.uses = s.getUses();
    }
    
    /*
    *   Get method
    *   @return String with the name of the boost
    */
    public String getName() {
        return name;
    }
    
    /*
    *   Amount of boost
    *   @return float with the amount of boost
    */
    public float getBoost() {
        return boost;
    }
    
    /*
    *   Uses left in the booster
    *   @return int with the number of uses left
    */
    public int getUses() {
        return uses;
    }
    
    public float useIt(){
        if (uses > 0){
            uses--;
            return getBoost();
        }else
            return 1.0f;
    }
    
    public ShieldToUI getUIversion(){
        return new ShieldToUI(this);
    }
    
    @Override
    public ShieldBooster copy(){
        return new ShieldBooster(this);
    }
}
