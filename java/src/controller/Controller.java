/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package controller;

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

    public void mountWeapon(int option) {
        model.mountWeapon(option);
        view.updateView();
    }
    
    public void mountDiscardFromHangar(Operation operation, Element element, int option) {
        switch (element) {
            case WEAPON :
              if (operation == Operation.MOUNT)
                  model.mountWeapon(option);
              else
                  model.discardWeaponInHangar(option);
              break;
            case SHIELD :
              if (operation == Operation.MOUNT)
                  model.mountShieldBooster(option);
              else
                  model.discardShieldBoosterInHangar(option);
              break;
        }
        view.updateView();
    }
    
    public void discardMountedElements(Element element, int option) {
        switch (element) {
            case WEAPON :
              model.discardWeapon(option);
              break;
            case SHIELD :
              model.discardShieldBooster(option);
              break;
        }
        view.updateView();
        
    }

}
