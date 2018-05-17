/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package deepspace.main;

import View.GUI.MainWindow;
import View.View;
import deepspace.GameUniverse;
import controller.Controller;

/**
 *
 * @author yabir
 */

public class DeepSpaceGraphic {

    
    public static void main(String[] args) {
        GameUniverse model = new GameUniverse();
        
        // Puedes ejecutar la aplicación con una u otra vista 
        // según que clase de vista se instancie
        View view = new MainWindow();
//        View view = new MainWindow_v2();
        Controller controller = new Controller(model,view);
        controller.start();
    }
    
}
