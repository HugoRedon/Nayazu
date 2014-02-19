
package application.controller;

import application.model.Eqfases2Copy;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import termo.component.Component;
import termo.equilibrium.EquilibriaSolution;
import termo.equilibrium.MixtureEquilibriaPhaseSolution;
import termo.substance.MixtureSubstance;

/**
 * FXML
 * Controller
 * class
 *
 * @author
 * Hugo
 */
public class BinaryDiagramResultController  implements Initializable {

    @FXML LineChart<Number,Number> diagram;
    /**
     * Initializes
     * the
     * controller
     * class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
	diagramTypeXY();
    }    
    
    
    protected void diagramTypeXY(){
	 XYChart.Series series = new XYChart.Series();
	 ArrayList<EquilibriaSolution> solution = Eqfases2Copy.getDiagramData();
	
	
	MixtureSubstance mixturesubstance = (MixtureSubstance)Eqfases2Copy.getSubstance();
	//PureSubstance sub1 = mixturesubstance.getPureSubstances().get(0);
	 Component sub1 = Eqfases2Copy.getComponents().get(0);
        for(EquilibriaSolution sol : solution){
	    MixtureEquilibriaPhaseSolution mix = (MixtureEquilibriaPhaseSolution)sol;
   
	    series.getData().add(new XYChart.Data(mix.getLiquidFractions().get(sub1), mix.getVaporFractions().get(sub1)));
	    System.out.println(mix.getLiquidFractions().get(sub1)+"," + mix.getVaporFractions().get(sub1));
	    
	}

	diagram.getData().add(series);
    }
    
    @FXML protected void diagramTypeXY(ActionEvent event){
	diagram.getData().clear();
	diagramTypeXY();
    }
    
    @FXML protected void diagramaTypeTXY(ActionEvent event){
	diagram.getData().clear();
	XYChart.Series lseries = new XYChart.Series();
	XYChart.Series vseries = new XYChart.Series();
	
	
	 
	 ArrayList<EquilibriaSolution> solution = Eqfases2Copy.getDiagramData();
	MixtureEquilibriaPhaseSolution aa = (MixtureEquilibriaPhaseSolution)solution.get(0);
	
	MixtureSubstance mixturesubstance = (MixtureSubstance)Eqfases2Copy.getSubstance();
	Component sub1 = Eqfases2Copy.getComponents().get(0);//mixturesubstance.getPureSubstances().get(0);
	 
        for(EquilibriaSolution sol : solution){
	    MixtureEquilibriaPhaseSolution mix = (MixtureEquilibriaPhaseSolution)sol;
   
	    lseries.getData().add(new XYChart.Data(mix.getLiquidFractions().get(sub1), mix.getTemperature()));
	    vseries.getData().add(new XYChart.Data(mix.getVaporFractions().get(sub1), mix.getTemperature()));
	   // System.out.println(mix.getLiquidFractions().get(sub1)+"," + mix.getVaporFractions().get(sub1));
	    
	}
	
	diagram.getData().addAll(lseries,vseries);
	
    }
}
