
package dipprreader;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import termo.component.Component;

/**
 *
 * @author
 * Hugo
 */

public final class DipprReader{
  JavaLogin siteMessenger ;
    String dipprUrl ="http://dippr.byu.edu/students/";
    String dipprLoginUrl = "http://dippr.byu.edu/students/chemsearch.asp";
    
    
    public DipprReader(){
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
    
     HashMap<String,String> searchUrlComponentByName(String componentName,String exactness/*exact contains */) throws Exception {
	 HashMap<String,String> componentsFound = new HashMap();
	 //ArrayList<String> componentsFound = new ArrayList();
	//String searchByNamePage = siteMessenger.GetPageContent(dipprLoginUrl + "?Mode=Search&Method=Name");
//        String searchParams = siteMessenger.getSearchFormParams(searchByNamePage, componentName, exactness);
//       String result =  siteMessenger.sendPost(dipprLoginUrl, searchParams);
	String result = siteMessenger.GetPageContent(dipprLoginUrl + "?Mode=Search&Method=Name&NC=1&C1="+URLEncoder.encode(componentName, "UTF-8")+"&Exactness="+URLEncoder.encode(exactness, "UTF-8")+"&submit1=Enviar");
       Document doc = Jsoup.parse(result);
       Element olElements = doc.getElementsByTag("ol").first();
       
       
         for (Element el : olElements.getElementsByTag("li")){
	    String searchUrl  = el.getElementsByTag("a").attr("href");
	    
	    String name = el.text();
	   
	    componentsFound.put(name, searchUrl);	 
	    //componentsFound.add(searchUrl);
       }
	 
	 return componentsFound;
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
    
    public Component getComponent(String componentName) throws Exception{
	HashMap<String,String> urlList =searchUrlComponentByName(componentName,"exact");
	String name =(String) urlList.keySet().toArray()[0];
	String url = urlList.get(name);
	String componentId = url.substring(url.lastIndexOf("=")+1);
	
	
	Double mw = getPropertyValue(componentId, "MW", "Molecular Weight");
	Double tc = getPropertyValue(componentId, "TC", "Critical Temperature");
	Double pc = getPropertyValue(componentId, "PC", "Critical Pressure");
	Double vc = getPropertyValue(componentId, "VC", "Critical Volume");
	//Double zc = getPropertyValue(componentId, "ZC", "Critical Compressibility");
	Double hfor = getPropertyValue(componentId, "HFOR", "Enthalpy of Formation of Ideal Gas");
	Double acen = getPropertyValue(componentId, "ACEN", "Acentric Factor ");
	
	
	Component com = new Component();
	com.setName(name);
	com.setCriticalTemperature(tc);
	com.setMolecularWeight(mw);
	com.setCriticalPressure(pc);
	com.setCriticalVolume(vc);
	//factor factor not added zc
	com.setEnthalpyofFormationofIdealgasat298_15Kand101325Pa(hfor);
	com.setAcentricFactor(acen);
	return com;
    }
}
