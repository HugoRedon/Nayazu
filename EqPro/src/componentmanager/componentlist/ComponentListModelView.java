package componentmanager.componentlist;

import componentmanager.component.ComponentController;
import componentmanager.component.ComponentModelView;
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
public class ComponentListModelView {
    private ObservableList<Component> componentsObservableList = FXCollections.observableArrayList();
    private ObservableValue<Component> selectedComponentObservableValue ;
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
    
     protected void saveObjects( )  throws Exception {
        
           
              if(listFilenameProperty.get().equals("") || !listFilenameProperty.get().endsWith(".eqProComp")){
                  saveAs();
              }else{
                  save(listFilenameProperty.get());
              }
            
               
    }
     protected void saveAs()throws Exception{
         File file = getSaveFile();
         if(file ==null){
             return;
         }
         
          save(file.getPath());
         
     }
     protected void save(String fileName)throws Exception{
         listFilenameProperty.set(fileName);
           if(!fileName.endsWith(".eqProComp")){
                  fileName =fileName + ".eqProComp";
              }
              
               FileOutputStream fout = new FileOutputStream(fileName);
              BufferedOutputStream buf = new BufferedOutputStream(fout);
              XMLEncoder encoder = new XMLEncoder(buf);
               encoder.writeObject(componentsObservableList.toArray());
               encoder.close();
     }
     
     
        protected File  getSaveFile(){
                return  getChooser().showSaveDialog(null);
                
        }
        
        protected FileChooser getChooser(){
                FileChooser chooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivo de componentes  EqPro (*.eqProComp)","*.eqProComp");
                File initialDirectory = new File("C:\\");
                chooser.setInitialDirectory(initialDirectory);
                chooser.getExtensionFilters().add(extFilter);
                return chooser;
        }
        
        protected File  getLoadFile(){
                return  getChooser().showOpenDialog(null);
        }
        
         protected void loadObjects( ){
               try {
                   componentsObservableList.clear();
                   File file = getLoadFile();
                   if(file ==null){
                       return;
                   }
                   String fileName = file.getPath();
                   listFilenameProperty.set(fileName);
                   
                FileInputStream fin = new FileInputStream(fileName);
                BufferedInputStream buff = new BufferedInputStream(fin);
                        
                XMLDecoder decoder = new XMLDecoder(buff);


                Object[] arrayComp ;
                arrayComp = (Object[]) decoder.readObject();
                for(Object ob : arrayComp){
                    Component c = (Component)ob;
                    componentsObservableList.add(c);
                
               decoder.close();
            }
               
            } catch (Exception ex) {
                Logger.getLogger(ComponentController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    

  

    public void saveChanges(Component newValue, Component oldValue) {
        if(componentsObservableList.contains(oldValue)){
            int index = componentsObservableList.indexOf(oldValue);
            componentsObservableList.add(index, newValue);
            componentsObservableList.remove(oldValue);
            //componentsObservableList.add(component);
        }
    }

    public ObservableList<Component> getComponentsObservableList() {
        return componentsObservableList;
    }

    public void setComponentsObservableList(ObservableList<Component> componentsObservableList) {
        this.componentsObservableList = componentsObservableList;
    }

    public ObservableValue<Component> getSelectedComponentObservableValue() {
        return selectedComponentObservableValue;
    }

    public void setSelectedComponentObservableValue(ObservableValue<Component> selectedComponentObservableValue) {
        this.selectedComponentObservableValue = selectedComponentObservableValue;
    }

    public SimpleStringProperty getListFilenameProperty() {
        return listFilenameProperty;
    }

    public void setListFilenameProperty(SimpleStringProperty listFilenameProperty) {
        this.listFilenameProperty = listFilenameProperty;
    }

    public void modifySelectedComponent() throws Exception {
        ComponentModelView componentModelView = UserProperties.getComponentModelView();
        
        if(selectedComponentObservableValue.getValue() != null){
            componentModelView.setComponentBeingModified(true);
            componentModelView.setComponentToModify(selectedComponentObservableValue.getValue());
            eqpro.EqPro.startComponentManager();
        }
        
        
    }

    public void deleteSelectedObject() {
        if(selectedComponentObservableValue.getValue() != null){
            componentsObservableList.remove(selectedComponentObservableValue.getValue());
        }
    }

    public void createNewComponent() throws Exception{
        ComponentModelView componentModelView = UserProperties.getComponentModelView();
        
        componentModelView.setComponentBeingModified(false);
            
            
        Component newComponent = new Component();
        newComponent.setName("Componente Nuevo");
        newComponent.setCasNumber("Número Cas");
        newComponent.setCp(new DIPPR_107_Equation());
            
        componentModelView.showComponent(newComponent);
        eqpro.EqPro.startComponentManager();
        
       
    }
        
}
