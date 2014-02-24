/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application.controller;

import static application.model.VLEType.*;
import application.model.Eqfases2Copy;
import application.model.ThermoPackage;
import application.model.VLEType;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import termo.component.Component;
import termo.equilibrium.EquilibriaSolution;
import termo.substance.HeterogeneousMixtureSubstance;
import termo.substance.HeterogeneousPureSubstance;
import termo.substance.HeterogeneousSubstance;















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
	ThermoPackage pa = Eqfases2Copy.getThermoPackage();
	HeterogeneousSubstance hs = pa.getHeterogeneousSubstance();
	ObservableList<Component > obsListComponents = Eqfases2Copy.getComponents();
	boolean isMixture = obsListComponents.size() > 1? true:false;
	double pressure =Double.valueOf( pressureTF.getText());
	VLEType type = (VLEType)c.getSelectedToggle().getUserData();

	showResult(isMixture,type,hs,pressure,pa);
	
	
    }
    
    public void showResult(
	    boolean isMixture,
	    VLEType type,
	    HeterogeneousSubstance hs,
	    double pressure,
	    ThermoPackage pa){
	
	
	
	//EquilibriaSolution sol = null;
	ObservableList<Component > obsListComponents = Eqfases2Copy.getComponents();
	

	
	if(type.equals(BubbleT)){
	    int iter = hs.bubbleTemperature(pressure);
	    
	    
	    Result r = new Result(iter, pa, hs,new ArrayList<>(obsListComponents));
	    showWindow(r);
	   
	    
	}else if(type.equals(DewT)){
	    //int iter= hs.dew
	}
	
	
	
	
    }
    
    
    private void showWindow(Result r){
	Stage stage = new Stage();
	VBox v = new VBox(15);
	v.setFillWidth(true);
	v.setPadding(new Insets(10, 10, 10, 10));	
	GridPane grid = new GridPane( );
	grid.setHgap(15);
	
	addRow(grid, 0, "Equilibrio de fases");
	addRow(grid, 1, "Ecuación de estado",r.getEquationOfState());
	addRow(grid, 2, "Regla de mezclado",r.getMixingRule());
	
	addRow(grid,3, "Estado de referencia","T = 298.15 K , P = 1 ATM");
	addRow(grid,4,"Iteraciones requeridas",r.getIterations());
	
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
	
	addRow(grid3,0, "Temperatura=", r.getTemperature());
	addRow(grid3, 1, "Presión=",r.getPressure());
	addRow(grid3, 2, "Relación V/F=",r.getVFRelation());

	GridPane grid4 = new GridPane();
	grid4.setHgap(15);
	
	grid4.add(new Text("Propiedades termodinámicas"),0,0,4,1 );
	addRow(grid4, 1, "","Total","Vapor","Líquido");
	addRow(grid4, 2, "Z=",r.getZ(),r.getZVapor(),r.getZLiquid());
	addRow(grid4,3,"H=",r.getEnthalpy(),r.getVaporEnthalpy(),r.getLiquidEnthalpy());
	addRow(grid4, 4, "S=",r.getEntropy(),r.getVaporEntropy(),r.getLiquidEntropy());
	addRow(grid4, 5, "G=",r.getGibbs(),r.getVaporGibbs(),r.getLiquidGibbs());
	addRow(grid4, 6, "V=",r.getVolume(),r.getVaporVolume(),r.getLiquidVolume());
	
	
	GridPane grid5 = new GridPane();
	grid5.setHgap(15);
	
	grid5.add(new Text("Composición"), 0, 0,4,1);
	addRow(grid5, 1, "Compuesto:","Global","Líquido","Vapor");
	int row =2;
	if(r.isMixture()){
	    
	    for (Component component : r.getListComponents()){	
		addRow(grid5, row++, component.getName(),r.getZFraction(component),r.getX(component),r.getY(component));
		
	    }
	}else{
	    
	    addRow(grid5, row, r.getListComponents().get(0).toString(),"1","1","1");

	}
	
	GridPane grid6 = new GridPane();
	grid6.setHgap(15);
		
	grid6.add(new Text("Constantes de equilibrio"), 0, 0,4,1);
	addRow(grid6, 1, "Compuesto", "K Equilibrio","\u03A6 Líquido","\u03A6 Vapor");
	 row = 2;
	
	if(r.isMixture()){
	    for (Component component: r.getListComponents()){
		addRow(grid6, row++,component.toString(),r.getEquilibriumConstant(component) ,r.getLiquidFugacity(component),r.getVaporFugacity(component));
		
	}
	}else{
	   Component component =r.getListComponents().get(0);
	    addRow(grid6, row, component.toString(),r.getEquilibriumConstant(component), r.getLiquidFugacity(component),r.getVaporFugacity(component));
	    
	}
	
	GridPane grid7 = new GridPane();
	grid7.setHgap(15);
	
	
	
	grid7.add(new Text("Valores de inicialización"), 0, 0, 4,1);
	addRow(grid7, 1,r.getTemperatureEstimate() );
	
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
class Result{
//    private Double temperature;
//    private Double pressure;
    private ThermoPackage pa;
//    private EquilibriaSolution sol;
    private ArrayList<Component> listComponents = new ArrayList<>();
    private boolean mixture;
    private HeterogeneousSubstance hs;
    private int iterations;
    
    public Result(
	    int iterations,
	    ThermoPackage pa,
	    HeterogeneousSubstance hs,
	    ArrayList<Component> obsListComponents){
	this.hs = hs;
	this.iterations = iterations;
//	sol = solution;
//	temperature = solution.getTemperature();
//	pressure = solution.getPressure();
	this.pa = pa;
	
	listComponents = obsListComponents;
	
	mixture = listComponents.size() > 1?true:false;
    }
    
    private DecimalFormat format = new DecimalFormat("0.00000");
    private String format(double number){
	return format.format(number);
    }
    public String getEquationOfState() {
	return pa.getEquationOfState().toString();
    }

    public String getMixingRule() {
	return pa.getMixingRule().toString();
    }
    
    String getIterations() {
	return format(iterations);
    }

    public String getTemperature() {
	return format(hs.getTemperature());
    }

    String getPressure() {
	return format(hs.getPressure());
    }

    String getVFRelation() {
	return format(sol.getvFRelation());
    }

    boolean isMixture() {
	return mixture;
    }

    public String getZ() {
	return getZLiquid();//check vf this only works for bubble case
    }

    public String getZVapor() {
	return format(hs.getVapor().calculateCompresibilityFactor());
    }

    String getZLiquid() {
	return format(hs.getLiquid().calculateCompresibilityFactor());
    }

    String getEnthalpy() {
	return getLiquidEnthalpy();
    }

    String getVaporEnthalpy() {
	return format(hs.getVapor().calculateEnthalpy());
    }

    String getLiquidEnthalpy() {
	return format(hs.getLiquid().calculateEnthalpy());
    }

    String getEntropy() {
	return getLiquidEnthalpy();
    }

    String getVaporEntropy() {
	return format(hs.getVapor().calculateEntropy());
    }

    String getLiquidEntropy() {
	return format(hs.getLiquid().calculateEntropy());
    }

    String getGibbs() {
	return getLiquidGibbs();
    }

    String getVaporGibbs() {
	return format(hs.getVapor().calculateGibbs());
    }

    String getLiquidGibbs() {
	return format.format(hs.getLiquid().calculateGibbs());
    }

    String getVolume() {
	return getLiquidVolume();
    }

    String getVaporVolume() {
	return format(hs.getVapor().calculateMolarVolume());
    }

    String getLiquidVolume() {
	 return format.format(hs.getLiquid().calculateMolarVolume());
    }

    ArrayList<Component> getListComponents() {
	return listComponents;
    }

    String getZFraction(Component component) {
	if(isMixture()){
	    HeterogeneousMixtureSubstance mix = (HeterogeneousMixtureSubstance)hs;
	    return format(mix.getzFractions().get(component));
	}else{
	    return "1";
	}
	  
    }

    
    String getX(Component component) {
	if(isMixture()){
	    HeterogeneousMixtureSubstance mix = (HeterogeneousMixtureSubstance)hs;
	    return format(mix.getLiquid().getReadOnlyFractions().get(component));
	}else{
	    return "1";
	}
	
    }

    String getY(Component component) {
	if(isMixture()){
	    HeterogeneousMixtureSubstance mix = (HeterogeneousMixtureSubstance)hs;
	    return format(mix.getVapor().getReadOnlyFractions().get(component));
	}else{
	    return "1";
	}
    }

		
		
    String getEquilibriumConstant(Component component) {
	if(isMixture()){
	    HeterogeneousMixtureSubstance mix = (HeterogeneousMixtureSubstance)hs;
	    return format(mix.equilibriumRelation(component));
	}else{
	    HeterogeneousPureSubstance puresub = (HeterogeneousPureSubstance)hs;
	    return format(puresub.equilibriaRelation(iterations, iterations));
	}
    }

    String getLiquidFugacity(Component component) {
	if(isMixture()){
	    HeterogeneousMixtureSubstance mix = (HeterogeneousMixtureSubstance)hs;
	    return format(mix.getLiquid().calculateFugacity(component, temperature, pressure));
	}else{
	    HeterogeneousPureSubstance puresub = (HeterogeneousPureSubstance)hs;
	    return format(puresub.getLiquid().calculateFugacity(temperature, pressure));
	}
    }

    String getVaporFugacity(Component component) {
	if(isMixture()){
	    HeterogeneousMixtureSubstance mix = (HeterogeneousMixtureSubstance)hs;
	    return format(mix.getVapor().calculateFugacity(component, temperature, pressure));
	}else{
	    HeterogeneousPureSubstance puresub = (HeterogeneousPureSubstance)hs;
	    return format(puresub.getVapor().calculateFugacity(temperature, pressure));
	}
    }

    String getTemperatureEstimate() {
	return format(sol.getEstimateTemperature());
    }

    
	
}

