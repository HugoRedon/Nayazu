package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import termo.component.BinaryInteractionParameters;
//import termo.component.BinaryInteractionParameters;
import termo.component.Component;

/**
 *
 * @author Hugo Redon Rivera
 */
public class ComponentSelectionController implements Initializable {
    
    @FXML ComboBox components;
    @FXML TableView componentTableView;
    @FXML TableColumn parametersTC;
    @FXML TableColumn valuesTC;
    @FXML TableColumn unitsTC;
    @Override public void initialize(URL location, ResourceBundle resources) {
        Component n_heptane = new Component(1, "n-Heptane",0.35d, 27.4d, 540.2d, 0.432d);
        n_heptane.setPrsvk1(0.0722d);
        
        //evaporación relativa
        
        Component methanol = new Component(2, "Methanol", 0.55699, 72.80, 512.598, 0.118d);
        //methanol.setPrsvk1(-0.163374);
        methanol.setPrsvk1(0.39379);
        
        Component n_hexane = new Component(3,"n-Hexane", 0.305d,29 , 507.4d, 0.368d);
        n_hexane.setPrsvk1(0.07);
        
        Component water = new Component(4, "Water", 0.344d, 218.307d, 647.299d, 0.0571d);
       // water.setPrsvk1(-0.06655d);
        water.setPrsvk1(-0.0767);
        
        Component ethane = new Component(5, "Ethane", 0.099d, 48.72, 305.32, 0d);
        
        
        Component propane = new Component(6, "Propane", 0.152, 42.48, 369.83, 0);
        propane.setPrsvk1(0.03136d);
        
        Component cyclohexane = new Component(7,"Cyclohexane",0.319,3120*0.0098692327 ,659,0);
          
        components.getItems().addAll(n_heptane,ethane,propane,methanol,n_hexane,water,cyclohexane);
        components.setValue(methanol);
        
        BinaryInteractionParameters kinteraction = new BinaryInteractionParameters();
        
        kinteraction.setValue(ethane, propane, 0.0011, true);
        kinteraction.setValue(ethane, n_heptane, 0.0067, true);
        kinteraction.setValue(propane, n_heptane, 0.0056, true);
        kinteraction.setValue(propane, methanol, 0.0451, true);
        kinteraction.setValue(methanol, n_heptane, 0.08, true);
        kinteraction.setValue(methanol, n_hexane, 0.051, true);
        kinteraction.setValue(methanol, water, -0.0778, true);
        kinteraction.setValue(n_hexane, n_heptane, -0.009, true);
        kinteraction.setValue(water, n_heptane, 0.5, true);        
        kinteraction.setValue(water, n_hexane, 0.5, true);
        
        UserProperties.setInteractionParameters(kinteraction);
        componentTableView.setVisible(false);
       
    }
   
    @FXML public void addComponent(ActionEvent event) {       
        UserProperties.addComponent((Component)components.getValue());      
    }
}
