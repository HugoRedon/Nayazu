/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

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
    
    private EQStage componentSelection = new EQStage("componentSelection.fxml");
    private EQStage compositionSelection = new EQStage("CompositionSelection.fxml");
    private EQStage equationOfStateSelection = new EQStage("EquationOfStateSelection.fxml");
    private EQStage typeOfCalculationSelection = new EQStage("TypeOfCalculationSelection.fxml");
    
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


class EQStage {
    private String fxml ;
    private Stage stage;

    EQStage(String fxml) {
	this.fxml = fxml;
	try {
	    stage = new Stage();
	    Parent parent = FXMLLoader.load(getClass().getResource(fxml));
	    Scene scene = new Scene(parent);
	    Eqfases2Copy.setInerWindow(stage);
	    stage.setScene(scene);
	} catch (IOException ex) {
	    System.out.println("Error al cargar el archivo fxml " + fxml);
	    
	}
    }
    
    
     public void run() {
	
	    stage.show();
	
    }
     
   
//     public void initOwner(Stage owner){
//	 
//	 stage.initOwner(owner);
//     }

    /**
     * @return the fxml
     */
    public String getFxml() {
	return fxml;
    }

    /**
     * @param fxml the fxml to set
     */
    public void setFxml(String fxml) {
	this.fxml = fxml;
    }

    /**
     * @return the stage
     */
    public Stage getStage() {
	return stage;
    }

    /**
     * @param stage the stage to set
     */
    public void setStage(Stage stage) {
	this.stage = stage;
    }
}