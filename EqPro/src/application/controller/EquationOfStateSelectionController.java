
package application.controller;

import application.model.Eqfases2Copy;
import application.controller.EQController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import termo.eos.EquationOfStateFactory;
import termo.eos.alpha.AlphaFactory;
import termo.eos.mixingRule.VDWMixingRule;

/**
 * FXML
 * Controller
 * class
 *
 * @author
 * Hugo
 */
public class EquationOfStateSelectionController  extends EQController implements Initializable {

    @FXML private RadioButton rksmRB;
    @FXML private RadioButton prsvRB;
    
    @FXML private ToggleGroup equationTG;
    
    @FXML private RadioButton vdw1RB;
    @FXML private RadioButton vdw2RB;
    @FXML private RadioButton mkpRB;
    
    @FXML private ToggleGroup mixingRuleTG;
    
    
    /**
     * Initializes
     * the
     * controller
     * class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
	// TODO
	
	
    }    


    @Override
    public void done() {
	System.out.println("ecuacion de estado y alpha seleccionados");
	if(equationTG.getSelectedToggle().equals(prsvRB)){
	    Eqfases2Copy.setEos(EquationOfStateFactory.pengRobinsonBase());
	    Eqfases2Copy.setAlpha(AlphaFactory.getStryjekAndVeraExpression());
	}if(mixingRuleTG.getSelectedToggle().equals(vdw1RB)){
	    Eqfases2Copy.setMixingRule(new VDWMixingRule());
	}
    }
}
