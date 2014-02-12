
package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import termo.phase.Phase;
import termo.substance.PureSubstance;

/**
 * FXML
 * Controller
 * class
 *
 * @author
 * Hugo
 */
public class OnePhasePropertiesController implements Initializable {

    
    @FXML TextField temperatureTF;
    @FXML TextField pressureTF;
    
    
    @FXML RadioButton liquidRB;
    @FXML RadioButton vaporRB;
    @FXML ToggleGroup phaseGroup;
    
    /**
     * Initializes
     * the
     * controller
     * class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
	liquidRB.setUserData(Phase.LIQUID);
	vaporRB.setUserData(Phase.VAPOR);
		
	

    }  
    
    @FXML protected void calculate(ActionEvent event){
	Phase phase = (Phase)phaseGroup.getSelectedToggle().getUserData();
	PureSubstance substance = (PureSubstance)application.Eqfases2Copy.getSubstance();
	substance.setPhase(phase);
	
	double temperature = Double.valueOf(temperatureTF.getText());
	double pressure = Double.valueOf(pressureTF.getText());
	
	double fug = substance.calculateFugacity(temperature, pressure);
	double vol = substance.calculateMolarVolume(temperature,pressure);
	double compresibility = substance.calculateCompresibilityFactor(temperature, pressure);
	
	
	
	OnePhasePropertiesResult result = new OnePhasePropertiesResult();
	
	result.setEntropy(0);
	result.setEnthalpy(0);
	result.setGibbs(0);
	
	result.setTemperature(temperature);
	result.setPressure(pressure);
	result.setMolarVolume(vol);
	result.setPhase(phase);
	result.setZ(compresibility);
	
	
	Eqfases2Copy.setOnePhasePropertiesResult(result);
	
	Stage stage = (Stage)liquidRB.getScene().getWindow();
	
	
	EQStage resultStage = new EQStage("OnePhasePropertiesResult.fxml");
	resultStage.getStage().initOwner(stage);
	
	
	
	resultStage.run();
	
    }
}
