package termopackagemanager;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import termo.eos.Cubic;
import termo.eos.EquationOfStateFactory;

/**
 * FXML Controller class
 *
 * @author Chilpayate
 */
public class TermoPackageManagerController implements Initializable {

    @FXML ComboBox termoPackageCombo ;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        termoPackageCombo.getItems().clear();
        Cubic tst = EquationOfStateFactory.twoSimTassone();
        Cubic pr = EquationOfStateFactory.pengRobinsonBase();
        Cubic rks = EquationOfStateFactory.redlichKwongSoaveBase();
        Cubic vanDW = EquationOfStateFactory.vanDerWaals();
        
        termoPackageCombo.getItems().addAll(tst,pr,rks,vanDW);
       
    }    
}
