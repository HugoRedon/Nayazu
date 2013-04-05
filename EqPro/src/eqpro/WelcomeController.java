
package eqpro;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.text.Text;

/**
 *
 * @author Chilpayate
 */
public class WelcomeController implements Initializable {
    

@FXML
protected void openComponentManager(ActionEvent event)throws Exception{
    Parent root = FXMLLoader.load(getClass().getResource(EqPro.componentManagerFXML));
    EqPro.loadFxml(root);
}
@FXML
protected void openTermoPackageManager(ActionEvent event) throws IOException{
    Parent root = FXMLLoader.load(getClass().getResource(EqPro.termoPackageFXML));
    EqPro.loadFxml(root);
}

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
