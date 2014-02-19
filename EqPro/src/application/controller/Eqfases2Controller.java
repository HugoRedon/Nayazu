/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controller;

import application.model.EQStage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML
 * Controller
 * class
 *
 * @author
 * Hugo
 */
public class Eqfases2Controller implements Initializable {
    
    private EQStage componentSelection = new EQStage("/application/componentSelection.fxml");
    private EQStage compositionSelection = new EQStage("/application/CompositionSelection.fxml");
    private EQStage equationOfStateSelection = new EQStage("/application/EquationOfStateSelection.fxml");
    private EQStage typeOfCalculationSelection = new EQStage("/application/TypeOfCalculationSelection.fxml");
    
    private ArrayList<Stage> stages= new ArrayList();
    
    /**
     * Initializes
     * the
     * controller
     * class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

	
    }    
    
    @FXML protected void runComponentSelection(ActionEvent event) {
	componentSelection.run();
    }
    @FXML protected void runCompositionSelection(ActionEvent event) {
	compositionSelection.run() ;
    }
    @FXML protected void runEquationOfStateSelection(ActionEvent event) {
	equationOfStateSelection.run();
    }
    @FXML protected void runTypeOfCalculationSelection(ActionEvent event) {
	typeOfCalculationSelection.run();
    }
}

