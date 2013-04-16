package termopackagemanager.alphaexpression;

import componentmanager.componentlist.ComponentListModelView;
import eqpro.UserProperties;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import termo.component.Component;

/**
 * FXML Controller class
 *
 * @author Chilpayate
 */
public class AlphaExpressionManagerController implements Initializable {

    
    @FXML ToggleButton soaveAlphaButton;
    @FXML ToggleButton pengRobAlphaButton;
    @FXML ToggleButton generalTwuAlphaButton;
    @FXML ToggleButton mathiasAlphaButton;
    @FXML ToggleButton twuAlphaToggle;
    @FXML ToggleButton svAlphaToggle;
    @FXML ToggleButton MathiasCopemanAlphaToggle;
    @FXML ToggleButton GCEOSAlphaToggle;
    @FXML ToggleButton commonAlphaToggle;
    @FXML ToggleButton unitAlphaToggle;
    
    @FXML ToggleGroup group;
    
    @FXML ListView<Component> componentsList;
    
    @FXML ImageView alphaImageView;
    @FXML AnchorPane alphaPane;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            ComponentListModelView manager = UserProperties.getComponentListModelView();
            componentsList.setItems(manager.getComponentsObservableList());
        
        
    }    
    public static final String COMMON_ALPHA_FORM = "CommonsAlphaForm.fxml";
    
    @FXML protected void commonAlphaSelected() throws IOException{
        AnchorPane parent = (AnchorPane)FXMLLoader.load(getClass().getResource(COMMON_ALPHA_FORM));
        alphaPane.getChildren().add(parent);
    }
}
