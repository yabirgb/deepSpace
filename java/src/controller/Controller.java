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

    public GameState getState() {
        return model.getState();
    }

    public CombatResult combat() {
        CombatResult result = model.combat();
        view.updateView();
        return result;
    }

    public boolean haveAWinner() {
        return model.haveAWinner();
    }

    public void discardHangar() {
        model.discardHangar();
        view.updateView();
    }

    public boolean nextTurn() {
        boolean result = model.nextTurn();
        view.updateView();
        return result;
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
    
    public void discardMountedElements(ArrayList<Integer> selected) { 
        int WeaponSize = model.getUIversion().getCurrentStation().getHangar().getWeapons().size();
        
        ArrayList<Integer> reversed = new ArrayList<>(selected);
        Collections.reverse(reversed);
        for(Integer i: reversed){
            if(i < WeaponSize){
                model.discardWeapon(i);
            }else{
                model.discardShieldBooster(i-WeaponSize);
            }
        }
        view.updateView();
    }

}
