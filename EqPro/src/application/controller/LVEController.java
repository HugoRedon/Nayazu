/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controller;

import application.model.CompositionTableItem;
import static application.model.VLEType.*;
import static application.model.MixingRuleType.*;
import application.model.Eqfases2Copy;
import application.model.EquationOfState;
import application.model.MixingRuleType;
import application.model.ThermoPackage;
import application.model.VLEType;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import termo.activityModel.NRTLActivityModel;
import termo.activityModel.WilsonActivityModel;
import termo.binaryParameter.InteractionParameter;
import termo.component.Component;
import termo.eos.Cubic;
import termo.eos.EquationOfStateFactory;
import termo.eos.alpha.Alpha;
import termo.eos.alpha.AlphaFactory;
import termo.eos.mixingRule.HuronVidalMixingRule;
import termo.eos.mixingRule.MathiasKlotzPrausnitzMixingRule;
import termo.eos.mixingRule.MixingRule;
import termo.eos.mixingRule.VDWMixingRule;
import termo.eos.mixingRule.WongSandlerMixingRule;
import termo.equilibrium.EquilibriaSolution;
import termo.equilibrium.MixtureEquilibriaPhaseSolution;
import termo.substance.HeterogeneousMixtureSubstance;
import termo.substance.HeterogeneousPureSubstance;
import termo.substance.HeterogenousSubstance;

/**
 * FXML
 * Controller
 * class
 *
 * @author
 * Hugo
 */
public class LVEController implements Initializable {

    @FXML RadioButton bubbleTRB;
    @FXML RadioButton dewTRB;
    @FXML RadioButton bubblePRB;
    @FXML RadioButton dewPRB;
    
    @FXML RadioButton flashTVFRB;
    @FXML RadioButton flashPVFRB;
    @FXML RadioButton flashTPRB;
    
    @FXML RadioButton flashPHRB;
    @FXML RadioButton flashPSRB;
    @FXML RadioButton flashTSRB;
    
    @FXML ToggleGroup c;
    
    @FXML TextField pressureTF;
    @FXML TextField valueTF;
    
