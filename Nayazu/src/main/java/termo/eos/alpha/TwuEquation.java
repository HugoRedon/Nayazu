package termo.eos.alpha;

import termo.component.Component;

public class TwuEquation extends Alpha {
    public TwuEquation(){
        setEquation("\\alpha(T) = T_r^{N(M-1)}e^{L\\left( 1-T_r^{NM} \\right)}");
    }
    
    @Override
    public double alpha(double temperature, Component component) {
        double tc = component.getCriticalTemperature();
        double tr = temperature / tc;
        
        double N = component.getN_Twu();
        double M = component.getM_Twu();
        double L = component.getL_Twu();
                
        return Math.pow(tr, N * (M -1)) * Math.exp(L* (1- Math.pow(tr, N * M)));
 
    }

    @Override
    public double TempOverAlphaTimesDerivativeAlphaRespectTemperature(double temperature, Component component) {
        double tc = component.getCriticalTemperature();
        double tr = temperature / tc;
        
        double N = component.getN_Twu();
        double M = component.getM_Twu();
        double L = component.getL_Twu();
        
        return N*( M-1) - L * N * M * Math.pow(tr, N*M);
    }
    

     @Override
    public int numberOfParameters() {
        return 3;
    }
    
    @Override
    public double getAlphaParameterA(Component component) {
	return component.getN_Twu();
    } 
    
    @Override
    public void setAlphaParameterA(double paramValue, Component component) {
        component.setN_Twu(paramValue);
    }
    
    @Override
    public void setAlphaParameterB(double paramValue, Component component) {
        component.setM_Twu(paramValue);
    }

    @Override
    public double getAlphaParameterB(Component component) {
        return component.getM_Twu();
    }

    @Override
    public void setAlphaParameterC(double paramValue, Component component) {
        component.setL_Twu(paramValue);
    }

    @Override
    public double getAlphaParameterC(Component component) {
        return component.getL_Twu();
    }


}
