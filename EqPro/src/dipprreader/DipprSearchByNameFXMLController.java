package dipprreader;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * FXML Controller class
 *
 * @author Hugo
 */
public class DipprSearchByNameFXMLController implements Initializable {

    @FXML TextField searchNameTextField;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
    
    @FXML protected void searchComponentByName(ActionEvent event)throws Exception{
        System.out.println("search");
        
        String dipprUrl ="http://dippr.byu.edu/students/";
        String dipprLoginUrl = "http://dippr.byu.edu/students/chemsearch.asp";

	JavaLogin http = new JavaLogin();
	CookieHandler.setDefault(new CookieManager());
	String page = http.GetPageContent(dipprUrl);
        String postParams = http.getFormParams(page, "HugoRedon", "DIPPR-Hugo");
	// 2. Construct above post's content and then send a POST request for
	// authentication
	http.sendPost(dipprLoginUrl, postParams);
        
        // go to search by name form
        String searchByNamePage = http.GetPageContent(dipprLoginUrl + "?Mode=Search&Method=Name");
         
        String componentName = searchNameTextField.getText();
        String searchParams = http.getSearchFormParams(searchByNamePage, componentName, "contains");
	
       String result =  http.sendPost(dipprLoginUrl, searchParams);
        
       
       Document doc = Jsoup.parse(result);
       
       
       Element olElements = doc.getElementsByTag("ol").first();
       
       
        System.out.println("elementos encontrados: +");
       for (Element el : olElements.getElementsByTag("li")){
           
           String name = el.text();
           System.out.println( name );
       }
       
       //result.
	//String result = http.GetPageContent(dipprLoginUrl);
	System.out.println(result);
    }
}
