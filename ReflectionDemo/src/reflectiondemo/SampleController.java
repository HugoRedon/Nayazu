/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reflectiondemo;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Chilpayate
 */
public class SampleController implements Initializable {
    
    @FXML    private Label label;
    @FXML private Label numberLabel;
    @FXML private TextField aText;
    @FXML private TextField aNumber;
    
    private TheModelView theModel;
    
  
    
    @FXML protected void doubleParse() throws Exception{
        TheModel instanceModel = theModel.createAModel();
        
        label.setText(instanceModel.getaText());
        numberLabel.setText(String.valueOf(instanceModel.getaNumber()));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
             bindViewWithModel();
    }    
    
    private void bindViewWithModel(){
        try {
            theModel = new TheModelView();
            Field[] fields = theModel.getClass().getDeclaredFields();
            for(Field field : fields){
                field.setAccessible(true);
                
                SimpleStringProperty property = (SimpleStringProperty)field.get(theModel);
                TextField aTextField = (TextField)getClass().getDeclaredField(field.getName()).get(this);
                
                property.bindBidirectional(aTextField.textProperty());
                
            }  
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
             label.setText("dafuk!" + ex.getMessage());
            Logger.getLogger(SampleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
