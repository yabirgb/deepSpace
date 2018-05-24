/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package controller;

import java.util.ArrayList;
import java.util.Collections;

import View.View;

import deepspace.CombatResult;
import deepspace.GameState;
import deepspace.GameUniverse;
import deepspace.GameUniverseToUI;
import deepspace.HangarToUI;

/**
 *
 * @author yabir
 */
public class Controller{
    
    static public enum Element {
        WEAPON ("Arma"), 
        SHIELD ("Escudo"), 
        HANGAR ("Hangar");

        final public String text;

        Element (String t) { text = t; }
    };

    static public enum Operation {
        MOUNT ("Montar"), 
        DISCARD ("Descartar");

        final public String text;

        Operation (String t) { text = t; }
    };

    private GameUniverse model;
    private View view;
    
    public Controller(GameUniverse aModel, View aView) {
        model = aModel;
        view = aView;
        view.setController(this);
    }
    
    public void start() {
        model.init(view.getNames());
        view.updateView();
        view.showView();
    }

    public GameUniverseToUI getUIversion() {
        return model.getUIversion();
    }
    
    public void updateView(){
        view.updateView();
    }

    public GameState getState() {
        return model.getState();
    }

    public CombatResult combat() {
        CombatResult combatResult = model.combat();
        view.displayMessage(combatResult);
        view.updateView();
        return combatResult;
    }

    public boolean haveAWinner() {
        return model.haveAWinner();
    }

    public void discardHangar() {
        model.discardHangar();
        view.updateView();
    }

    public boolean nextTurn() {
        boolean next = model.nextTurn();
        if(next)
            view.updateView();
        else
            view.displayCleanDamage();
        return next;
    }
    
    public void mountFromHangar(ArrayList<Integer> selected) {
        int WeaponSize = model.getUIversion().getCurrentStation().getHangar().getWeapons().size();
        
        //Usamos que en el hangar estan primero las armas y despues los 
        //escudos. Además usamos el vector al reves para no modificar
        //los índices de los elementos.
        ArrayList<Integer> reversed = new ArrayList<>(selected);
        Collections.reverse(reversed);
        for(Integer i: reversed){
            if(i < WeaponSize){
                model.mountWeapon(i);
            }else{
                model.mountShieldBooster(i-WeaponSize);
            }
        }
        
        view.updateView();
    }
    
    public void discardInHangar(ArrayList<Integer> selected) { 
        HangarToUI h = model.getUIversion().getCurrentStation().getHangar();
        if(h != null){
            int w = h.getWeapons().size();
            int pos;

            for(int i = selected.size()-1; i>=0; i--){
                pos = selected.get(i);
                if(pos < w)
                    model.discardWeaponInHangar(pos);
                else   
                    model.discardShieldBoosterInHangar(pos-w);
            }
        }
    }
    
    public void discardWeapons(ArrayList<Integer> selected){
        
        for(int i = selected.size()-1; i>=0; i--)
            model.discardWeapon(selected.get(i)); 
    }
    
    public void discardShieldBoosters(ArrayList<Integer> selected){
        
        for(int i = selected.size()-1; i>=0; i--)
            model.discardShieldBooster(selected.get(i)); 
    }
    
    public void revive(){
        System.out.println("reviviendo");
        model.reanimateCurrentStation();
        view.updateView();
    }
    
    public boolean canRevive(){
        return model.canRevive();
    }
   

}
