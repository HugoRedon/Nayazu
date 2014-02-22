/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application.model;

/**
 *
 * @author
 * Hugo
 */
public enum VLEType{
    BubbleT,
    DewT,
    BubbleP,
    DewP,
    FlashTVF,
    FlashPVF,
    FlashTP,
    FlashPH,
    FlashPS,
    FlashTS;
    
    double TemperatureOrPressure;
    double seconValue;

    public double getSeconValue() {
	return seconValue;
    }

    public double getTemperatureOrPressure() {
	return TemperatureOrPressure;
    }

    public void setSeconValue(double seconValue) {
	this.seconValue = seconValue;
    }

    public void setTemperatureOrPressure(double TemperatureOrPressure) {
	this.TemperatureOrPressure = TemperatureOrPressure;
    }
    
}