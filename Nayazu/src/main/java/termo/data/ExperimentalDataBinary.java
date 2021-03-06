package termo.data;

import java.util.ArrayList;
import java.util.HashMap;
import termo.component.Component;

/**
 *
 * @author Hugo Redon Rivera
 */
public class ExperimentalDataBinary{
        
            private int index;
            private double liquidFraction;
            private double vaporFraction;
            private double temperature;
            private double pressure;

            private Component referenceComponent;
            private Component nonReferenceComponent;

    public ExperimentalDataBinary() {
    }

            public ExperimentalDataBinary(int index,Component referenceComponent,Component nonReferenceComponent,double temperature, double pressure, double liquidFraction, double vaporFraction){
                this.index = index;
                this.referenceComponent = referenceComponent;
                this.nonReferenceComponent = nonReferenceComponent;
                this.liquidFraction = (liquidFraction);
                this.temperature =  (temperature);
                this.pressure =  (pressure);
                this.vaporFraction =  (vaporFraction);
            }

            public void setReferenceComponent(Component referenceComponent){
                this.referenceComponent = referenceComponent;
            }
            public Component getReferenceComponent(){
                return referenceComponent;
            }

            public void setNonReferenceComponent(Component nonReferenceComponent){
                this.nonReferenceComponent = nonReferenceComponent;
            }
            public Component getNonReferenceComponent(){
                return nonReferenceComponent;
            }
            public ArrayList<Component> getComponents(){
                ArrayList<Component> components = new ArrayList<>();
                components.add(referenceComponent);
                components.add(nonReferenceComponent);
                
                return components;
            }

            public int getIndex(){
                return index;
            }
            public void setIndex(int index){
                this.index = index;
            }

            public void setLiquidFraction(double liquidFraction){
                this.liquidFraction =  (liquidFraction);
            }
            public double getLiquidFraction(){
                return liquidFraction;
            }
            public HashMap<Component,Double> getLiquidFractions(){
                HashMap<Component,Double> liquidFractions = new HashMap<>();
                liquidFractions.put(referenceComponent, liquidFraction);
                liquidFractions.put(nonReferenceComponent, 1- liquidFraction);

                return liquidFractions;
            }

            public void setVaporFraction(double vaporFraction){
                this.vaporFraction =  (vaporFraction);
            }
            public double getVaporFraction(){
                return vaporFraction;
            }

            public void setTemperature(double temperature){
                this.setTemperature(temperature);

            }
            public double getTemperature(){
                return temperature;
            }

            public void setPressure(double pressure){
                this.pressure =  (pressure);
            }
            public double getPressure(){
                return pressure;
            } 
    }
