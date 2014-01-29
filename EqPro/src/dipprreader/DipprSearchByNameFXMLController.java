package dipprreader;


import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import termo.component.Component;

/**
 * FXML Controller class
 *
 * @author Hugo
 */
public class DipprSearchByNameFXMLController implements Initializable {

    @FXML TextField searchNameTextField;
    @FXML ListView componentsMatchLV;
    HashMap<String,String> components = new HashMap();
    
    JavaLogin siteMessenger ;
    String dipprUrl ="http://dippr.byu.edu/students/";
    String dipprLoginUrl = "http://dippr.byu.edu/students/chemsearch.asp";
	
    
    DipprReader reader = new DipprReader();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

		
    }    
    
    
   
   
    
    @FXML protected void searchComponentByName(ActionEvent event)throws Exception{

        String componentName = searchNameTextField.getText();	
	//components = reader.searchUrlComponentByName(componentName,"contains");
       for (String key : components.keySet()){ 
	    componentsMatchLV.getItems().add(components.get(key));  
       }
    }
    
    
    @FXML public void requestComponentPage(MouseEvent event) throws Exception{
	String component =(String) componentsMatchLV.getSelectionModel().getSelectedItem();
	String url = components.get(component);
	
	    
	
	
	
    }
    
    
}

