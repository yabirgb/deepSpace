/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package deepspace;

import java.util.ArrayList;
/**
 *
 * @author yabir
 */
public class SpaceCity extends SpaceStation{
    
    private SpaceStation base;
    private ArrayList<SpaceStation> rest;
    
    SpaceCity(SpaceStation b, ArrayList<SpaceStation> rest){
        super(b);
        this.rest = rest;
        this.base = b;
    }
    
    ArrayList<SpaceStation> getCollaborators(){
        return rest;
    }
    
    @Override
    public float fire(){
        float factor = super.fire();
        for(SpaceStation station: getCollaborators()){
            factor += station.fire();
        }
        
        return factor;
    }
    
    @Override
    public float protection(){
        float factor = super.protection();
        
        for(SpaceStation station: getCollaborators()){
            factor += station.protection();
        }
        
        return factor;
    }
    
    @Override
    public Transformation setLoot(Loot l){
        super.setLoot(l);
        return Transformation.NOTRANSFORM;
    }
    
    @Override
    public SpaceCityToUI getUIversion(){
        return new SpaceCityToUI(this);
    }
    
}
