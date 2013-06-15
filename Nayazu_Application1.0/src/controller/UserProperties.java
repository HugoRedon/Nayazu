package controller;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import termo.binaryParameter.BinaryInteractionParameter;
import termo.component.Component;
import termo.data.ExperimentalDataBinary;
import termo.eos.EOS;
import termo.eos.IdealGas;
import termo.eos.VanDerWaals;
import termo.eos.mixingRule.MixingRule;
import termo.eos.mixingRule.VDWMixingRule;


/**
 *
 * @author Hugo Redon Rivera
 */
public class UserProperties  {
    
    /** For experimental data **/
    
    private static Component  component1;
    private static Component component2;
    
  private static  ObservableList<Component> forBinaryMixture = FXCollections.observableArrayList();
    
  private static ArrayList<ExperimentalDataBinary> experimentalData;
  
  public static void setExperimentalDataArrayList(ArrayList<ExperimentalDataBinary> experimentalPoints){
      experimentalData = experimentalPoints;
  }
  public static ArrayList<ExperimentalDataBinary> getExperimentalDataArrayList(){
      return experimentalData;
  }
  
    public static Component getComponent1(){
        return component1;
    }
    public static void setComponent1(Component aReferenceComponent){
        component1 = aReferenceComponent;
    }
    
    public static Component getComponent2(){
        return component2;
    }
    public static void setComponent2(Component aNonReferenceComponent){
        component2 = aNonReferenceComponent;
    }
    
    public static void setBinaryComponentsObservable(ObservableList<Component> mixtureComponents){
        forBinaryMixture.addAll(mixtureComponents);// = mixtureComponents;
    }
    public static ObservableList<Component> getBinaryComponentsObservable(){
        return forBinaryMixture;
    }
    
    
  /**================================ Mixing Rule =======================================================**/
    private static SimpleBooleanProperty isMixingRuleSelected = new SimpleBooleanProperty(false);
    private static MixingRule selectedMixingRule;
    private static BinaryInteractionParameter kinteraction;
    
    public static SimpleBooleanProperty isMixingRuleSelected(){
      
        return isMixingRuleSelected;
    }
    
    public static void setSelectedMixingRule(MixingRule aSelectedMixingRule){
        selectedMixingRule = aSelectedMixingRule;
        isMixingRuleSelected.set(true);
    }
    public static MixingRule getSelectedMixingRule(){
        return selectedMixingRule;
    }
    
    public static BinaryInteractionParameter getInteractionParameters(){
        return kinteraction;
    }
    public static void setInteractionParameters(BinaryInteractionParameter aKinteraction){
        kinteraction = aKinteraction;
    }
    
    
/**================================ Package =======================================================**/
    private static EOS selectedEOS;
    private static SimpleBooleanProperty needsComponents = new SimpleBooleanProperty(false);
    private static SimpleBooleanProperty isPackageSelected = new SimpleBooleanProperty(false);
    private static SimpleStringProperty packageText = new SimpleStringProperty("");
    
      public static void setSelectedPackage(Object obj){
        
        selectedEOS = (EOS)obj;
        
        needsComponents.set( selectedEOS.needsComponents());
        isPackageSelected.set(true);
        packageText.set(obj.toString()); 
    }
      
/**================================ For components =======================================================**/
   private static SimpleBooleanProperty needsMixingRule = new SimpleBooleanProperty(false);
   private static ObservableList<Component> obsListComponents = FXCollections.observableArrayList();
   private static SimpleBooleanProperty areComponentsSelected = new SimpleBooleanProperty(false);
   private static HashMap<Component,SimpleDoubleProperty> fractionsObservableList = new HashMap<>();
   
   
   
   
   public static ObservableList<Component> getSelectedComponentsObservableList(){  
       return obsListComponents;
   }
   public static ArrayList<Component> getSelectedComponentsArrayList(){
       ArrayList<Component> components = new ArrayList<>();
       
       for (Component component : obsListComponents){
           components.add(component);
       }
       
       return components;
   }
    
    public static void removeComponent(Component componentToRemove){
        obsListComponents.remove(componentToRemove);     
        checkComponentsStatus();
    }
     public static void addComponent(Component aComponent){
        obsListComponents.add(aComponent);       
      checkComponentsStatus();
                  
    }
     
     public static void checkComponentsStatus(){       
         areComponentsSelected.set(!obsListComponents.isEmpty());         
         needsMixingRule.set(obsListComponents.size() > 1);          
     }
     
     public static SimpleBooleanProperty needsMixingRule(){
        return needsMixingRule;
    }
     
     
     
