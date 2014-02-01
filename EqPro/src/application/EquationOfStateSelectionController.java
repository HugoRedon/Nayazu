
package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javax.swing.ButtonGroup;

/**
 * FXML
 * Controller
 * class
 *
 * @author
 * Hugo
 */
public class EquationOfStateSelectionController implements Initializable {

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
}
