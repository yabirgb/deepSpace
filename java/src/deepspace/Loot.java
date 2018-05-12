/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deepspace;

/**
 *
 * @author yabir
 */
public class Loot {
    private int nSupplies;
    private int nWeapons;
    private int nShields;
    private int nHangars;
    private int nMedals;
    private boolean getEfficient;
    private boolean spaceCity;
    
    Loot(int nSupplies, int nWeapons, int nShields, int nHangars, int nMedals){
        this.nSupplies = nSupplies;
        this.nWeapons = nWeapons;
        this.nShields = nShields;
        this.nHangars = nHangars;
        this.nMedals = nMedals;
        this.getEfficient = false;
        this.spaceCity = false;
    }
    
    Loot(int nSupplies, int nWeapons, int nShields, int nHangars, int nMedals, boolean ef, boolean city){
        this.nSupplies = nSupplies;
        this.nWeapons = nWeapons;
        this.nShields = nShields;
        this.nHangars = nHangars;
        this.nMedals = nMedals;
        this.getEfficient = ef;
        this.spaceCity = city;
    }
    
    public int getNSupplies(){
        return nSupplies;
    }
    
    public int getNWeapons(){
        return nWeapons;
    }
    
    public int getNShields(){
        return nShields;
    }
    
    public int getNHangars(){
        return nHangars;
    }
    
    public int getNMedals(){
        return nMedals;
    }
    
    public boolean getEfficient(){
        return getEfficient;
    }
    
    public boolean spaceCity(){
        return spaceCity;
    }
    
    public LootToUI getUIversion(){
        return new LootToUI(this);
    }
}
