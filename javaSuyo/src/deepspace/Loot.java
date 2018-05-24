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
    private boolean efficient;
    private boolean spaceCity;
    
    Loot(int sp, int wp, int sh, int hn, int medals){
        nSupplies=sp;
        nWeapons=wp;
        nShields=sh;
        nHangars=hn;
        nMedals=medals;
        efficient=false;
        spaceCity=false;
    }
    
    Loot(int sp, int wp, int sh, int hn, int medals, boolean ef, boolean city){
        this(sp,wp,sh,hn,medals);
        efficient=ef;
        spaceCity=city;
    }
    
    Loot(Loot l){
        this(l.nSupplies, l.nWeapons, l.nShields, l.nHangars, l.nMedals, l.efficient, l.spaceCity);
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
        return efficient;
    }
    
    public boolean spaceCity(){
        return spaceCity;
    }
    
    public LootToUI getUIversion(){
        return new LootToUI(this);
    }
}
