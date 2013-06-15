
package componentmanager.componentlists;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;


/**
 * FXML Controller class
 *
 * @author Hugo Redon Rivera
 */
public class ComponentListsController implements Initializable {

    @FXML ListView listComponentListView;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    @FXML  protected void addComponentListFromFile(ActionEvent event )throws Exception{
         FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Elige una lista de componentes EQPro");
        fileChooser.getExtensionFilters().add(
                new ExtensionFilter("EqPro Lista de componentes", "*.eqProComp"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null ) {
            String fileName = selectedFile.getPath();
       
                FileInputStream fin = new FileInputStream(fileName);
                BufferedInputStream buff = new BufferedInputStream(fin);
             try (XMLDecoder decoder = new XMLDecoder(buff)) {
                 EQProComponentList list = (EQProComponentList)decoder.readObject();
               listComponentListView.getItems().add(list);
             }
        }
    }

    @FXML protected void createComponentList(ActionEvent event){
        
    }
    @FXML protected void editComponentList(ActionEvent event){
        
    }
    
    @FXML  protected void deleteComponentList(ActionEvent event){
        
    }
    
    @FXML protected void done(){
        
    }
    
}
