package componentmanager;

import eqpro.UserProperties;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Chilpayate
 */
public class ComponentManagerController implements Initializable {
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
  
  @FXML private TextField enthalpyIG;
  @FXML private TextField entropyIG;
  
  
  @FXML private ListView componentsListView;

  @FXML private Label errorLabel;  
  
//    @FXML
//    private ImageView cpImgView;
    
    
   ComponentManagerControllerModelView manager ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         manager = UserProperties.getComponentManager();
        if(manager ==null){
             createAndBindManagerProperties();
        }
        componentsListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(mouseEvent.getClickCount() == 2){
                        manager.showSelectedComponent();
                    }
                }
            }
        });
    }    
    
    
    @FXML protected void createComponent(ActionEvent event){
        manager.addNewComponent();
    }
    @FXML protected void saveObjects(ActionEvent event)   {
        manager.saveObjects();     
    }
    @FXML protected void loadObjects(ActionEvent event){
              manager.loadObjects();
    }
   
    @FXML protected void regresar(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource(eqpro.EqPro.WelcomeFXML));
        eqpro.EqPro.loadFxml(root);
    }

    private void createAndBindManagerProperties() {
        
             manager = new ComponentManagerControllerModelView();
            UserProperties.setComponentManager(manager);    
            manager.setSelectedComponentProperty(componentsListView.getSelectionModel().selectedItemProperty());
            
            
            
            manager.getNameProperty().bindBidirectional(name.textProperty());
           manager.getCasNumberProperty().bindBidirectional(casNumber.textProperty());
           manager.getCriticalTemperatureProperty().bindBidirectional(criticalTemperature.textProperty());
           manager.getCriticalPressureProperty().bindBidirectional(criticalPressure.textProperty());
           manager.getAcentricFactorProperty().bindBidirectional(acentricFactor.textProperty());
           manager.getL_TwuProperty().bindBidirectional(L_Twu.textProperty());
           manager.getM_TwuProperty().bindBidirectional(M_Twu.textProperty());
           manager.getN_TwuProperty().bindBidirectional(N_Twu.textProperty());
           
           manager.getA_Mathias_CopemanProperty().bindBidirectional(A_Mathias_Copeman.textProperty());
           manager.getB_Mathias_CopemanProperty().bindBidirectional(B_Mathias_Copeman.textProperty());
           
            
         
            manager.getC_Mathias_CopemanProperty().bindBidirectional(C_Mathias_Copeman.textProperty());
            manager.getR_UNIQUACProperty().bindBidirectional(r_UNIQUAC.textProperty());
            manager.getQ_UNIQUACProperty().bindBidirectional(q_UNIQUAC.textProperty());
            
            manager.getPrsvKappaProperty().bindBidirectional(prsvKappa.textProperty());
            
            manager.getA_dippr1007Property().bindBidirectional(A_dippr1007.textProperty());
            manager.getB_dippr1007Property().bindBidirectional(B_dippr1007.textProperty());
            manager.getC_dippr1007Property().bindBidirectional(C_dippr1007.textProperty());
            manager.getD_dippr1007Property().bindBidirectional(D_dippr1007.textProperty());
            manager.getE_dippr1007Property().bindBidirectional(E_dippr1007.textProperty());
            
            manager.getEnthalpyIG().bindBidirectional(enthalpyIG.textProperty());
            manager.getEntropyIG().bindBidirectional(entropyIG.textProperty());
         
            componentsListView.setItems(manager.getComponents());
            
    }
    @FXML protected void justNumbersAllowed(KeyEvent keyEvent){
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
               classes.add("good");
               
           }catch(Exception e){
               classes.add("error");
            }
        
    }

}
