package application.model;

import static application.model.MixingRuleType.HuronVidalNRTL;
import static application.model.MixingRuleType.HuronVidalWilson;
import static application.model.MixingRuleType.MathiasKlotzPrausnitz;
import static application.model.MixingRuleType.PanagiotopoulosReid;
import static application.model.MixingRuleType.SandovalWilczecVeraVera;
import static application.model.MixingRuleType.VanDerWaals1;
import static application.model.MixingRuleType.VanDerWaals2;
import static application.model.MixingRuleType.WongSandlerNRTL;
import static application.model.MixingRuleType.WongSandlerWilson;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.collections.ObservableList;
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
import termo.substance.HeterogeneousMixtureSubstance;
import termo.substance.HeterogeneousPureSubstance;
import termo.substance.HeterogeneousSubstance;

public class ThermoPackage{
    
    private EquationOfState equationOfState;
    private MixingRuleType mixingRule;

    /**
     * @return the equationOfState
     */
    public EquationOfState getEquationOfState() {
	return equationOfState;
    }

    /**
     * @param equationOfState the equationOfState to set
     */
    public void setEquationOfState(EquationOfState equationOfState) {
	this.equationOfState = equationOfState;
    }

    /**
     * @return the mixingRule
     */
    public MixingRuleType getMixingRule() {
	return mixingRule;
    }

    /**
     * @param mixingRule the mixingRule to set
     */
    public void setMixingRule(MixingRuleType mixingRule) {
	this.mixingRule = mixingRule;
    }
    
    public HeterogeneousSubstance getHeterogeneousSubstance(){
	
	
	ThermoPackage pa = Eqfases2Copy.getThermoPackage();
	
	MixingRuleType mrType = pa.getMixingRule();
	ObservableList<Component> obsListComponents = Eqfases2Copy.getComponents();
	
	
	
	Cubic eos;
	Alpha alpha;
	MixingRule mRule = null;
	
	boolean isSymmetric = false;
	
	
	
	if(pa.getEquationOfState().equals(EquationOfState.PengRobinsonStryjekVera)){
	    eos = EquationOfStateFactory.pengRobinsonBase();
	    alpha = AlphaFactory.getStryjekAndVeraExpression();
	}else {//rksm
	    eos = EquationOfStateFactory.redlichKwongSoaveBase();
	    alpha = AlphaFactory.getMathiasExpression();
	}
	
	
	if(mrType.equals(VanDerWaals1)){
	    mRule = new VDWMixingRule();//symetric params 
	}else if(mrType == VanDerWaals2){
	    isSymmetric = true;
	    mRule = new VDWMixingRule();
	    //todo non symetric parameters??
	}else if(mrType == PanagiotopoulosReid){
	    
	}else if(mrType ==SandovalWilczecVeraVera ){
	    
	}else if(mrType == MathiasKlotzPrausnitz){
	    mRule = new MathiasKlotzPrausnitzMixingRule();
	}else if(mrType == HuronVidalWilson){
	    WilsonActivityModel w = new WilsonActivityModel();
	    mRule = new HuronVidalMixingRule(w, eos);
	}else if(mrType == HuronVidalNRTL){
	    NRTLActivityModel n = new NRTLActivityModel();
	    mRule = new HuronVidalMixingRule(n, eos);
	}else if(mrType == WongSandlerWilson){
	    WilsonActivityModel w = new WilsonActivityModel();
	    mRule = new WongSandlerMixingRule(w, eos);
	}else if(mrType == WongSandlerNRTL){
	    NRTLActivityModel n = new NRTLActivityModel();
	    mRule = new WongSandlerMixingRule(n, eos);
	}
	
	HeterogeneousSubstance hs ;
	
	
	
	
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
	    
	    HeterogeneousMixtureSubstance mix =new HeterogeneousMixtureSubstance(eos, alpha, mRule, components, k);
	    mix.setzFractions(fractions);
		hs = mix;
		
		
	}else{//one component : PUresubstance
	    Component component = obsListComponents.get(0);
	    hs = new HeterogeneousPureSubstance(eos, alpha, component);
	}
	
	return hs;
    }

}

