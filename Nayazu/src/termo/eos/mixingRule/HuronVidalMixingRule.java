package termo.eos.mixingRule;

import termo.Constants;
import termo.activityModel.ActivityModel;
import termo.binaryParameter.ActivityModelBinaryParameter;
import termo.eos.Cubic;
import termo.substance.MixtureSubstance;
import termo.substance.PureSubstance;

/**
 *
 * @author
 * Hugo
 */
public class HuronVidalMixingRule extends MixtureSubstance{

    ActivityModel activityModel;
    
    private double L;
    public HuronVidalMixingRule(ActivityModel activityModel,Cubic eos){
	this.activityModel = activityModel;
	
	L = eos.calculateL(1, 1);
	
    }
    
     @Override
    public double calculate_a_cubicParameter() {
            double b = calculate_b_cubicParameter();
            double excessGibbs = activityModel.excessGibbsEnergy( molarFractions, (ActivityModelBinaryParameter)binaryParameters, getTemperature());
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
         double b = 0;
      for(PureSubstance iComponent:molarFractions.keySet()){
            double xi = molarFractions.get(iComponent);
            double bi = iComponent.calculate_b_cubicParameter();
            b += xi * bi ;
      }
       return b;
    }

    
    @Override
    public double oneOver_N_Parcial_a(PureSubstance ci) {
         
        double b = calculate_b_cubicParameter( );
        double a =calculate_a_cubicParameter();
        
        ActivityModelBinaryParameter param = (ActivityModelBinaryParameter)binaryParameters;
	double ai = ci.calculate_a_cubicParameter();
	double bi = ci.calculate_b_cubicParameter();
	double alphai = ai/(bi*Constants.R * getTemperature());
	
        double gammai = activityModel.activityCoefficient( ci, molarFractions, param, getTemperature());
        return b * Constants.R * getTemperature()*( alphai -  Math.log(gammai)/L) + a * bi / b;
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
