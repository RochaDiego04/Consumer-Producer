
package mvc;

import controller.Controller;
import models.Buffer;
import view.MainView;


public class MVC {


    public static void main(String[] args) {
        MainView view = new MainView();
        Buffer buffer = new Buffer(5);

        Controller ctrl = new Controller(view, buffer);
        ctrl.start();
        view.setVisible(true);
    }
    
}
