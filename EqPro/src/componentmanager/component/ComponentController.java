package componentmanager.component;

import eqpro.UserProperties;
import eqpro.numerictextfieldcontroller.NumericTextFieldController;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Chilpayate
 */
public class ComponentController extends NumericTextFieldController implements Initializable {
    @FXML    private TextField criticalTemperature;
    @FXML     private TextField criticalPressure;
    @FXML    private TextField acentricFactor;
    @FXML    private TextField prsvKappa;

    @FXML      private TextField L_Twu;
    @FXML    private TextField M_Twu;
    @FXML    private TextField N_Twu;

    @FXML      private TextField A_Mathias_Copeman;
    @FXML      private TextField B_Mathias_Copeman;
    @FXML     private TextField C_Mathias_Copeman;

    @FXML       private TextField r_UNIQUAC;
    @FXML      private TextField q_UNIQUAC;

    @FXML     private TextField name;
    @FXML      private TextField casNumber;

    @FXML      private TextField A_dippr1007;
    @FXML      private TextField B_dippr1007;
    @FXML     private TextField C_dippr1007;
    @FXML      private TextField D_dippr1007;
    @FXML      private TextField E_dippr1007;

    @FXML private TextField enthalpyofFormationofIdealgasat298_15Kand101325Pa;
    @FXML private TextField absoluteEntropyofIdealGasat298_15Kand101325Pa;

    ComponentModelView theModel ;
    @FXML private Label errorLabel;
    @FXML private Hyperlink okAction;
    @FXML private ImageView okImage;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        theModel = UserProperties.getComponentModelView();

        try{
            bindViewWithModel();           
            if(!theModel.isComponentBeingModified()){   
            }
        }catch(Exception e){
           // errorLabel.setText("error");
            System.err.println(e.getMessage());
        }          
    }    
    
     private void bindViewWithModel()throws Exception{
        
            Field[] fields = theModel.getClass().getDeclaredFields();
            for(Field field : fields){
                field.setAccessible(true);
                if(field.getType().equals(SimpleStringProperty.class)){
                    SimpleStringProperty property = (SimpleStringProperty)field.get(theModel);
                    TextField aTextField = (TextField)getClass().getDeclaredField(field.getName()).get(this);
                    aTextField.textProperty().bindBidirectional(property);
                } 
            }  
 
    }
    @FXML protected void ok() throws IOException, Exception{
        boolean  succes = theModel.ok();
        if(!succes) {
            errorLabel.setText("El nombre o número CAS ya existe en la lista de componentes.");
        }
       
    }
    @FXML protected void cancel() throws IOException{
        eqpro.EqPro.startComponentListManager();
    }
}
