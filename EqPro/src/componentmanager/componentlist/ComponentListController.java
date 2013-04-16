package componentmanager.componentlist;

import eqpro.EqPro;
import eqpro.UserProperties;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import termo.component.Component;

/**
 * FXML Controller class
 *
 * @author Hugo Redon Rivera
 */
public class ComponentListController implements Initializable {

    @FXML  ListView componentsListView;
    private ComponentListModelView manager;
    @FXML private Label fileName;
    
    @FXML private Hyperlink deleteLink;
    @FXML private ImageView deleteImage;
    
    @FXML private ImageView editImage;
    @FXML private Hyperlink editLink;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        manager =  UserProperties.getComponentListModelView();
        componentsListView.setItems(manager.getComponentsObservableList());
        manager.setSelectedComponentObservableValue(componentsListView.getSelectionModel().selectedItemProperty());
        fileName.textProperty().bind(manager.getListFilenameProperty());
        manager.getSelectedComponentObservableValue().addListener(new ChangeListener<Component>() {

    @Override
    public void changed(ObservableValue<? extends Component> ov, Component t, Component t1) {
        if(t1 != null){
            deleteLink.setVisible(true);
            deleteImage.setVisible(true);
            editImage.setVisible(true);
            editLink.setVisible(true);
        }else{
            deleteLink.setVisible(false);
            deleteImage.setVisible(false);
            editImage.setVisible(false);
            editLink.setVisible(false);
        }
        
     
    //    System.out.println("Value Changed");
            }
        });
    }   
    
    @FXML protected void modifyComponent(MouseEvent mouseEvent) throws Exception{
          if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
            if(mouseEvent.getClickCount() == 2){
               modifyComponent();
            }
            
        }
    }
    @FXML protected void modifyComponentFromLink(ActionEvent event)throws Exception{
        modifyComponent();
    }
    protected void modifyComponent()throws Exception{
        if(manager.getSelectedComponentObservableValue().getValue() != null){
            manager.modifySelectedComponent();
        }
    }
    
//    @FXML protected void modifyComponent(ActionEvent event) throws Exception{
//         if(manager.getSelectedComponentObservableValue().getValue() != null){
//             manager.modifySelectedComponent();
//         }
//    }
    
    @FXML protected void createAndAddComponent(ActionEvent event)throws Exception{
        manager.createNewComponent();
        
    }
    @FXML protected void deleteComponent(ActionEvent event){
          if(manager.getSelectedComponentObservableValue().getValue() != null){
              manager.deleteSelectedObject();
          }
        
    }
    
     @FXML protected void saveObjects(ActionEvent event)  throws Exception {
         
        manager.saveObjects();     
    }
     @FXML protected void saveAs(ActionEvent event)throws Exception{
         manager.saveAs();
     }
         @FXML protected void loadObjects(ActionEvent event){
              manager.loadObjects();
    }
    @FXML protected void regresar(ActionEvent event) throws IOException{
        EqPro.startWelcomeForm();
    }
}
