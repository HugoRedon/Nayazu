package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Hugo Redon Rivera
 */
public class PropertiesBarController implements Initializable, ChangeListener {
    @FXML Text packageText;
    @FXML FlowPane pane;
    @FXML Text mixingRuleText;
   @FXML Text messageText;
    
    BooleanBinding ready ;
//    SimpleStringProperty state = new SimpleStringProperty( "notReady");
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        packageText.textProperty().bind(UserProperties.getSelectedPackageText());
        
        messageText.textProperty().bind(UserProperties.messageToshow());
        
        
        ready = UserProperties.arePropertiesReady();
        ready.addListener(this);
    }    
    protected void propertiesAreReady(){
        pane.getStyleClass().clear();
        pane.getStyleClass().add("ready");
        
    }
    
    protected void propertiesAreNotReady(){
        pane.getStyleClass().clear();
        pane.getStyleClass().add("notReady");
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        boolean newState = (boolean)newValue;
        
        if(newState == true){
            propertiesAreReady();
        }else{
            propertiesAreNotReady();
        }
            
        
        
    }

 
}
