
package application.controller;

import application.model.EQStage;
import application.model.Eqfases2Copy;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import termo.component.Component;
import termo.equilibrium.EquilibriaSolution;
import termo.equilibrium.MixtureEquilibriaPhaseSolution;
import termo.substance.HeterogeneousMixtureSubstance;
import termo.substance.MixtureSubstance;

/**
 * FXML
 * Controller
 * class
 *
 * @author
 * Hugo
 */
public class BinaryDiagramController  implements Initializable {
    @FXML RadioButton pressureRB;
    @FXML  RadioButton tempRB;
    @FXML ToggleGroup specificationTG;
    
    @FXML TextField valueTF;
    
    
    HeterogeneousMixtureSubstance mix ;
    
    /**
     * Initializes
     * the
     * controller
     * class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
	// TODO
	
    }    

    public ArrayList<EquilibriaSolution> diagramData(){
	
	ArrayList<Component> comp = new ArrayList();
//	for(Component component: Eqfases2Copy.getComponents()){
//	    comp.add(component);
//	}
//	
//	 mix = new HeterogeneousMixtureSubstance(Eqfases2Copy.getEos(), Eqfases2Copy.getAlpha(), Eqfases2Copy.getMixingRule(), comp);
//	
//	
//	
//	double value = Double.valueOf(valueTF.getText());
//	ArrayList<EquilibriaSolution> result = new ArrayList();
//	Toggle selectedToggle = specificationTG.getSelectedToggle();
//	if(selectedToggle.equals(pressureRB)){	    
//	   
//	    ArrayList<Component> subs = mix.getComponents();
//	    Component component1 = subs.get(0);
//	    Component component2 = subs.get(1);
//	   
//	   double numberCalculations = 100;
//
//	   double step = 1d / numberCalculations;
//
//	   for(double i= 0; i <= 1; i += step){
//	       
//	       mix.setZFraction(component1,i);
//	       mix.setZFraction(component2,1-i);
//
//	       MixtureEquilibriaPhaseSolution sol = (MixtureEquilibriaPhaseSolution)mix.bubbleTemperature(value);
//	       
//	       result.add(sol);
//
//	   }
//
//	}else if (selectedToggle.equals(tempRB)){
//	    
//	}
	//return result;
	return null;
    }
    
    
    @FXML public void done() {
	Eqfases2Copy.setDiagramData(diagramData());
	
	EQStage result = new EQStage("/application/BinaryDiagramResult.fxml");
	
	result.run();
    }
}