    /**
     * Initializes
     * the
     * controller
     * class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
	bubbleTRB.setUserData(VLEType.BubbleT);
	dewTRB.setUserData(VLEType.DewT);
	bubblePRB.setUserData(VLEType.BubbleP);
	dewPRB.setUserData(VLEType.DewP);
	
	flashTVFRB.setUserData(VLEType.FlashTVF);
	flashPVFRB.setUserData(VLEType.FlashPVF);
	flashTPRB.setUserData(VLEType.FlashTP);
	
	flashPHRB.setUserData(VLEType.FlashPH);
	flashPSRB.setUserData(VLEType.FlashPS);
	flashTSRB.setUserData(VLEType.FlashTS);
	
    } 
    
    @FXML protected void calculate(ActionEvent event){
//	VLEType calculation = (VLEType)c.getSelectedToggle().getUserData();
//	calculation.setTemperatureOrPressure(Double.valueOf(pressureTF.getText()));
//	calculation.setSeconValue(Double.valueOf(valueTF.getText()));
	
	
	
	
	
	ThermoPackage pa = Eqfases2Copy.getThermoPackage();
	
	MixingRuleType mrType = pa.getMixingRule();
	ObservableList<Component> obsListComponents = Eqfases2Copy.getComponents();
	
	
	
	Cubic eos;
	Alpha alpha;
	MixingRule mixingRule = null;
	
	boolean isSymmetric = false;
	
	
	
	if(pa.getEquationOfState().equals(EquationOfState.PengRobinsonStryjekVera)){
	    eos = EquationOfStateFactory.pengRobinsonBase();
	    alpha = AlphaFactory.getStryjekAndVeraExpression();
	}else {//rksm
	    eos = EquationOfStateFactory.redlichKwongSoaveBase();
	    alpha = AlphaFactory.getMathiasExpression();
	}
	
	
	if(mrType.equals(VanDerWaals1)){
	    mixingRule = new VDWMixingRule();//symetric params 
	}else if(mrType == VanDerWaals2){
	    isSymmetric = true;
	    mixingRule = new VDWMixingRule();
	    //todo non symetric parameters??
	}else if(mrType == PanagiotopoulosReid){
	    
	}else if(mrType ==SandovalWilczecVeraVera ){
	    
	}else if(mrType == MathiasKlotzPrausnitz){
	    mixingRule = new MathiasKlotzPrausnitzMixingRule();
	}else if(mrType == HuronVidalWilson){
	    WilsonActivityModel w = new WilsonActivityModel();
	    mixingRule = new HuronVidalMixingRule(w, eos);
	}else if(mrType == HuronVidalNRTL){
	    NRTLActivityModel n = new NRTLActivityModel();
	    mixingRule = new HuronVidalMixingRule(n, eos);
	}else if(mrType == WongSandlerWilson){
	    WilsonActivityModel w = new WilsonActivityModel();
	    mixingRule = new WongSandlerMixingRule(w, eos);
	}else if(mrType == WongSandlerNRTL){
	    NRTLActivityModel n = new NRTLActivityModel();
	    mixingRule = new WongSandlerMixingRule(n, eos);
	}
	
	HeterogenousSubstance hs ;
	
	
	double pressure =Double.valueOf( pressureTF.getText());
	
	boolean isMixture = obsListComponents.size() > 1;
	
	if(isMixture){
	    ArrayList<Component> components = new ArrayList<>();
	    HashMap<Component,Double > fractions = new HashMap();
	    for(CompositionTableItem i:Eqfases2Copy.getCompositionItems()){
		fractions.put(i.getComponent(), Double.valueOf(i.getMolFractionValue()));
	    }
	    for(Component com: obsListComponents){
		components.add(com);
	    }
	InteractionParameter k = new InteractionParameter( isSymmetric) ;
	    
	    HeterogeneousMixtureSubstance mix =new HeterogeneousMixtureSubstance(eos, alpha, mixingRule, components, k);
	    mix.setzFractions(fractions);
		hs = mix;
		
		
	}else{//one component : PUresubstance
	    Component component = obsListComponents.get(0);
	    hs = new HeterogeneousPureSubstance(eos, alpha, component);
	}
	
	
	VLEType type = (VLEType)c.getSelectedToggle().getUserData();

	EquilibriaSolution sol = null;
	
	DecimalFormat format = new DecimalFormat("0.000000");
	String z = null;
	String zL = null;
	String zV = null;
	
	
	String H = null;
	String HL = null;
	String HV= null;
	
	String S = null;
	String SL = null;
	String SV = null;
	
	String G = null;
	String GL = null;
	String GV = null;
	
	String vol = null;
	String volL = null;
	String volV = null;
	
	if(type.equals(BubbleT)){
	    sol = hs.bubbleTemperature(pressure);
	    
	    z= format.format(hs.getLiquid().calculateCompresibilityFactor(sol.getTemperature(), pressure));
	    H=format.format(hs.getLiquid().calculateEnthalpy(sol.getTemperature(), pressure));
	    S = format.format(hs.getLiquid().calculateEntropy(sol.getTemperature(), pressure));
	    G = format.format(hs.getLiquid().calculateGibbs(sol.getTemperature(),pressure));
	    vol = format.format(hs.getLiquid().calculateMolarVolume(sol.getTemperature(), pressure));
	    
	    zL = z;
	    HL= H;
	    SL = S;
	    GL = G;
	    volL = vol;
	    
	   
	    
	     zV= format.format(hs.getLiquid().calculateCompresibilityFactor(sol.getTemperature(), pressure));
	    HV=format.format(hs.getVapor().calculateEnthalpy(sol.getTemperature(), pressure));
	    SV = format.format(hs.getVapor().calculateEntropy(sol.getTemperature(), pressure));
	    GV = format.format(hs.getVapor().calculateGibbs(sol.getTemperature(),pressure));
	    volV = format.format(hs.getVapor().calculateMolarVolume(sol.getTemperature(), pressure));
	    
	    
	    
	}
	
	Stage stage = new Stage();
	
	//Parent parent = FXMLLoader.load(getClass().getResource("/application.LVEResult.fxml"))
	
	VBox v = new VBox(15);
	v.setFillWidth(true);
	
	
	GridPane grid = new GridPane( );
	grid.setHgap(15);
	
	
	addRow(grid, 0, "Equilibrio de fases");
	addRow(grid, 1, "Ecuación de estado",pa.getEquationOfState().toString());
	addRow(grid, 2, "Regla de mezclado",pa.getMixingRule().toString());
	
	addRow(grid,3, "Estado de referencia","T = 298.15 K , P = 1 ATM");
	addRow(grid,4,"Iteraciones requeridas",sol.getIterations().toString() );
	
	GridPane grid2 = new GridPane();
	grid2.setHgap(15);
	
	addRow(grid2,0,"Unidades");
	addRow(grid2,1, "Temperatura", "T","K");
	addRow(grid2,2, "Presión", "P","ATM");
	addRow(grid2,3, "Entalpia", "H","J/kgMol");
	addRow(grid2,4, "Entropia", "s","J/kgMol-K");
	addRow(grid2,5,"Engergía de Gibbs","G", "J/kgMol");
	addRow(grid2,6, "Volumen molar","V", "m^3 / kgMol");
	
	GridPane grid3 = new GridPane();
	grid3.setHgap(15);
	
	addRow(grid3,0, "Temperatura=", sol.getTemperature().toString());
	addRow(grid3, 1, "Presión=",sol.getPressure().toString());
	addRow(grid3, 2, "Relación V/F=",sol.getvFRelation().toString() );

	GridPane grid4 = new GridPane();
	grid4.setHgap(15);
	
	grid4.add(new Text("Propiedades termodinámicas"),0,0,4,1 );
	addRow(grid4, 1, "","Total","Vapor","Líquido");
	addRow(grid4, 2, "Z=",z,zV,zL);
	addRow(grid4,3,"H=",H,HV,HL);
	addRow(grid4, 4, "S=",S,SV,SL);
	addRow(grid4, 5, "G=",G,GV,GL);
	addRow(grid4, 6, "V=",vol,volV,volL);
	
	
	GridPane grid5 = new GridPane();
	grid5.setHgap(15);
	
	grid5.add(new Text("Composición"), 0, 0,4,1);
	addRow(grid5, 1, "Compuesto:","Global","Líquido","Vapor");
	int row =2;
	if(isMixture){
	    HeterogeneousMixtureSubstance substanceMix = (HeterogeneousMixtureSubstance)hs;
	    for (Component component : obsListComponents){
		String zF =format.format( substanceMix.getzFractions().get(component));
		String x = format.format(substanceMix.getLiquid().getReadOnlyFractions().get(component));
		String y = format.format( substanceMix.getVapor().getReadOnlyFractions().get(component));
		addRow(grid5, row++, component.getName(),zF,x,y);
		
	    }
	}else{
	    HeterogeneousPureSubstance puresub = (HeterogeneousPureSubstance)hs;
	    
	    
	    
	    
	}
	
	GridPane grid6 = new GridPane();
	grid6.setHgap(15);
		
	grid6.add(new Text("Constantes de equilibrio"), 0, 0,4,1);
	addRow(grid6, 1, "Compuesto", "K Equilibrio","\u03A6 Líquido","\u03A6 Vapor");
	 row = 2;
	
	if(isMixture){
	    HeterogeneousMixtureSubstance substanceMix = (HeterogeneousMixtureSubstance)hs;
	    
	    for (Component component: obsListComponents){
		String keq = format.format(substanceMix.equilibriumRelation(component, sol.getTemperature(), pressure));
		String fugL = format.format(substanceMix.getLiquid().calculateFugacity(component, sol.getTemperature(), pressure));
		String fugV = format.format(substanceMix.getVapor().calculateFugacity(component, sol.getTemperature(), pressure));
		
		addRow(grid6, row++,component.toString(),keq ,fugL,fugV);
		
	}
	}else{
	    HeterogeneousPureSubstance puresub = (HeterogeneousPureSubstance)hs;
	    String keq = format.format(puresub.equilibriaRelation(sol.getTemperature(), pressure));
	    String fugL = format.format(puresub.getLiquid().calculateFugacity(sol.getTemperature(), pressure));
	    String fugV = format.format(puresub.getVapor().calculateFugacity(sol.getTemperature(), pressure));
	    addRow(grid6, row, puresub.getComponent().toString(), fugL,fugV);
	    
	}
	
	GridPane grid7 = new GridPane();
	grid7.setHgap(15);
	
	grid7.add(new Text("Valores de inicialización"), 0, 0, 4,1);
	addRow(grid7, 1, format.format(sol.getEstimateSolution().getTemperature()));
	
//	GridPane grid8 = new GridPane();
//	grid8.setHgap(15);
//	
//	grid8.add(new Text("Composición"), 0, 0, 4,1);
//	addRow(grid8, 1, "Compuesto:","Global","Líquido","Vapor");
//	row = 2;
//	if(isMixture){
//	    MixtureEquilibriaPhaseSolution solu = (MixtureEquilibriaPhaseSolution)sol;
//	    
//	    for(Component component: obsListComponents){
//		addRow(grid8, row++, component.toString(), 
//			sol.getEstimateSolution().);
//	    }
//	}
	
	
	
	v.getChildren().addAll(grid, grid2,grid3,grid4,grid5,grid6,grid7);
	
	Scene scene= new Scene(v, 300, 200);
	
	stage.setScene(scene);
	stage.show();
	
	
    }
    
    
    private void addRow(GridPane pane,int row ,String... values ){
	Text[] nodes = new Text[values.length];
	for(int i =0; i < values.length;i++){
	    nodes[i] = new Text(values[i]);
	}
	pane.addRow(row, nodes);
    }
    
    
    
    
}



class EquilibriaResult{
    private double iterations;

    /**
     * @return the iterations
     */
    public double getIterations() {
	return iterations;
    }

    /**
     * @param iterations the iterations to set
     */
    public void setIterations(double iterations) {
	this.iterations = iterations;
    }
}

