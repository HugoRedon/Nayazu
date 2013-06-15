
package termopackagemanager.alphaexpression;

import eqpro.UserProperties;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import termo.component.Component;
import termo.eos.alpha.Alpha;
import termo.eos.alpha.AlphaFactory;
import termo.substance.PureSubstance;

/**
 *
 * @author Hugo Redon Rivera
 */
public final class AlphaModelView  implements ChangeListener{
    private static ObservableList<Alpha> existentAlphas = FXCollections.observableArrayList();
    private ObservableList<ObjectProperty> selectedAlphas = FXCollections.observableArrayList();
    private ObservableValue shownAlpha ;
    private ObservableList<Component> components;
    private ObservableList<PureSubstance> pureSubstanceList = FXCollections.observableArrayList();
    
    public AlphaModelView() {
        components  = UserProperties.getComponentListModelView().getComponentsObservableList();
        for(Component c: components){
            Alpha defaultAlpha = AlphaFactory.getSoaveExpression();
           pureSubstanceList.add(createPureSubstance(defaultAlpha, c));
        }
        
        
    }

    public static ObservableList<Alpha> getExistentAlphas() {return existentAlphas; }
    public static void setExistentAlphas(ObservableList<Alpha> aExistentAlphas) {existentAlphas = aExistentAlphas; }
 

    public PureSubstance createPureSubstance(Alpha alpha,Component component){
        PureSubstance substance = new PureSubstance();
        
        substance.setAlpha(alpha);
        substance.setComponent(component);
        return substance;
    }
    
   
    
      
    {
            Method[] methods = AlphaFactory.class.getDeclaredMethods();
            Method.setAccessible(methods, true);
            for(Method method: methods){
            try {                
                Alpha alpha = (Alpha)method.invoke(null,  null);
                getExistentAlphas().add(alpha);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(AlphaModelView.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }


    

    public ObservableList<PureSubstance> getPureSubstanceList() {
        return pureSubstanceList;
    }

    public void setPureSubstanceList(ObservableList<PureSubstance> pureSubstanceList) {
        this.pureSubstanceList = pureSubstanceList;
    }
    public void addSelectedAlpha(ObjectProperty selectedAlpha){
        selectedAlpha.addListener(this);
        selectedAlphas.add(selectedAlpha);
        
        //setShownAlpha(selectedAlpha);
        
    }
    public ObservableList<ObjectProperty> getSelectedAlphas(){
        return this.selectedAlphas;
    }

    @Override
    public void changed(ObservableValue ov, Object t, Object t1) {
       Alpha  newShowAlpha = (Alpha) t1;
       //shownAlpha = new ObservableStringValue(newShowAlpha);
          System.out.println("Cambio de alpha a: "  + newShowAlpha.toString());
        
    }

    public ObservableValue getShownAlpha() {
        return shownAlpha;
    }

    public void setShownAlpha(ObservableValue shownAlpha) {
        this.shownAlpha = shownAlpha;
    }
}