    public static HashMap<Component,Double> getFractions(){    
        HashMap<Component,Double> fractions = new HashMap<>();       
        for(Component component : obsListComponents){
            
            
            fractions.put(component, fractionsObservableList.get(component).get());
        }       
        return fractions;
        
    }
    
    public static HashMap<Component,SimpleDoubleProperty> getFractionsObservable(){
        return fractionsObservableList;
    }
   
 /**===================================== Lists ==========================================================**/
    private static HashMap<String,EOS> listOfEOS = new HashMap<>();
    private static HashMap<String,MixingRule> listOfMixingRules = new HashMap<>();
    
    public static HashMap<String,EOS> getListOfEOS(){
        if(listOfEOS.isEmpty()){
      
            listOfEOS.put("vdw",EOS.vanDerWaals());
            listOfEOS.put("ig",EOS.idealGas());
            
            listOfEOS.put("rks",EOS.redlichKwongSoave());//new RedlichKwongSoave()); 
            listOfEOS.put("rksm",  EOS.redlichKwongSoaveMathias());//new RedlichKwongSoave(new Mathias_StryjekVera()));
           // listOfEOS.put("rkst",new RedlichKwongSoave(new Twu()));
              
            
            listOfEOS.put("pr",EOS.pengRobinson());//new PengRobinson());
            listOfEOS.put("prsv",  EOS.pengRobinsonStryjekVera());  // new PengRobinson(new Mathias_StryjekVera()));
            //listOfEOS.put("prt", new PengRobinson(new Twu()));
            
            listOfEOS.put("tst", EOS.twoSimTassone());  //new TwuSimTassone());
           // listOfEOS.put("tstmsv", new TwuSimTassone(new Mathias_StryjekVera()));
           // listOfEOS.put("tstspr", new TwuSimTassone(new Soave_PengRobinsonAlpha()));
        }
        
        
        return listOfEOS;
    }
    
    public static HashMap<String,MixingRule> getListOfMixingRule(){
        if(listOfMixingRules.isEmpty()){
            listOfMixingRules.put("vdwMR", new VDWMixingRule());
            
        }
        
        setSelectedMixingRule(listOfMixingRules.get("vdwMR"));
        
        
        return listOfMixingRules;
    }
    
 
/**===================================== Ready to calculate ==========================================================**/
   
   
    
    public static BooleanBinding arePropertiesReady(){  
         return propertiesReady;
    }
   
    
    public static SimpleBooleanProperty needsComponentes(){
        return needsComponents;
    }
    
    public static SimpleStringProperty getSelectedPackageText(){
        return packageText;
    }
    public static EOS getSelectedEOS(){
        return selectedEOS;
    }

 /**===================================== State & Messages ==========================================================**/   
    
    private static SimpleDoubleProperty tolerance  = new SimpleDoubleProperty(0.00001);
    
    public static double getTolerance(){
        return tolerance.get();
    }
    
    
    private static String eosMessage = "Selecciona un paquete termodinamico";
    private static String componentsMessage = "Selecciona componente(s)";
    private static String mixingRuleMessage = "Selecciona una Regla de mezclado";
   
    private static String readyMessage = "Listo para realizar cálculos";
   
   
    private static BooleanBinding propertiesReady = isPackageSelected.and(needsComponents.not()).or(
            isPackageSelected.and(needsComponents.and(areComponentsSelected.and(needsMixingRule.not()))).or(
            isPackageSelected.and(needsComponents.and(areComponentsSelected).and(needsMixingRule.and(isMixingRuleSelected)))));
   
    private static SimpleStringProperty messageToshow = new SimpleStringProperty(eosMessage);;
    
    
    private static BooleanBinding eosMising = propertiesReady.not().and(isPackageSelected.not());
    private static BooleanBinding componentMissing = propertiesReady.not().and(areComponentsSelected.not());
    private static BooleanBinding mixingRuleMising = propertiesReady.not().and(isMixingRuleSelected.not());
    
    public static SimpleStringProperty messageToshow(){ 
        
        
        
        ChangeListener<Boolean> change = new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(eosMising.get()){
                    messageToshow.set(eosMessage);
                }else if(componentMissing.get()){
                    messageToshow.set(componentsMessage);
                }else if(mixingRuleMising.get()){
                    messageToshow.set(mixingRuleMessage);
                }else if(propertiesReady.get()){
                    messageToshow.set(readyMessage);
                }
            }
        };
        eosMising.addListener(change);
        componentMissing.addListener(change);
        mixingRuleMising.addListener(change);
        propertiesReady.addListener(change);
        
        return messageToshow;       
    }

   

    
                
}
