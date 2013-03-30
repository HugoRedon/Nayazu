package termo.substance;

import termo.Constants;
import termo.component.Component;
import termo.eos.alpha.Alpha;

/**
 *
 * @author Hugo Redon Rivera
 */
public class PureSubstance extends Substance{
    private Component component;
    private Alpha alpha;
    
    
    public boolean equals(PureSubstance substance){
        if(substance.getComponent().equals(component) &&
                substance.getCubicEquationOfState().equals(this.getCubicEquationOfState()) &&
                substance.getAlpha().equals(this.getAlpha())){
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    public String toString(){
        String equationOfStateName = getCubicEquationOfState().getName();
        String alphaName = getAlpha().getName();
        String componentName = getComponent().getName();
        
        return "Pure substance with: Cubic Equation of State: " + equationOfStateName + 
                " Alpha Expression: " + alphaName + 
                " Component: " + componentName;
        
    }
    
    @Override
    public double calculateIdealGasEnthalpy(double temperature){
        double enthalpyReference = component.getEnthalpyofFormationofIdealgasat298_15Kand101325Pa();
        double referenceTemp = 298.15;
         return  component.getCpIdealGasEquation().Enthalpy(temperature, referenceTemp, enthalpyReference);
    }

    @Override
    public double temperatureParcial_a(double temperature){
        double a = calculate_a_cubicParameter(temperature);
        double ToveralphaDerivativeAlpha = alpha.TempOverAlphaTimesDerivativeAlphaRespectTemperature(temperature, component);
        return a * ToveralphaDerivativeAlpha;
    }

    
    @Override
    public double oneOver_N_Parcial_a(double temperature, PureSubstance pureSubstance){
        return 2 * calculate_a_cubicParameter(temperature);
    }
    

    

    @Override
    public double calculate_a_cubicParameter(double temperature){
        
        double omega_a = getCubicEquationOfState().getOmega_a();
        
             double tc = component.getCriticalTemperature();
        double pc = component.getCriticalPressure();
        
        return (omega_a * Math.pow( Constants.R  * tc,2) *getAlpha().alpha(temperature,component) )/ (pc);  
    }

    @Override
    public double calculate_b_cubicParameter(){
         
        double omega_b = getCubicEquationOfState().getOmega_b();
        
          double tc = component.getCriticalTemperature();
        double pc = component.getCriticalPressure();
        
        return (omega_b * tc* Constants.R)/ (pc); 
    }
    /**
     * @return the component
     */
    public Component getComponent() {
        return component;
    }

    /**
     * @param component the component to set
     */
    public void setComponent(Component component) {
        this.component = component;
    }

    /**
     * @return the alpha
     */
    public Alpha getAlpha() {
        return alpha;
    }

    /**
     * @param alpha the alpha to set
     */
    public void setAlpha(Alpha alpha) {
        this.alpha = alpha;
    }
    @Override
    public  double calculateIdealGasEntropy(double temperature, double pressure) {
        double entropyReference = component.getAbsoluteEntropyofIdealGasat298_15Kand101325Pa();
        double referenceTemperature = 298.15;
        double referencePressure = 101325;
        return component.getCpIdealGasEquation().idealGasEntropy(temperature, referenceTemperature, pressure, referencePressure, entropyReference);
    }
}