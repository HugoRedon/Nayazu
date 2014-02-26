
package termo.substance;

import termo.Constants;
import termo.component.Component;
import termo.eos.Cubic;
import termo.phase.Phase;

/**
 *
 * @author Hugo Redon Rivera
 */
public abstract class  HomogeneousSubstance extends Cubic {
    
//    private Cubic cubicEquationOfState;    
       protected double temperature;
    protected double pressure;
    protected double volume;
    protected Phase phase;
    
    public HomogeneousSubstance(){
	
    }
    public HomogeneousSubstance(Cubic eos){
	//this.cubicEquationOfState= eos;
    }
    public HomogeneousSubstance(Cubic eos,Phase phase){
//	this.cubicEquationOfState = eos;
	//this.phase = phase;
    }

    public abstract double temperatureParcial_a();
    public abstract double calculate_a_cubicParameter();
    public abstract double calculateIdealGasEnthalpy();
    public abstract double calculate_b_cubicParameter();
    public  abstract double calculateIdealGasEntropy() ;
    public abstract double oneOver_N_Parcial_a(PureSubstance pureSubstance);
    

  
    
//     public double bi(PureSubstance pureSubstance){
//        return pureSubstance.calculate_b_cubicParameter();
//    }
    
       
    protected double calculateFugacity( PureSubstance pureSubstance){
         a =calculate_a_cubicParameter();
         b = calculate_b_cubicParameter();

	
	
        double  parcialb = pureSubstance.calculate_b_cubicParameter();//bi(pureSubstance);
        double parciala = oneOver_N_Parcial_a( pureSubstance);
        
        return calculateFugacity(temperature, pressure, parciala, parcialb, phase);
    }
    
    private double calculateEntropy(){
	volume = calculateMolarVolume(temperature, pressure, phase);
        double idealGasEntropy = calculateIdealGasEntropy();
         b = calculate_b_cubicParameter();
        double Temp_parcial_a = temperatureParcial_a( );
        
        double L = calculateL(volume, b);
	double z = calculateCompresibilityFactor(temperature, pressure, phase);
        
        //return idealGasEntropy +  Constants.R * Math.log( (pressure *(volume - b))/(Constants.R * temperature)) + L * (Temp_parcial_a)/(b);
	return idealGasEntropy +  Constants.R * Math.log( (z *(volume - b))/(volume)) + L * (Temp_parcial_a)/(getTemperature()*b);
    }
   
    private  double calculateEnthalpy( ){
	volume = calculateMolarVolume(temperature, pressure, phase);
        double idealGasEnthalpy = calculateIdealGasEnthalpy();
         a = calculate_a_cubicParameter();
         b = calculate_b_cubicParameter();
        double L = calculateL(volume, b);
        //double alphaValue = alpha.alpha(temperature, component);
        
        double Temp_parcial_a = temperatureParcial_a( );
        
        return idealGasEnthalpy + ((Temp_parcial_a - a)/b) * L  + getPressure() * volume - Constants.R *getTemperature();
    }
   
        
//            /**
//     * @return the cubicEquationOfState
//     */
//    public Cubic getCubicEquationOfState() {
//        return cubicEquationOfState;
//    }
//
//    /**
//     * @param cubicEquationOfState the cubicEquationOfState to set
//     */
//    public void setCubicEquationOfState(Cubic cubicEquationOfState) {
//        this.cubicEquationOfState = cubicEquationOfState;
//    }

    public abstract double calculatetAcentricFactorBasedVaporPressure();
//    public abstract EquilibriaSolution bubbleTemperature(double pressure);
//    public abstract EquilibriaSolution bubblePressure(double temperature);
//    public abstract EquilibriaSolution bubbleTemperatureEstimate(double pressure);
//    public abstract double bubblePressureEstimate(double temperature);
//    public abstract EquilibriaSolution dewTemperature(double pressure);
//    public abstract EquilibriaSolution dewTemperatureEstimate(double pressure);
//    public abstract EquilibriaSolution dewPressureEstimate(double temperature);
//    public abstract EquilibriaSolution dewPressure(double temperature);

    /**
     * @return the phase
     */
    public Phase getPhase() {
	return getPhase();
    }

    /**
     * @param phase the phase to set
     */
    public void setPhase(Phase phase) {
	this.setPhase(phase);
    }

    public double calculateGibbs() {
	double enthalpy = calculateEnthalpy();
	double entropy = calculateEntropy();
	
	return enthalpy - getTemperature() * entropy;
    }

    /**
     * @return the temperature
     */
    public double getTemperature() {
	return getTemperature();
    }

    /**
     * @param temperature the temperature to set
     */
    public void setTemperature(double temperature) {
	this.setTemperature(temperature);
    }

    /**
     * @return the pressure
     */
    public double getPressure() {
	return getPressure();
    }

    /**
     * @param pressure the pressure to set
     */
    public void setPressure(double pressure) {
	this.setPressure(pressure);
    }
}
