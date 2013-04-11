package termopackagemanager;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import termo.eos.Cubic;
import termo.eos.EquationOfStateFactory;
import termo.eos.alpha.Alpha;
import termo.eos.alpha.AlphaFactory;

/**
 * FXML Controller class
 *
 * @author Chilpayate
 */
public class TermoPackageManagerController implements Initializable {

    
    @FXML ToggleButton tstButton;
    @FXML ToggleButton prButton;
    @FXML ToggleButton rksButton;
    @FXML ToggleButton vdwButton;
    @FXML ToggleButton customEosToggle;

    
    
    @FXML ToggleGroup group;
    
    @FXML TextField uField;
    @FXML TextField wField;
    @FXML TextField omega_aField;
    @FXML TextField omega_bField;
    
    
    HashMap<String,Cubic> eos ;
        
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        termoPackageCombo.getItems().clear();
        final Cubic  tst = EquationOfStateFactory.twoSimTassone();
        final Cubic pr = EquationOfStateFactory.pengRobinsonBase();
        final Cubic rks = EquationOfStateFactory.redlichKwongSoaveBase();
        final Cubic vanDW = EquationOfStateFactory.vanDerWaals();
        
        Alpha soave = AlphaFactory.getSoaveExpression();
        Alpha pengRob = AlphaFactory.getPengAndRobinsonExpression();
        Alpha generalTwu = AlphaFactory.getGeneralTwuEquation();
        Alpha mathias = AlphaFactory.getMathiasExpression();
        Alpha twu = AlphaFactory.getTwuExpression();
        Alpha sv = AlphaFactory.getStryjekAndVeraExpression();
        
        
        
        tstButton.setText(tst.getName());
        prButton.setText(pr.getName());
        rksButton.setText(rks.getName());
        vdwButton.setText(vanDW.getName());
        
        tstButton.setUserData(tst);
        prButton.setUserData(pr);
         rksButton.setUserData(rks);
        vdwButton.setUserData(vanDW);
        
        eos = new HashMap(){
            {
                put(tst.getName(),tst);
                put(pr.getName(),pr);
                put(rks.getName(),rks);
                put(vanDW.getName(),vanDW);
            }
        
            
    };
        
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
                
                if(t1 == customEosToggle){
                    uField.setText("0.0");
                    omega_aField.setText("0.0");
                    omega_bField.setText("0.0");
                    wField.setText("0.0");
                    
                    uField.setEditable(true);
                    omega_aField.setEditable(true);
                    omega_bField.setEditable(true);
                    wField.setEditable(true);
                            
                }else{
                    uField.setEditable(false);
                    omega_aField.setEditable(false);
                    omega_bField.setEditable(false);
                    wField.setEditable(false);

                     Cubic cubic = (Cubic)t1.getUserData();

                     uField.setText(String.valueOf(cubic.getU()));
                     omega_aField.setText(String.valueOf(cubic.getOmega_a()));
                     omega_bField.setText(String.valueOf(cubic.getOmega_b()));
                     wField.setText(String.valueOf(cubic.getW()));
                }
              
                
            }
        });
        
//        
//        eosList.getItems().addAll(tst,pr,rks,vanDW);
//        
//        alphaList.getItems().addAll(soave,pengRob,generalTwu,mathias,twu,sv);
//       
    }    
}
