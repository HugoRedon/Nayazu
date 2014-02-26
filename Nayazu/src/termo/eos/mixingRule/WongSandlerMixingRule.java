/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package termo.eos.mixingRule;

import java.util.ArrayList;
import java.util.HashMap;
import termo.Constants;
import termo.activityModel.ActivityModel;
import termo.binaryParameter.ActivityModelBinaryParameter;
import termo.binaryParameter.BinaryInteractionParameter;
import termo.binaryParameter.InteractionParameter;
import termo.component.Component;
import termo.eos.Cubic;
import termo.substance.MixtureSubstance;
import termo.substance.PureSubstance;

/**
 *
 * @author
 * Hugo
 */
public class WongSandlerMixingRule extends MixtureSubstance {
       ActivityModel activityModel;
    
    private double L;
    public WongSandlerMixingRule(ActivityModel activityModel,Cubic eos){
	this.activityModel = activityModel;
	
	L = eos.calculateL(1, 1);
	
    }
    
    
    
    

    
       @Override
    public double calculate_a_cubicParameter() {
            
	ActivityModelBinaryParameter params = (ActivityModelBinaryParameter)binaryParameters;
	
            double b = calculate_b_cubicParameter();
            double excessGibbs = activityModel.excessGibbsEnergy( molarFractions,params , getTemperature());
            
            double firstTerm = 0;
           
            for(PureSubstance ci : molarFractions.keySet()){
                 double xi = molarFractions.get(ci);
                 double ai = ci.calculate_a_cubicParameter();
                 double bi = ci.calculate_b_cubicParameter();
                
                firstTerm += xi * (ai) / bi ;
            }
       return b* (firstTerm  - excessGibbs/(getL()));
    }

    
    
       @Override
    public double calculate_b_cubicParameter() {

	ActivityModelBinaryParameter params = (ActivityModelBinaryParameter)binaryParameters;
	
	double b = 0;
	
	double numer = 0;
	double denomSum =0;
	
	for(PureSubstance ci: molarFractions.keySet()){
	    for(PureSubstance cj: molarFractions.keySet()){
		double xi = molarFractions.get(ci);
		double xj = molarFractions.get(cj);
		
		double bi = ci.calculate_b_cubicParameter();
		double ai = ci.calculate_a_cubicParameter();
		
		double bj = cj.calculate_b_cubicParameter();
		double aj = cj.calculate_a_cubicParameter();
		
		double kij = params.getK().getValue(ci.getComponent(), cj.getComponent());
		
		double R = Constants.R;
		
		denomSum += xi*(ai/(bi*R*getTemperature()));
		
		
		double isum = bi - ai/(R*getTemperature());
		double jsum = bj - aj/(R*getTemperature());
		
		numer+= xi*xj*((isum+jsum)/2)*(1-kij);
		
	    }
	}
	
	
	double ge = activityModel.excessGibbsEnergy(molarFractions, params, getTemperature());
	
	double denom = 1 - ge/(L*getTemperature() * Constants.R) - denomSum;
	
	return numer/denom;
	
    }

    
    
       @Override
    public double oneOver_N_Parcial_a(PureSubstance ci) {
        double b = calculate_b_cubicParameter();
        double a =calculate_a_cubicParameter();
        
        ActivityModelBinaryParameter param = (ActivityModelBinaryParameter)binaryParameters;
	double ai = ci.calculate_a_cubicParameter();
	double bi = ci.calculate_b_cubicParameter();
	double alphai = ai/(bi*Constants.R * getTemperature());
	
        double gammai = activityModel.activityCoefficient( ci, molarFractions, param, getTemperature());
        return b * Constants.R * getTemperature()*( alphai -  Math.log(gammai)/L) + a * bi / b;
    }

    
    
    public double temperatureParcial_a(double temperature, ArrayList<Component> components, HashMap<Component, Double> fractions, HashMap<Component, Double> single_as, HashMap<Component, Double> single_bs, HashMap<Component, Double> alphaDerivatives, BinaryInteractionParameter k) {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the L
     */
    public double getL() {
	return L;
    }

    /**
     * @param L the L to set
     */
    public void setL(double L) {
	this.L = L;
    }

    @Override
    public double temperatureParcial_a() {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
