package componentmanager.component;

import eqpro.UserProperties;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import termo.component.Component;
import termo.cp.DIPPR_107_Equation;

/**
 * FXML Controller class
 *
 * @author Chilpayate
 */
public class ComponentController implements Initializable {
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
            errorLabel.setText("error");
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


    
    
    protected boolean containsError() throws Exception{
        Field[] fields = this.getClass().getDeclaredFields();
        for(Field field: fields){
            if(field.getType().equals(TextField.class)){
                TextField textField = (TextField)field.get(this);
                if(textField.getStyleClass().contains("error")){
                    return true;
                }
            }
        }
        return false;
    }
 
 
    @FXML protected void justNumbersAllowed(KeyEvent keyEvent) throws Exception{
            TextField focusedField = (TextField)keyEvent.getSource();
            ObservableList<String> classes = focusedField.getStyleClass();
            classes.clear();
            
            String keyCharacter = keyEvent.getCharacter();
           String fieldText = focusedField.getText();
           fieldText = (fieldText == null )? "":fieldText;
           fieldText = (keyCharacter.matches("\\r"))? fieldText.substring(0, fieldText.length()-2):fieldText;
           keyCharacter = (keyCharacter.matches("\\W"))? "": keyCharacter;
           String resultText = fieldText + keyCharacter;
           resultText = (resultText.equals("")   || resultText.equals(".") || resultText.equals("-") || resultText.equals("e"))? "0":resultText;
           
           try{
               Double.valueOf(resultText);
               classes.add("text-field");
           }catch(Exception e){
               classes.add("error");
            }finally{
               boolean containsError = containsError();
               okAction.setVisible(!containsError);
               okImage.setVisible(!containsError);
               
           }
        
    }

}
