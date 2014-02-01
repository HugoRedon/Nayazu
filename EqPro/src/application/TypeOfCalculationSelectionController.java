
package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML
 * Controller
 * class
 *
 * @author
 * Hugo
 */
public class TypeOfCalculationSelectionController implements Initializable {
    @FXML Parent root;
    @FXML RadioButton onePhasePropertiesRB;	
    
    private EQStage onePhaeProperties = new EQStage("OnePhaseProperties.fxml");
    
    @FXML private ToggleGroup group;
    /**
     * Initializes
     * the
     * controller
     * class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
	// TODO
	onePhasePropertiesRB.setUserData(onePhaeProperties);
    }    
    
    
    @FXML void done(){
	EQStage selectedStage = (EQStage)group.getSelectedToggle().getUserData();
	
	Stage stage = (Stage)root.getScene().getWindow();
	selectedStage.getStage().initOwner(stage);
	selectedStage.run();
    }

}


