/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.IOException;
import java.net.URL;
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

    
  
    
    /**
     * Initializes
     * the
     * controller
     * class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

	
    }    
    
    @FXML protected void runComponentSelection(ActionEvent event) throws IOException{
	Stage stage = new Stage();
	Parent parent = FXMLLoader.load(getClass().getResource("componentSelection.fxml"));
	
	Scene scene = new Scene(parent);
	Eqfases2Copy.setInerWindow(stage);
	stage.setScene(scene);
	stage.show();
	
	
	
    }
}
