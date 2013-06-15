package componentmanager.components;

import componentmanager.component.ComponentController;
import componentmanager.component.ComponentModelView;
import componentmanager.componentlists.EQProComponentList;
import eqpro.UserProperties;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import termo.component.Component;
import termo.cp.DIPPR_107_Equation;

/**
 *
 * @author Hugo Redon Rivera
 */
public class ComponentsModelView {
    private ObservableList<Component> componentsObservableList = FXCollections.observableArrayList();
    private ObservableValue<Component> selectedComponent ;
    private SimpleStringProperty listFilenameProperty = new SimpleStringProperty("");
    
    

    public boolean addComponentToList(Component newComponent){
         if(!isComponentNameAndCasNumberAlreadyInList(newComponent)){
           componentsObservableList.add(newComponent);
            return true;
        }
         return false;
    }
    
     public boolean isComponentAlreadyAdded(Component comp){
      for(Component c : componentsObservableList){
          if(comp.equals(c)){
              return true;
          }
      }  
      
      return false;
    }
    public boolean isComponentNameAndCasNumberAlreadyInList(Component newComponent){
        for(Component c: componentsObservableList){
            String newName = newComponent.getName();
            String newCas = newComponent.getCasNumber();
            
            String existingName = c.getName();
            String existingCas = c.getCasNumber();
            
            
            if(newCas.equals(existingCas) || newName.equals(existingName)){
                return true;
            }
        }
        return false;
    }
    

 

     
     
   
   
    

  

    public void saveChanges(Component newValue, Component oldValue) {
        if(componentsObservableList.contains(oldValue)){
            int index = componentsObservableList.indexOf(oldValue);
            componentsObservableList.add(index, newValue);
            componentsObservableList.remove(oldValue);
            //componentsObservableList.add(component);
        }
    }





    public boolean isMixture() {
        if(componentsObservableList.size() > 1){
            return true;
        } else {
            return false;
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    public ObservableList<Component> getComponentsObservableList() {
        return componentsObservableList;
    }

    public void setComponentsObservableList(ObservableList<Component> componentsObservableList) {
        this.componentsObservableList = componentsObservableList;
    }

    public ObservableValue<Component> getSelectedComponent() {
        return selectedComponent;
    }

    public void setSelectedComponent(ObservableValue<Component> selectedComponentObservableValue) {
        this.selectedComponent = selectedComponentObservableValue;
    }
    
    public SimpleStringProperty getListFilenameProperty() {
        return listFilenameProperty;
    }

    public void setListFilenameProperty(SimpleStringProperty listFilenameProperty) {
        this.listFilenameProperty = listFilenameProperty;
    }
        
}
