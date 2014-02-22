package application.model;

import javafx.beans.property.SimpleStringProperty;
import termo.component.Component;

/**
 *
 * @author
 * Hugo
 */
public class CompositionTableItem{
    private SimpleStringProperty componentName = new SimpleStringProperty();
    private Component component;
    private SimpleStringProperty molFractionValue = new SimpleStringProperty();
    
    public String getComponentName(){
	return componentName.get();
    }
    public void setComponentName(String name){
	componentName.set(name);
    }

   
    public String getMolFractionValue(){
	//return String.valueOf(molFractionValue.get());
	return molFractionValue.get();
    }
    public void setMolFractionValue(String value){
	//Double d = Double.valueOf(value);
	
	molFractionValue.set(value);
    }

    /**
     * @return the component
     */
    public Component getComponent() {
	return component;
    }
    public void setComponent(Component component){
	this.component = component;
    }
    
}
