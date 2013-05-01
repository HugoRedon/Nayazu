package eqpro.windowcontrol;

import eqpro.EqPro;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Hugo Redon Rivera
 */
public class WindowControlsController implements Initializable{

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    @FXML protected void minimize(MouseEvent event){
        EqPro.minimize();
    }

    @FXML protected void close(MouseEvent event){
        EqPro.close();
    }

}
