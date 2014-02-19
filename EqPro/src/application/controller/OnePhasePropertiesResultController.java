package application.controller;

import application.model.Eqfases2Copy;
import application.model.OnePhasePropertiesResult;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML
 * Controller
 * class
 *
 * @author
 * Hugo
 */
public class OnePhasePropertiesResultController implements Initializable {
    @FXML Label temperatureL;
    @FXML Label pressureL;
    @FXML Label phaseL;
    
    @FXML Label zL;
    @FXML Label enthalpyL;
    @FXML Label entropyL;
    @FXML Label gibbsL;
    @FXML Label molarVolumeL;
    
    /**
     * Initializes
     * the
     * controller
     * class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
	OnePhasePropertiesResult res = Eqfases2Copy.getOnePhasePropertiesResult();
	temperatureL.setText(res.getTemperature().toString());
	pressureL.setText(res.getPressure().toString());
	phaseL.setText(res.getPhase().toString());
	zL.setText(res.getZ().toString());
	enthalpyL.setText(res.getEnthalpy().toString());
	entropyL.setText(res.getEntropy().toString());
	gibbsL.setText(res.getGibbs().toString());
	molarVolumeL.setText(res.getMolarVolume().toString());
	
	
    }    
}
