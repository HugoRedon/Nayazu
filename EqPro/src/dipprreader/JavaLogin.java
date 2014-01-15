
package dipprreader;


    
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
public class JavaLogin {
 
  private List<String> cookies;
  private HttpURLConnection conn;
 
  private final String USER_AGENT = "Mozilla/5.0";
 
  
  public String sendPost(String url, String postParams) throws Exception {
 
	URL obj = new URL(url);
	conn = (HttpURLConnection) obj.openConnection();
 
	// Acts like a browser
	conn.setUseCaches(false);
	conn.setRequestMethod("POST");
	//conn.setRequestProperty("Host", "accounts.google.com");
	conn.setRequestProperty("User-Agent", USER_AGENT);
	conn.setRequestProperty("Accept",
		"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
//	for (String cookie : this.cookies) {
//		conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
//	}
	conn.setRequestProperty("Connection", "keep-alive");
	//conn.setRequestProperty("Referer", "https://accounts.google.com/ServiceLoginAuth");
	conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
	conn.setRequestProperty("Content-Length", Integer.toString(postParams.length()));
 
	conn.setDoOutput(true);
	conn.setDoInput(true);
      try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
          wr.writeBytes(postParams);
          wr.flush();
      }
 
	int responseCode = conn.getResponseCode();
	System.out.println("\nSending 'POST' request to URL : " + url);
	System.out.println("Post parameters : " + postParams);
	//System.out.println("Response Code : " + responseCode);
//        try{
//            InputStream inputStream = conn.getInputStream();
//        }catch(IOException ex){
//            System.out.println("Error IOException " + ex.getMessage());
//        }
        
      try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream( )))) {
          String inputLine;
          StringBuilder response = new StringBuilder();
   
          while ((inputLine = in.readLine()) != null) {
                  response.append(inputLine);
          }
          return response.toString();
      }
 
  }
 
  public String GetPageContent(String url) throws Exception {
 
	URL obj = new URL(url);
        
	conn = (HttpURLConnection) obj.openConnection();
 
	// default is GET
	conn.setRequestMethod("GET");
 
	conn.setUseCaches(false);
 
	// act like a browser
	conn.setRequestProperty("User-Agent", USER_AGENT);
	conn.setRequestProperty("Accept",
		"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
	conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
	if (cookies != null) {
		for (String cookie : this.cookies) {
			conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
		}
	}
	int responseCode = conn.getResponseCode();
	System.out.println("\nSending 'GET' request to URL : " + url);
	//System.out.println("Response Code : " + responseCode);
      StringBuffer response;
      try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
          String inputLine;
          response = new StringBuffer();
          while ((inputLine = in.readLine()) != null) {
                  response.append(inputLine);
          }
      }
 
	// Get the response cookies
	setCookies(conn.getHeaderFields().get("Set-Cookie"));
 
	return response.toString();
 
  }
 
  public String getFormParams(String html, String username, String password)
		throws UnsupportedEncodingException {
 
	System.out.println("Extracting form's data...");
 
	Document doc = Jsoup.parse(html);
 
	// Google form id
        Element loginform = doc.select("form").first();
        
	
	Elements inputElements = loginform.getElementsByTag("input");
	List<String> paramList = new ArrayList<>();
	for (Element inputElement : inputElements) {
		String key = inputElement.attr("name");
		String value = inputElement.attr("value");
            switch (key) {
                case "frmUserID":
                    value = username;
                    break;
                case "frmUserPass":
                    value = password;
                    break;
            }
		paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
	}
 
	// build parameters list
	StringBuilder result = new StringBuilder();
	for (String param : paramList) {
		if (result.length() == 0) {
			result.append(param);
		} else {
			result.append("&").append(param);
		}
	}
	return result.toString();
  }
 
  public List<String> getCookies() {
	return cookies;
  }
 
  public void setCookies(List<String> cookies) {
	this.cookies = cookies;
  }

   public String getSearchFormParams(String html, 
            String componentName, String exact) throws UnsupportedEncodingException {
 
	System.out.println("Extracting form's data...");
 
	Document doc = Jsoup.parse(html);
 
	// Google form id
        Element loginform = doc.select("form").first();
        
	
	Elements inputElements = loginform.getElementsByTag("input");
	List<String> paramList = new ArrayList<>();
          boolean exactUsed = false;
	for (Element inputElement : inputElements) {
		String key = inputElement.attr("name");
		String value = inputElement.attr("value");
          
            switch (key) {
                case "Exactness":
                    if (exactUsed){
                        continue;
                    }
                    value = exact;
                    exactUsed = true;
                    break;
                case "C1":
                    value = componentName;
                    break;
            }
		paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
	}
 
	// build parameters list
	StringBuilder result = new StringBuilder();
	for (String param : paramList) {
		if (result.length() == 0) {
			result.append(param);
		} else {
			result.append("&").append(param);
		}
	}
	return result.toString();
    }
 
} 

