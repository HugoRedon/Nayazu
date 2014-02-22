
package application.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 *
 * @author
 * Hugo
 */
public abstract class EQController {
    public void close(ActionEvent event){
	Node node = (Node)event.getSource();
	Stage stage =(Stage) node.getScene().getWindow();
	stage.close();
    }
	public abstract void done();
	public abstract void cancel();
	@FXML public void done(ActionEvent event){
	    done();
	    close(event);
	}
	@FXML public void cancel(ActionEvent event){
	    cancel();
	    close(event);
	}
	
}
