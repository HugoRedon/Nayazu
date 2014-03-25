package termo.substance;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.HashMap;
import termo.Constants;
import termo.binaryParameter.InteractionParameter;
import termo.component.Component;
import termo.eos.Cubic;
import termo.eos.alpha.Alpha;
import termo.eos.mixingRule.MixingRule;
import termo.phase.Phase;

/**
 *
 * @author Hugo Redon Rivera
 */
public class MixtureSubstance extends HomogeneousSubstance{
    private MixingRule mixingRule;
    protected ArrayList<PureSubstance> pureSubstances = new ArrayList<>();
    protected HashMap<PureSubstance,Double> molarFractions = new HashMap<>();
    protected InteractionParameter binaryParameters = new InteractionParameter();

    private Alpha alpha;
    
    public MixtureSubstance(){
        
    }

 
    public MixtureSubstance(Cubic equationOfState,Alpha alpha,  ArrayList<Component> components,Phase phase,MixingRule mixingRule ,InteractionParameter k){
	super(equationOfState,phase);
	this.mixingRule = mixingRule;
	for (Component component:components){
	    PureSubstance sub = new PureSubstance(equationOfState, alpha, component, phase);
            mpcs.addPropertyChangeListener(sub);
	    pureSubstances.add(sub);
	}
	this.binaryParameters = k;
    }
     


    public void setComponents(ArrayList<Component> components){
        pureSubstances.clear();
        for(Component component : components){ //implement property change listener
            PureSubstance pure = new PureSubstance(super.getCubicEquationOfState(), alpha, component, super.getPhase());
            mpcs.addPropertyChangeListener(pure);
            pureSubstances.add(pure);
        }
    }

    
    private PureSubstance getPureSubstance(Component component){
	PureSubstance result = null;
	for(PureSubstance pure: pureSubstances){
	    if(component.equals(pure.getComponent())){
		result = pure;
	    }
	}
	return result;
    }
    
    
    public double calculateFugacity(Component component){
	PureSubstance pure = getPureSubstance(component);
	return calculateFugacity(pure);
	//throw new Exception("La mezcla no contiene el componente " + component.toString());
	
    }

    
    public void addComponent(PureSubstance pureSubstance, double molarFraction){
        pureSubstance.setCubicEquationOfState(getCubicEquationOfState());
        getPureSubstances().add(pureSubstance);
        getMolarFractions().put(pureSubstance, molarFraction);
    }
    public void removeComponent(PureSubstance pureSubstance){
      	getPureSubstances().remove(pureSubstance);
      	getMolarFractions().remove(pureSubstance);
    }


    @Override
    public double temperatureParcial_a() {
        return getMixingRule().temperatureParcial_a(super.getTemperature(), molarFractions,binaryParameters );
    }

    @Override
    public double calculate_a_cubicParameter(){
        return mixingRule.a(temperature, molarFractions, binaryParameters);
    }
    
     @Override
    public double calculate_b_cubicParameter() {       
        return getMixingRule().b(molarFractions,temperature, binaryParameters);
    }
    
    
    public ArrayList<Component> getComponents(){
        ArrayList<Component> components = new ArrayList<>();
        for (PureSubstance pureSubstance : getPureSubstances()){
            components.add(pureSubstance.getComponent());
        }
        return components;
    }
    public HashMap<Component,Double> getReadOnlyFractions(){
        HashMap<Component,Double> fractions = new HashMap<>();
        for (PureSubstance pureSubstance : getPureSubstances()){
            Component component = pureSubstance.getComponent();
            double molarFraction = getMolarFractions().get(pureSubstance);
            fractions.put(component, molarFraction);
        }
        return fractions;
    }

    @Override
    public double calculateIdealGasEnthalpy() {
        double idealGasEnthalpy = 0;
        for(PureSubstance pureSubstance: getPureSubstances()){
            double xi = getMolarFractions().get(pureSubstance);
            double idealGasEnthalpyFori = pureSubstance.calculateIdealGasEnthalpy();
            
            idealGasEnthalpy += xi *idealGasEnthalpyFori;
        }
        return idealGasEnthalpy;
    }

    @Override
    public double calculateIdealGasEntropy() {

           double term1 = 0;
           double term2 = 0;
        
        for(PureSubstance pureSubstance: getPureSubstances()){
            double xi = getMolarFractions().get(pureSubstance);
            double entropyFori = pureSubstance.calculateIdealGasEntropy();
            
            term1 += xi * entropyFori;
            
            term2 += xi * Math.log(xi);
            
        }
        
        return term1  - Constants.R * term2;
    }

    @Override
    public double oneOver_N_Parcial_a(PureSubstance pureSubstance) {
        Component component = pureSubstance.getComponent();
       return getMixingRule().oneOverNParcial_aN2RespectN(
               super.getTemperature(), 
               pureSubstance, 
               molarFractions,
	       binaryParameters);
    }

    /**
     * @return the mixingRule
     */
    public MixingRule getMixingRule() {
	return mixingRule;
    }

    /**
     * @param mixingRule the mixingRule to set
     */
    public void setMixingRule(MixingRule mixingRule) {
	this.mixingRule = mixingRule;
    }

    /**
     * @return the pureSubstances
     */
    public ArrayList<PureSubstance> getPureSubstances() {
	return pureSubstances;
    }

//    /**
//     * @param pureSubstances the pureSubstances to set
//     */
//    private void setPureSubstances(ArrayList<PureSubstance> pureSubstances) {
//	this.pureSubstances = pureSubstances;
//    }

    /**
     * @return the molarFractions
     */
    public HashMap<PureSubstance,Double> getMolarFractions() {
	return molarFractions;
    }

    /**
     * @param molarFractions the molarFractions to set
     */
//    private void setMolarFractions(HashMap<PureSubstance,Double> molarFractions) {
//	this.molarFractions = molarFractions;
//    }

    /**
     * @return the binaryParameters
     */
    public InteractionParameter getBinaryParameters() {
	return binaryParameters;
    }
//      public BinaryInteractionParameter getBinaryParameters() {
//	return null;
//    }

    /**
     * @param binaryParameters the binaryParameters to set
     */
    public void setBinaryParameters(InteractionParameter binaryParameters) {
	this.binaryParameters = binaryParameters;
    }

    

    private void setFraction(PureSubstance component, double i) {
	molarFractions.put(component, i);
    }

    public void setFraction(Component component, Double fraction) {
	for (PureSubstance pure : pureSubstances){
	    if(pure.getComponent().equals(component)){
		molarFractions.put(pure, fraction);
	    }
	}
    }
    
    public void setFractions(HashMap<Component,Double> fractions){
	for(PureSubstance pure: pureSubstances){
	    molarFractions.put(pure, fractions.get(pure.getComponent()));
	}
    }

    @Override
    public double calculatetAcentricFactorBasedVaporPressure() {
	double result = 0;
	for( PureSubstance component : pureSubstances){
	    double vaporP =  component.calculatetAcentricFactorBasedVaporPressure();
	    result += vaporP * molarFractions.get(component);  
	}
	return result;
    }

    /**
     * @return the alpha
     */
    public Alpha getAlpha() {
        return alpha;
    }
    public void setAlpha(Alpha alpha){
        Alpha oldAlpha = this.alpha;
        this.alpha = alpha;
        mpcs.firePropertyChange("alpha", oldAlpha, alpha);
    }


}
