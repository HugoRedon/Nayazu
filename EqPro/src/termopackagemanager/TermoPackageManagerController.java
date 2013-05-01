package termopackagemanager;

import eqpro.EqPro;
import eqpro.UserProperties;
import eqpro.numerictextfieldcontroller.NumericTextFieldController;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import termo.eos.Cubic;
import termo.eos.EquationOfStateFactory;

/**
 * FXML Controller class
 *
 * @author Chilpayate
 */
public class TermoPackageManagerController extends NumericTextFieldController implements Initializable {
    
    @FXML ToggleButton customEosToggle;
    
    @FXML ToggleGroup group;
    
    @FXML TextField nameField;
    @FXML TextField uField;
    @FXML TextField wField;
    @FXML TextField omega_aField;
    @FXML TextField omega_bField;
    
    private CubicModelView manager;
    HashMap<String,Cubic> eos ;
    
    @FXML  private Hyperlink okAction;
    @FXML  private ImageView okImage;
    
    @FXML private GridPane gridPane;
    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          manager = UserProperties.getCubicModelView();
        //bind 
        bindWithModelView();
        try{
            addingCubicEOSToButtons();
        }catch(Exception e){
            System.out.println("Esta mal" + e.getMessage());
        }
   

       
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
                Cubic cubic;
                
                if(t1 == null){
                   manager.showEmpty();
                    manager.getSelectedProperty().set(false);
                    manager.getEditableProperty().set(false);
                    return;
                }else if(t1 == customEosToggle){
                    cubic = new Cubic();   
                    cubic.setName("Nueva ecuación");
                    manager.getEditableProperty().set(true);
                }else{
                    cubic = (Cubic)t1.getUserData();
                    manager.getEditableProperty().set(false);
                }
                                        
                manager.getSelectedProperty().set(true);
                manager.setSelectedCubic(cubic);
                 
            }
        });
        
        if(manager.getSelectedProperty().get()){
            for(Toggle toggle : group.getToggles()){
                Cubic cubic =(Cubic)toggle.getUserData();
                if(manager.getSelectedCubic().equals(cubic)){
                    toggle.setSelected(true);
                }
            }
        }
            
    }    
    
    @FXML protected void next() throws IOException{         
        EqPro.startAlphaExpressionForm();
    }

    
    @FXML protected void back()throws Exception{
        EqPro.startWelcomeForm();
    }
   
  
    private void bindWithModelView() {
        nameField.textProperty().bindBidirectional(manager.getName());
        uField.textProperty().bindBidirectional(manager.getU());
        wField.textProperty().bindBidirectional(manager.getW());
        omega_aField.textProperty().bindBidirectional(manager.getOmega_a());
        omega_bField.textProperty().bindBidirectional(manager.getOmega_b());
        
        bindTextFieldsEditableProperty(manager.getEditableProperty());
        
        okAction.visibleProperty().bind(containsErrorProperty.not().and(manager.getSelectedProperty()));
        okImage.visibleProperty().bind(okAction.visibleProperty());
    }
    
    private void bindTextFieldsEditableProperty(ObservableValue<? extends Boolean> isEditable){
        nameField.editableProperty().bind(isEditable);
        uField.editableProperty().bind(isEditable);
        wField.editableProperty().bind(isEditable);
        omega_aField.editableProperty().bind(isEditable);
        omega_bField.editableProperty().bind(isEditable);
    }
    
    

    private void addingCubicEOSToButtons() throws Exception {
        //could be used
        
        Method[] methods =EquationOfStateFactory.class.getDeclaredMethods();
        Method.setAccessible(methods, true);
        
        for(Method method : methods){
            Class type = method.getReturnType();
            if(type.equals(Cubic.class)){
                Cubic cubic = (Cubic)method.invoke(null, null);
                ToggleButton toggle = new ToggleButton(cubic.getName());
                toggle.setUserData(cubic);
                int size =  gridPane.getChildren().size();
                gridPane.add(toggle, 0, size );
                group.getToggles().add(toggle);
            }
        }
        
      
    }
}
