
package eqpro;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;

/**
 *
 * @author Chilpayate
 */
public class WelcomeController implements Initializable {
    

    @FXML ImageView componentsImage;
    @FXML ImageView packageImage;
    @FXML ImageView simulationImage;
    

    
@FXML
protected void openComponentManager(ActionEvent event)throws Exception{
    EqPro.startComponentListManager();
}
@FXML
protected void openTermoPackageManager(ActionEvent event) throws IOException{
    EqPro.startTermoPackageManager();
}

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//          componentsImage.visibleProperty().bindBidirectional(UserProperties.getComponentListAsigned());
        
        //componentsImage.setVisible((boolean)UserProperties.getProperties().get(UserProperties.ARE_COMPONENTS_ASIGNED));
    }    
}
