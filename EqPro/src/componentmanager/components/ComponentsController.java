package componentmanager.components;

import componentmanager.component.ComponentModelView;
import componentmanager.componentlists.EQProComponentList;
import eqpro.EqPro;
import eqpro.UserProperties;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import termo.component.Component;
import termo.cp.DIPPR_107_Equation;

/**
 * FXML Controller class
 *
 * @author Hugo Redon Rivera
 */
public class ComponentsController implements Initializable {

    private EQProComponentList componentList = new EQProComponentList();
    private  ReadOnlyObjectProperty<Component> selectedComponent;
    private SimpleBooleanProperty isSelected = new SimpleBooleanProperty(false);
    
    @FXML  ListView componentsListView ;
    @FXML private Label fileName;
    @FXML private Hyperlink deleteLink;
    @FXML private ImageView deleteImage;
    @FXML private ImageView editImage;
    @FXML private Hyperlink editLink;
    @FXML private Hyperlink importHyperLink;	
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        componentsListView.setItems(componentList.getComponents());
        fileName.textProperty().bind(componentList.getName());
        
        selectedComponent = componentsListView.getSelectionModel().selectedItemProperty();
        
        isSelected.bind(selectedComponent.isNotNull());
        
        deleteLink.visibleProperty().bind(isSelected);
        deleteImage.visibleProperty().bind(isSelected);
        editLink.visibleProperty().bind(isSelected);
        editImage.visibleProperty().bind(isSelected);
        
        
    }   
    
    @FXML protected void modifyComponent(MouseEvent mouseEvent) throws Exception{
          if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
            if(mouseEvent.getClickCount() == 2){
               editComponent();
            }
            
        }
    }

    protected void editComponent()throws Exception{
           ComponentModelView componentModelView = UserProperties.getComponentModelView();
        
        if(selectedComponent.getValue() != null){
            componentModelView.setComponentBeingModified(true);
            componentModelView.setComponentToModify(selectedComponent.getValue());
            eqpro.EqPro.startComponentManager();
        }
    }
    
    @FXML protected void editComponent(ActionEvent event)throws Exception{
        editComponent();
    }
    
//    @FXML protected void editComponent(ActionEvent event) throws Exception{
//         if(manager.getSelectedComponent().getValue() != null){
//             manager.editSelectedComponent();
//         }
//    }
    
    @FXML protected void addComponent(ActionEvent event)throws Exception{
 
        
         ComponentModelView componentModelView = UserProperties.getComponentModelView();
        componentModelView.setComponentBeingModified(false);
        Component newComponent = new Component();
        newComponent.setName("Componente Nuevo");
        newComponent.setCasNumber("Número Cas");
        newComponent.setCp(new DIPPR_107_Equation(null));
            
        componentModelView.showComponent(newComponent);
        eqpro.EqPro.startComponentManager();
    }
    
    @FXML protected void deleteComponent(ActionEvent event){
            
        if(selectedComponent.getValue() != null){
            componentList.getComponents().remove(selectedComponent.getValue());
        }
    }
    
     @FXML protected void saveObjects(ActionEvent event)  throws Exception {
         
         String filePath = componentList.getFilePath().get();
          if(filePath.equals("") || !filePath.endsWith(".eqProComp")){
                  saveAs();
              }else{
                  save(filePath);
              }
    }
     
     protected void save(){
         
     }
     
     protected void saveAs()throws Exception{
             File file = getSaveFile();
         if(file ==null){
             return;
         }
         
          save(file.getPath());
     }
     @FXML protected void saveAs(ActionEvent event)throws Exception{
         saveAs();
     }
     @FXML protected void importComponent(ActionEvent event) throws Exception{
	 EqPro.startDIPPRImport();
     }
    protected void save(String fileName)throws Exception{
              
              
         
           if(!fileName.endsWith(".eqProComp")){
                  fileName =fileName + ".eqProComp";
              }
           componentList.getFilePath().set(fileName);
              
               FileOutputStream fout = new FileOutputStream(fileName);
              BufferedOutputStream buf = new BufferedOutputStream(fout);
              XMLEncoder encoder = new XMLEncoder(buf);
               encoder.writeObject(componentList);
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
	
	
          
//     @FXML protected void loadObjects(ActionEvent event){
//
//                componentsObservableList.clear();
//                componentsListView.setItems(com);
//                String filePath = list.getFilePath().get();
//                listFilenameProperty.set(filePath);
//                componentsObservableList.addAll(list.getComponents());
//    
//    
//    }
    @FXML protected void regresar(ActionEvent event) throws IOException{
        EqPro.startWelcomeForm();
    }
}
