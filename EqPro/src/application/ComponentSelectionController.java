
package application;

import dipprreader.DipprReader;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import termo.component.Component;

/**
 * FXML
 * Controller
 * class
 *
 * @author
 * Hugo
 */
public class ComponentSelectionController implements Initializable {
    @FXML protected ListView<Component> availableComponentsLV;
    @FXML protected ListView<Component> selectedComponentsLV;
    DipprReader reader ;
    
    String availableComponents = "methane, ethane, propane, isobutane," +
"n-butane, n-pentane, n-hexane, n-heptane," +
"n-octane,  n-nonane," +
"n-decane, cyclohexane, ethylene, propylene," +
"1-butene, cis-2-butene, trans-2-butene," +
"acetylene, benzene, toluene, ethylbenzene," +
"o-xylene, m-xylene, p-xylene, cumene," +
"styrene, naphthalene, oxygen, hydrogen," +
"carbon monoxide, carbon dioxide, sulfur dioxide," +
"nitric oxide, formaldehyde, acetylaldehyde," +
"acetone, methyl ethyl ketone, methanol, ethanol," +
"1-propanol, 1-butanol, acetic acid, methyl acetate," +
"ethyl acetate, diethyl ether, carbon tetrachloride," +
"chloroform, methylamine, ethylamine, sulfuric acid," +
"ammonia, sodium hydroxide, water, hydrogen sulfide";

    /**
     * Initializes
     * the
     * controller
     * class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
	//createList();
	
	//ArrayList<Component> components = 
	//if(components != null){
	    selectedComponentsLV.setItems(Eqfases2Copy.getComponents());
	//}
		try {//no deberia estar aqui, debe estar en el inicio del programa
		    XMLDecoder decoder = new XMLDecoder(
			new BufferedInputStream(
			    new FileInputStream("componentsList.xml")));
		    ArrayList<Component> obj =(ArrayList<Component>) decoder.readObject();
		    availableComponentsLV.getItems().addAll(obj);
		}catch(Exception e){
		    System.out.println("problems reading file" + e.getMessage());
		}
    }    
    
    protected void createList(){
	reader = new DipprReader();
	String[] components =availableComponents.split(",");

	
	ArrayList<Component> createList = new ArrayList();
	
		for (int i = 0; i < components.length ;i++){
		     String component = components[i];
			try {
			    Component com = reader.getComponent(component);
			   //  availableComponentsLV.getItems().add(com);
			     createList.add(com);

			} catch (Exception ex) {
			    System.out.println("No se pudo cargar el componente " + component);
			    Logger.getLogger(ComponentSelectionController.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		
		    try{
		   XMLEncoder encoder = new XMLEncoder(
			   new BufferedOutputStream(
			   new FileOutputStream("componentsList.xml")));
			     encoder.writeObject(createList);
			     encoder.close();
		    }catch(Exception e){
			System.out.println("is the file problem");
		    }     
	
	
    }
    
     @FXML protected void selectComponentB(ActionEvent event){
	selectComponent();
    }
     @FXML protected void selectComponent(MouseEvent event){
	if(event.getClickCount() ==2){
	   selectComponent();
	}
    }
     protected void selectComponent(){
	    Component com = availableComponentsLV.getSelectionModel().getSelectedItem();
	if (com != null){
	    if(!selectedComponentsLV.getItems().contains(com)){
		selectedComponentsLV.getItems().add(com);
	    }
	}
	}
	
    @FXML protected void unSelectComponentB(ActionEvent event){
	unSelectComponent();
    }
    @FXML protected void unSelectComponent(MouseEvent event){
	if(event.getClickCount() ==2 ){
	  unSelectComponent();
	}
    }
    protected void unSelectComponent(){
	Component com = selectedComponentsLV.getSelectionModel().getSelectedItem();
	if(com!=null){
	    selectedComponentsLV.getItems().remove(com);
	}
    }
    
    
    @FXML protected void cancel(ActionEvent event){
	//cancel list
	//close
	System.out.println("cancelando");
	
	close(event);
    }
    @FXML protected void done(ActionEvent event){
//	ArrayList<Component> components = new ArrayList();
//		
//	for (Component comp : selectedComponentsLV.getItems()){
//	    components.add(comp);
//	}
//	Eqfases2Copy.setComponents(components);
//	
//	
	//close
	System.out.println("componentes agregados");
	
	close(event);
    }
    
    public void close(ActionEvent event){
	Node node = (Node)event.getSource();
	Stage stage =(Stage) node.getScene().getWindow();
	//stage.getOnCloseRequest().handle(null); 
	stage.close();
    }
  
    
}
