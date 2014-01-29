/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import dipprreader.DipprReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
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
    @FXML protected ListView availableComponentsLV;
    DipprReader reader = new DipprReader();
    
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
	String[] components =availableComponents.split(",");
	
	
	
	
	
	
	

//    for (final Object o : list) {
//        exec.submit(new Runnable() {
//            @Override
//            public void run() {
//                // do stuff with o.
//            }
//        });
//    }
//} 


	
    
	 
		ExecutorService exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		
		
		for (final String  component: components){
		    exec.submit(new Runnable(){
		    @Override
			public void run(){
			try {
			    Component com = reader.getComponent(component);
			     availableComponentsLV.getItems().add(com);
			} catch (Exception ex) {
			    System.out.println("No se pudo cargar el componente " + component);
			    Logger.getLogger(ComponentSelectionController.class.getName()).log(Level.SEVERE, null, ex);
			}
			}
		    });
		    
		}
	
	    
	
    }    
}
