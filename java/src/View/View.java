/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package View;

import deepspace.GameUniverse;
import controller.Controller;
import java.util.ArrayList;
/**
 *
 * @author yabir
 */
public interface View {
    public void setController(Controller unControlador);
    public void updateView();
    public void showView();
    public ArrayList<String> getNames();
    public boolean confirmExitMessage();
}
