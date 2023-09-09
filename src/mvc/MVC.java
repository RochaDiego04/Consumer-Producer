
package mvc;

import controller.Controller;
import view.MainView;


public class MVC {


    public static void main(String[] args) {
        MainView view = new MainView();
        
        Controller ctrl = new Controller(view);
        ctrl.start();
        view.setVisible(true);
    }
    
}
