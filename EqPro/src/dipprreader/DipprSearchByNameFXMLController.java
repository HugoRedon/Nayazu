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
    
    
    JavaLogin siteMessenger ;
    String dipprUrl ="http://dippr.byu.edu/students/";
    String dipprLoginUrl = "http://dippr.byu.edu/students/chemsearch.asp";
	
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
	try {
	    login();
	} catch (Exception ex) {
	    System.out.println("login failed");
	}
		
    }    
    
    
   
    public void login()throws Exception{
	siteMessenger = new JavaLogin();
	CookieHandler.setDefault(new CookieManager());	
	String page = siteMessenger.GetPageContent(dipprUrl);
        String postParams = siteMessenger.getFormParams(page, "HugoRedon", "DIPPR-Hugo");
	siteMessenger.sendPost(dipprLoginUrl, postParams);
        
    }
    
    @FXML protected void searchComponentByName(ActionEvent event)throws Exception{
        System.out.println("------------- search -------------------");

        String searchByNamePage = siteMessenger.GetPageContent(dipprLoginUrl + "?Mode=Search&Method=Name");
         
        String componentName = searchNameTextField.getText();
        String searchParams = siteMessenger.getSearchFormParams(searchByNamePage, componentName, "contains");
	
       String result =  siteMessenger.sendPost(dipprLoginUrl, searchParams);
       Document doc = Jsoup.parse(result);
       Element olElements = doc.getElementsByTag("ol").first();
       for (Element el : olElements.getElementsByTag("li")){
	    String searchUrl  = el.getElementsByTag("a").attr("href");
	    
	    String name = el.text();
	   
	    components.put(name, searchUrl);
	    
	    componentsMatchLV.getItems().add(name);
           
       }
    }
    HashMap<String,String> components = new HashMap();
    
    @FXML public void requestComponentPage(MouseEvent event) throws Exception{
	String component =(String) componentsMatchLV.getSelectionModel().getSelectedItem();
	String url = components.get(component);
	String componentId = url.substring(url.lastIndexOf("=")+1);
	    
	Double mw = getPropertyValue(componentId, "MW", "Molecular Weight");
	Double tc = getPropertyValue(componentId, "TC", "Critical Temperature");
	Double 
	
	
	
	
	
	Component com = new Component();
	com.setCriticalTemperature(tc);
	com.setMolecularWeight(mw);
	
	
	
	
//	
//	
//	String searchByNamePage = siteMessenger.GetPageContent(dipprUrl + url);
//	
//	
//	
//	
//	
//	
//	Double mw = getMolecularWeight(componentId);
//	
//	Document doc = Jsoup.parse(searchByNamePage);
//	Elements trs = doc.getElementsByTag("tr");
//	
//	String molText= "Molecular Weight";
//	for (Element tr: trs){
//	    if(tr.text().contains(molText)){
//		//System.out.println(tr);
//		Elements tds = tr.getElementsByTag("td");
//		
//		for(Element td: tds){
//		    if (td.text().contains(molText)){
//			//System.out.println(td);
//			Elements as = td.getElementsByTag("a");
//			for (Element a : as){
//			    Elements anchors =a.getElementsByAttributeValueContaining("href", "MW");
//			    
//			    Integer size = anchors.size();
//			    if (size == 1 ){
//				System.out.println(anchors.first());
//				String propertyLink = anchors.first().attr("href");
//				String propertyPage = siteMessenger.GetPageContent(dipprUrl + propertyLink);
//				Elements paragraphs = Jsoup.parse(propertyPage).getElementsByTag("table");
//				
//				for(Element el: paragraphs){
//				    if (el.toString().contains(molText)){
//					Element importantParagraph = el;
//					System.out.println(importantParagraph.toString());
//					Elements blueProperties = importantParagraph.getElementsByAttributeValue("color", "blue");
//					int blueElements  = blueProperties.size();
//					if(blueElements == 1){
//					    System.out.println(blueProperties.first().text());
//					}
//				    }
//				}
//				
//						
//				
//				
//				
//			    }
//			    
//			    
//			    
//			}
//			
//		    }
//		}
//		
//		
//	    }
//	}
	
	
	
	
	
	//Element tbody = doc.getElementsByTag("tbody").first();
	//Elements rows = tbody.getElementsByTag("tr");
	//System.out.println(tbody);
	//Element r1 = rows.get(2);
	//Elements ds = r1.getElementsByTag("td");
	//Element d3 = ds.get(2);
	
	
	//System.out.println(d3);
	
	
    }
    
    private Double getPropertyValue(String componentId, String propertyShort,String propertyName) throws Exception{
	    String searchProperty = siteMessenger.GetPageContent(dipprUrl + "?Mode=Reference&ChemID=" + componentId + "&Property=" + propertyShort);
	
	Elements paragraphs = Jsoup.parse(searchProperty).getElementsByTag("table");
				
	for(Element el: paragraphs){
	    if (el.toString().contains(propertyName)){
		Element importantParagraph = el;
		Elements blueProperties = importantParagraph.getElementsByAttributeValue("color", "blue");
		int blueElements  = blueProperties.size();
		if(blueElements == 1){
		    return Double.parseDouble(blueProperties.first().text());
		}
	    }
	}
	return 0d;
    }
}


