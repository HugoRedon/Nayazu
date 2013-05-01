
package termopackagemanager.alphaexpression;

import eqpro.UserProperties;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class AlphaModelView {
    private static ObservableList<Alpha> existentAlphas = FXCollections.observableArrayList();
    private Alpha selectedAlpha;
    private Component selectedComponent;
    
    private ObservableList<PureSubstance> pureSubstances = FXCollections.observableArrayList();
    
    private ObservableList<Component> components;
    
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

    private ObservableList<PureSubstance> pureSubstanceList = FXCollections.observableArrayList();
    

    public ObservableList<PureSubstance> getPureSubstanceList() {
        return pureSubstanceList;
    }

    public void setPureSubstanceList(ObservableList<PureSubstance> pureSubstanceList) {
        this.pureSubstanceList = pureSubstanceList;
    }
    
    
}
