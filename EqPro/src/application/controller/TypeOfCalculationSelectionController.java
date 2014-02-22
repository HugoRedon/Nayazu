
package application.controller;

import application.model.EQStage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
    @FXML RadioButton binaryDiagramRB;
    @FXML RadioButton LVERB;
    @FXML RadioButton LLERB;
    @FXML RadioButton polarMathiasRB;
    @FXML RadioButton phaseEnvelopeRB;
    
    @FXML private ToggleGroup group;
    
    private EQStage LVE = new EQStage("/application/LVE.fxml");
    private EQStage LLE = new EQStage("/application/LLE.fxml");
    private EQStage onePhaseProperties = new EQStage("/application/OnePhaseProperties.fxml");
    private EQStage polarMathias  = new EQStage("/application/polarMathias.fxml");
    private EQStage phaseEnvelope = new EQStage("/application/phaseEnvelope.fxml");	
    private EQStage binaryDiagram = new EQStage("/application/BinaryDiagram.fxml");
    
    
    /**
     * Initializes
     * the
     * controller
     * class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
	
	onePhasePropertiesRB.setUserData(onePhaseProperties);
	binaryDiagramRB.setUserData(binaryDiagram);
	LVERB.setUserData(LVE);
	LLERB.setUserData(LLE);
	polarMathiasRB.setUserData(polarMathias);
	phaseEnvelopeRB.setUserData(phaseEnvelope);
    }    
    
    
    @FXML void done(ActionEvent event){
	EQStage selectedStage = (EQStage)group.getSelectedToggle().getUserData();
	
	Stage stage = (Stage)root.getScene().getWindow();
	selectedStage.getStage().initOwner(stage);
	selectedStage.run();
	
	
    }

}


