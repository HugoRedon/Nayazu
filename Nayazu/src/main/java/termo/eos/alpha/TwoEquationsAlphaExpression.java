package termo.eos.alpha;

import termo.component.Component;

/**
 *
 * @author Hugo Redon Rivera
 */
public class TwoEquationsAlphaExpression extends Alpha{
    
    private Alpha alphaAboveTc;
    private Alpha alphaBelowTc;

    @Override
    public String getEquation() {
        if(equation == null){
            StringBuilder b = new StringBuilder();
            
            b.append("-----T < T_c ");
            b.append("\\\\");
            b.append(alphaBelowTc.getEquation());
            b.append("\\\\");
            
            b.append("-----T > T_c");
            b.append("\\\\");
            b.append(alphaAboveTc.getEquation());
            
            equation = b.toString();
        }
        
        return equation;
    }
    
   
    
    @Override
     public  double alpha(double temperature,Component component){
           return getProperAlpha(temperature, component).alpha(temperature, component);
    }
       @Override
    public double TempOverAlphaTimesDerivativeAlphaRespectTemperature(double temperature, Component component) {
            return getProperAlpha(temperature, component).TempOverAlphaTimesDerivativeAlphaRespectTemperature(temperature, component);
    }
       
       public Alpha getProperAlpha(double temperature ,Component component){
                double criticalTemperature = component.getCriticalTemperature();
        double q = component.getK_StryjekAndVera();
        
        double reducedTemperature = temperature / criticalTemperature;
        
        if(reducedTemperature <1){
            return alphaBelowTc;
        }else{
            return alphaAboveTc;
        }
       }

    /**
     * @return the alphaAboveTc
     */
    public Alpha getAlphaAboveTc() {
        return alphaAboveTc;
    }

    /**
     * @param alphaAboveTc the alphaAboveTc to set
     */
    public void setAlphaAboveTc(Alpha alphaAboveTc) {
        this.alphaAboveTc = alphaAboveTc;
    }

    /**
     * @return the alphaBelowTc
     */
    public Alpha getAlphaBelowTc() {
        return alphaBelowTc;
    }

    /**
     * @param alphaBelowTc the alphaBelowTc to set
     */
    public void setAlphaBelowTc(Alpha alphaBelowTc) {
        this.alphaBelowTc = alphaBelowTc;
    }

    @Override
    public int numberOfParameters() {
        return alphaBelowTc.numberOfParameters();
    }

    @Override
    public void setAlphaParameterA(double paramValue, Component component) {
        alphaBelowTc.setAlphaParameterA(paramValue, component);
    }

    @Override
    public double getAlphaParameterA(Component component) {
        return alphaBelowTc.getAlphaParameterA(component);
    }

    @Override
    public void setAlphaParameterB(double paramValue, Component component) {
        alphaBelowTc.setAlphaParameterB(paramValue, component);
    }

    @Override
    public double getAlphaParameterB(Component component) {
        return alphaBelowTc.getAlphaParameterB(component);
    }

    @Override
    public void setAlphaParameterC(double paramValue, Component component) {
        alphaBelowTc.setAlphaParameterC(paramValue, component);
    }

    @Override
    public double getAlphaParameterC(Component component) {
        return alphaBelowTc.getAlphaParameterC(component);
    }

  
 

}
