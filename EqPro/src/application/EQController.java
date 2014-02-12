
package application;

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
	//stage.getOnCloseRequest().handle(null); 
	stage.close();
    }
	public abstract void done();
	@FXML public void done(ActionEvent event){
	    done();
	    close(event);
	}
	
}
