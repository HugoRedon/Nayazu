/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import termo.phase.Phase;
import termo.substance.Substance;

/**
 * FXML
 * Controller
 * class
 *
 * @author
 * Hugo
 */
public class OnePhasePropertiesController implements Initializable {

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
//	Substance substance = application.Eqfases2Copy.getSubstance();
//	
//	
//	substance.calculateEnthalpy(temperature, pressure, volume);
	
	
    }
}
