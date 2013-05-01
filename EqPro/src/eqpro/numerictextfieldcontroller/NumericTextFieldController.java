package eqpro.numerictextfieldcontroller;

import java.lang.reflect.Field;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author Hugo Redon Rivera
 */
public class NumericTextFieldController {
//    @FXML public Hyperlink okAction ;
//    @FXML public ImageView okImage;
    
    public SimpleBooleanProperty containsErrorProperty = new SimpleBooleanProperty();
    
    
protected boolean containsError() throws Exception{
        Field[] fields = this.getClass().getDeclaredFields();
        for(Field field: fields){
            if(field.getType().equals(TextField.class)){
                field.setAccessible(true);
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
               containsErrorProperty.set(containsError);
               
           }
        
    }
}
