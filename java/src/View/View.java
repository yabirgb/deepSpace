/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package View;

import deepspace.GameUniverse;
import controller.ControllerText;
import java.util.ArrayList;
/**
 *
 * @author yabir
 */
public interface View {
    public void setController(ControllerText unControlador);
    public void updateView();
    public void showView();
    public ArrayList<String> getNames();
}
