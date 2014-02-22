package application.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import termo.component.Component;
import termo.eos.Cubic;
import termo.eos.alpha.Alpha;
import termo.eos.mixingRule.MixingRule;
import termo.equilibrium.EquilibriaSolution;
import termo.substance.MixtureSubstance;
import termo.substance.PureSubstance;
import termo.substance.HeterogenousSubstance;
import termo.substance.SubstanceType;

/**
 *
 * @author
 * Hugo
 */
public class Eqfases2Copy extends Application implements ListChangeListener<Component>{
    
    private static ThermoPackage thermoPackage = new ThermoPackage();
    
    
    
    
    
    
    
    private static Stage primaryStage;
//    private static Cubic eos ;
//    private static Alpha alpha;
    private static MixingRule mixingRule;
    private static SubstanceType type; 
    
    
//    private static ThermoPackage equationOfState;
    
    
    private static ObservableList<Component> components = FXCollections.observableArrayList();


    public static  void setInerWindow(Stage stage){
	stage.initOwner(primaryStage);
	
    }
    
    private static ObservableList<CompositionTableItem> compositionItems = FXCollections.observableArrayList();
    private static HashMap<Component,CompositionTableItem> compositionMap = new HashMap();
       
    public static ObservableList<Component> getComponents() {
	return components;
    }
    public static void setComponents(ObservableList<Component> components){
	Eqfases2Copy.components = components;
    }
    
    public static ObservableList<CompositionTableItem> getCompositionItems() {
	return Eqfases2Copy.compositionItems;
    }
    public static void setCompositionItems(ObservableList<CompositionTableItem> aCompositionItems) {
	Eqfases2Copy.compositionItems = aCompositionItems;
    }

    private static ArrayList<EquilibriaSolution> diagramData;
    
    public static HeterogenousSubstance getSubstance() {

	
//	ArrayList<Component> compos = new ArrayList();
//	
//	for (Component component : components){
//	    compos.add(component);
//	}
//	
//	HeterogenousSubstance substance = null ;
//	if(components.size() == 1 ){
//	    PureSubstance pureSubstance = new PureSubstance(
//		    eos,
//		    alpha,
//		    components.get(0));
//	    substance = pureSubstance;
//	}else if(components.size() > 1){
//	    MixtureSubstance mixture = new MixtureSubstance(
//		    eos, 
//		    alpha, 
//		    mixingRule, 
//		    compos);
//	    
//
//	    substance = mixture ;
//	}
	
	return null;
	
    }

    /**
     * @return the eos
     */
//    public static Cubic getEos() {
//	return eos;
//    }
//
//    /**
//     * @param aEos the eos to set
//     */
//    public static void setEos(Cubic aEos) {
//	eos = aEos;
//    }
//
//    /**
//     * @return the alpha
//     */
//    public static Alpha getAlpha() {
//	return alpha;
//    }
//
//    /**
//     * @param aAlpha the alpha to set
//     */
//    public static void setAlpha(Alpha aAlpha) {
//	alpha = aAlpha;
//    }

    /**
     * @return the mixingRule
     */
    public static MixingRule getMixingRule() {
	return mixingRule;
    }

    /**
     * @param aMixingRule the mixingRule to set
     */
    public static void setMixingRule(MixingRule aMixingRule) {
	mixingRule = aMixingRule;
    }

    /**
     * @return the onePhasePropertiesResult
     */
    public static OnePhasePropertiesResult getOnePhasePropertiesResult() {
	return onePhasePropertiesResult;
    }

    /**
     * @param aOnePhasePropertiesResult the onePhasePropertiesResult to set
     */
    public static void setOnePhasePropertiesResult(OnePhasePropertiesResult aOnePhasePropertiesResult) {
	onePhasePropertiesResult = aOnePhasePropertiesResult;
    }

    public static void setDiagramData(ArrayList<EquilibriaSolution> aDiagramData) {
	diagramData = aDiagramData;
    }

    /**
     * @return the diagramData
     */
    public static ArrayList<EquilibriaSolution> getDiagramData() {
	return diagramData;
    }

    /**
     * @return the thermoPackage
     */
    public static ThermoPackage getThermoPackage() {
	return thermoPackage;
    }

    /**
     * @param aThermoPackage the thermoPackage to set
     */
    public static void setThermoPackage(ThermoPackage aThermoPackage) {
	thermoPackage = aThermoPackage;
    }
    
      @Override
    public void onChanged(ListChangeListener.Change<? extends Component> change) {
	
	while(change.next()){
	    if(change.wasAdded()){
		System.out.println("Component added");
		for(Component component: change.getAddedSubList()){
		    CompositionTableItem composition = new CompositionTableItem();
		    composition.setMolFractionValue("0.0");
		    composition.setComponentName(component.getName());
		    composition.setComponent(component);
		    compositionMap.put(component, composition);
		    compositionItems.add(composition);
		}
	    }if(change.wasRemoved()){
		for(Component component: change.getRemoved()){
		    compositionItems.remove(compositionMap.get(component));
		}
	    }
	    
	
	}
	
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
	
	components.addListener(this);
	Eqfases2Copy.primaryStage = primaryStage;
	Parent p = FXMLLoader.load(getClass().getResource("/application/eqfases2.fxml"));
	Scene scene = new Scene(p);
	primaryStage.setScene(scene);
	primaryStage.show();
    }
    
    
 
    private static OnePhasePropertiesResult onePhasePropertiesResult;

    /**
     * The
     * main()
     * method
     * is
     * ignored
     * in
     * correctly
     * deployed
     * JavaFX
     * application.
     * main()
     * serves
     * only
     * as
     * fallback
     * in
     * case
     * the
     * application
     * can
     * not
     * be
     * launched
     * through
     * deployment
     * artifacts,
     * e.g.,
     * in
     * IDEs
     * with
     * limited
     * FX
     * support.
     * NetBeans
     * ignores
     * main().
     *
     * @param
     * args
     * the
     * command
     * line
     * arguments
     */
    public static void main(String[] args) {
	launch(args);
    }

   

}


