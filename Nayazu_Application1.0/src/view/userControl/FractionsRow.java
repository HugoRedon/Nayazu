package view.userControl;

import javafx.beans.property.SimpleDoubleProperty;
import termo.component.Component;

/**
 *
 * @author Hugo Redon Rivera
 */
 public class FractionsRow{
        private Component component;
        private SimpleDoubleProperty fraction;
        
        public FractionsRow(Component aComponent,SimpleDoubleProperty aFraction){
            this.component = aComponent;
            this.fraction = aFraction;
        }
        
        public Component getComponent(){
            return this.component;
        }
        public void setComponent(Component aComponent){
            this.component = aComponent;
        }
        public Double getFraction(){
            return this.fraction.get();
        }
        public void setFraction(Double aFraction){
            this.fraction.set(aFraction);
        }
        
   }