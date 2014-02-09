
package application;

import termo.phase.Phase;

/**
 *
 * @author
 * Hugo
 */

public class OnePhasePropertiesResult{
    private Double z;
    private Double enthalpy;
    private Double entropy;
    private Double gibbs;
    private Double molarVolume;
    private Phase phase;
    
    private Double temperature;
    private Double pressure;

    /**
     * @return the z
     */
    public Double getZ() {
	return z;
    }

    /**
     * @param z the z to set
     */
    public void setZ(double z) {
	this.z = z;
    }

    /**
     * @return the enthalpy
     */
    public Double getEnthalpy() {
	return enthalpy;
    }

    /**
     * @param enthalpy the enthalpy to set
     */
    public void setEnthalpy(double enthalpy) {
	this.enthalpy = enthalpy;
    }

    /**
     * @return the entropy
     */
    public Double getEntropy() {
	return entropy;
    }

    /**
     * @param entropy the entropy to set
     */
    public void setEntropy(double entropy) {
	this.entropy = entropy;
    }

    /**
     * @return the gibbs
     */
    public Double getGibbs() {
	return gibbs;
    }

    /**
     * @param gibbs the gibbs to set
     */
    public void setGibbs(double gibbs) {
	this.gibbs = gibbs;
    }

    /**
     * @return the molarVolume
     */
    public Double getMolarVolume() {
	return molarVolume;
    }

    /**
     * @param molarVolume the molarVolume to set
     */
    public void setMolarVolume(double molarVolume) {
	this.molarVolume = molarVolume;
    }

    /**
     * @return the phase
     */
    public Phase getPhase() {
	return phase;
    }

    /**
     * @param phase the phase to set
     */
    public void setPhase(Phase phase) {
	this.phase = phase;
    }

    /**
     * @return the temperature
     */
    public Double getTemperature() {
	return temperature;
    }

    /**
     * @param temperature the temperature to set
     */
    public void setTemperature(double temperature) {
	this.temperature = temperature;
    }

    /**
     * @return the pressure
     */
    public Double getPressure() {
	return pressure;
    }

    /**
     * @param pressure the pressure to set
     */
    public void setPressure(double pressure) {
	this.pressure = pressure;
    }
}