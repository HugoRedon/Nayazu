package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import termo.substance.Substance;

/**
 * FXML
 * Controller
 * class
 *
 * @author
 * Hugo
 */
public class LVEquilibriaCalculationController implements Initializable {

    @FXML ToggleGroup calculationGroup ;
    @FXML RadioButton bubbleTempRB;
    @FXML TextField pressureTF;
    
    /**
     * Initializes
     * the
     * controller
     * class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
	
	
	
    }    
    @FXML protected void calculate(ActionEvent event){
	System.out.println("calculando temperatura de burbuja");
	Double pressure = Double.valueOf(pressureTF.getText());
	Substance substance = Eqfases2Copy.getSubstance();
	
	double bubbletemperature = substance.bubbleTemperature(pressure).getTemperature();
	System.out.println(bubbletemperature);
    }
}
