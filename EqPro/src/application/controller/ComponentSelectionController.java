
package application.controller;

import application.model.Eqfases2Copy;
import application.controller.EQController;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;
import termo.component.Component;

/**
 * FXML
 * Controller
 * class
 *
 * @author
 * Hugo
 */
public class ComponentSelectionController  extends EQController implements Initializable {
    @FXML protected ListView<Component> availableComponentsLV;
    @FXML protected ListView<Component> selectedComponentsLV;

    /**
     * Initializes
     * the
     * controller
     * class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
	    selectedComponentsLV.setItems(Eqfases2Copy.getComponents());   
	    ArrayList<Component> availableListFromFile = ComponentListLoader.readList();
	    availableComponentsLV.getItems().addAll(availableListFromFile);
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
    

    @Override
    public void done() {
    }
    @Override
    public void cancel(){
	
    }

  
    
}



class ComponentListLoader{
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
      DipprReader reader ;
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
     
     protected static ArrayList<Component> readList(){
	  ArrayList<Component> obj = new ArrayList<>();
	 try {//no deberia estar aqui, debe estar en el inicio del programa
		    XMLDecoder decoder = new XMLDecoder(
			new BufferedInputStream(
			    new FileInputStream("componentsList.xml")));
		   obj =(ArrayList<Component>) decoder.readObject();
		   
	}catch(Exception e){
	    System.out.println("problems reading file" + e.getMessage());
	}
	 
	 return obj;
	 
     }
}