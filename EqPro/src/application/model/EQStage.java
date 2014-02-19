/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application.model;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author
 * Hugo
 */
public class EQStage {
    private String fxml ;
    private Stage stage;

    public EQStage(String fxml) {
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
