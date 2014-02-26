package termo.eos.mixingRule;

import java.util.HashMap;
import termo.binaryParameter.InteractionParameter;
import termo.substance.MixtureSubstance;
import termo.substance.PureSubstance;

/**
 *
 * @author Hugo Redon Rivera
 */
public class VDWMixingRule extends MixtureSubstance{

//    public VDWMixingRule(){
////        this.name = "Van Der Waals";
// 
//    }
//    
   
    @Override
  public double calculate_a_cubicParameter(){
	
       double a = 0;
      for(PureSubstance iComponent: getPureSubstances()){
          for(PureSubstance jComponent: getPureSubstances()){
              double xi = molarFractions.get(iComponent);
              double xj = molarFractions.get(jComponent);
              
              double ai = iComponent.calculate_a_cubicParameter();
              double aj = jComponent.calculate_a_cubicParameter();
              
              double kij = binaryParameters.getValue(iComponent.getComponent(), jComponent.getComponent());
             
              a += xi * xj * Math.sqrt(ai * aj) * (1-kij);
          }
      }
       return a;
  }

    @Override
   public double temperatureParcial_a(){
       
       double result = 0;
       for(PureSubstance ci: pureSubstances){
            for (PureSubstance cj: pureSubstances){
                double xi = molarFractions.get(ci);
                double tempAlphaDerivativeAlphai =ci.getAlpha().TempOverAlphaTimesDerivativeAlphaRespectTemperature(getTemperature(), ci.getComponent()) ;
		double a = calculate_a_cubicParameter();
                double xj = molarFractions.get(cj);
                double tempAlphaDerivativeAlphaj = cj.getAlpha().TempOverAlphaTimesDerivativeAlphaRespectTemperature(getTemperature(), cj.getComponent());
                result += (1d/2d) * xi * xj * a*(tempAlphaDerivativeAlphai + tempAlphaDerivativeAlphaj);
                
            }
       }
       return result;
   }
   
    @Override
   public double oneOver_N_Parcial_a(PureSubstance iComponent){

       double sum = 0;
       double ai = iComponent.calculate_a_cubicParameter();
       
       for(PureSubstance kComponent : pureSubstances){
           double xk = molarFractions.get(kComponent);          
           double ak = kComponent.calculate_a_cubicParameter();     
           double kik = binaryParameters.getValue(iComponent.getComponent(), kComponent.getComponent());
           sum += xk * Math.sqrt(ai * ak ) * (1- kik);
       }

       return  2 * sum ;
   }


    @Override
    public double calculate_b_cubicParameter() {
         double b = 0;
      for(PureSubstance iComponent:pureSubstances){
            double xi = molarFractions.get(iComponent);
            double bi = iComponent.calculate_b_cubicParameter();//singleBs.get(iComponent);
            b += xi * bi ;
      }
       return b;
    }    
}
