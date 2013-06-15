package reflectiondemo;

import java.lang.reflect.Field;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Hugo Redon Rivera
 */
public class TheModelView {
    private SimpleStringProperty aText  = new SimpleStringProperty("hola");
    private SimpleStringProperty aNumber = new SimpleStringProperty("0");


    public TheModel createAModel()throws Exception{
            TheModel model = new TheModel();

           Field[] fields = model.getClass().getDeclaredFields();
           for(Field field: fields){
               field.setAccessible(true);
                   if(field.getType().equals(String.class)){
                       SimpleStringProperty aProperty = (SimpleStringProperty)this.getClass().getDeclaredField(field.getName()).get(this);
                       field.set(model, aProperty.get());
                       
                   }else if(field.getType().equals(double.class)){
                       SimpleStringProperty aProperty = (SimpleStringProperty)this.getClass().getDeclaredField(field.getName()).get(this);

                       field.set(model, Double.valueOf(aProperty.get()));
                   }
           }
        
        
        return model;
    }

    
}
