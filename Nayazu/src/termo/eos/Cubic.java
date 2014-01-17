package termo.eos;

import termo.Constants;
import termo.phase.Phase;

/**
 *
 * @author Hugo Redon Rivera
 */
public  class Cubic extends EOS{
    
    private double u ;
    private double w;
  
    private double omega_a;
    private double omega_b;
    
    @Override
    public String getEquation(){
        return "\\( P = \\frac{RT}{v - b} - \\frac{a} { v^2 + u b v + w b^2} \\)";
    }

    /**
     *
     * @param temperature Temperature in Kelvin
     * @param volume Volume in [m3/mol]
     * @param components List of components of the mixture
     * @param fractions Mol fractions of the mixture
     * @return The calculated pressure with the current equation of state
     */
    public double calculatePressure(
            double temperature, 
            double volume,
            double a,
            double b){    
        return ( Constants.R * temperature / (volume - b)    )
                - ( a / (Math.pow(volume ,  2 ) +  this.getU()  *  b  *  volume  +  this.getW()  *  Math.pow( b ,  2 ) ) ) ;
    }
    
    public boolean oneRoot(double pressure,
            double temperature,
            double a,
            double b
            ){
     
        double A = get_A(temperature, pressure, a);
        double B = get_B(temperature, pressure, b);
             
        double alpha = 1-(this.getU() - 1 ) * B;
        double beta = A - this.getU() * B - this.getU() * Math.pow(B, 2) + this.getW() * Math.pow(B, 2);
        double gama = A*B + this.getW() * Math.pow(B,2) + this.getW() * Math.pow(B, 3);
        
        double C = 3 * beta - Math.pow( alpha , 2 );
        double D = - Math.pow( alpha , 3 ) + 4.5d * alpha * beta - 13.5 * gama;
        double Q = Math.pow(C, 3) + Math.pow( D , 2 );
        
        if(Q <= 0){
            return false;
        }else{
            return true;
        }
            
    }
    
    public double get_A(double temperature, double pressure, double a){
        return    a * pressure /Math.pow(Constants.R * temperature,2);
    }
    public double get_B(double temperature, double pressure, double b){
        return b * pressure / (Constants.R * temperature); 
    }
    
    public double calculateCompresibilityFactor(
            double A,
            double B,
            Phase aPhase){
      
        double alpha = 1-(this.getU() - 1 ) * B;
        double beta = A - this.getU() * B - this.getU() * Math.pow(B, 2) + this.getW() * Math.pow(B, 2);
        double gama = A*B + this.getW() * Math.pow(B,2) + this.getW() * Math.pow(B, 3);
        
        double C = 3 * beta - Math.pow(alpha, 2);
        double D = - Math.pow(alpha, 3) + 4.5d * alpha * beta - 13.5 * gama;
        double Q = Math.pow(C, 3) + Math.pow(D, 2);
        
        if(Q <= 0){
            double theta = Math.acos(-D / Math.sqrt(- Math.pow(C, 3)));
            
            double liquidz = (1d/3d) * (alpha + 2 * Math.sqrt(-C) * Math.cos((theta / 3) + 120*(Math.PI / 180)));
            double vaporz = (1d/3d) * (alpha + 2 * Math.sqrt(-C) * Math.cos(theta / 3 ) );
            
            if(liquidz < B){
                liquidz = vaporz;
            }
            
            if(aPhase.equals(Phase.LIQUID)){
                return liquidz;
            }else{
                return vaporz;
            }
                
        }else {
           // Math.pow(negative number, fractional ) returns NaN
            
            
            double firstSum = -D + Math.sqrt(Q);
            double secondSum = -D - Math.sqrt(Q);
            
            double firstTerm = (firstSum < 0)? -Math.pow(-firstSum, 1d/3d): Math.pow(firstSum, 1d/3d);     
            double secondTerm= (secondSum < 0)? -Math.pow(-secondSum, 1d/3d): Math.pow(secondSum, 1d/3d);       
            double z = (1d/3d) * (alpha + firstTerm + secondTerm);
            return z;
        }
        
        

    }
 public double calculateVolume(
            double temperature, 
            double pressure,
            double z
            ){
      return z * Constants.R * temperature / pressure;
    }
 public double calculateFugacity(
               double temperature, 
               double pressure,
               double a,
               double b,
               double parciala,
               double bi,
               Phase aPhase
               ){
           
           double A = get_A(temperature, pressure, a);
           double B = get_B(temperature, pressure, b);
           
           double z = calculateCompresibilityFactor(A, B, aPhase);
           double volume = calculateVolume(temperature, pressure, z);
   
        double L = calculateL(volume, b);
         
        double lnfug = -Math.log((volume-b)/volume) + (z-1) * (bi/b) + (a / (Constants.R * temperature * b))*((bi/b) - (parciala/a))* L - Math.log(z);
        return Math.exp(lnfug);

    }
       
       public double calculateL(double volume, double b){
               double delta = Math.sqrt(Math.pow(this.getU(),2) - 4 * this.getW());
               
                 if( delta == 0 ){
                     return b / volume;
                 }else{
                     return  (1 / delta)* Math.log((2 * volume + (2 + delta) * b)/(2 * volume  + (2 - delta) * b));
                 }      
       }
       
       
       
       
       
       
    
    public void setOmega_a(double omega_a){
        this.omega_a = omega_a;
    }
    public double getOmega_a(){
        return this.omega_a;
    }
    public void setOmega_b(double omega_b){
        this.omega_b = omega_b;
    }
    public double getOmega_b(){
        return this.omega_b;
    }

    public double getU() {
        return u;
    }

    public void setU(double u) {
        this.u = u;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }
        @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.u) ^ (Double.doubleToLongBits(this.u) >>> 32));
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.w) ^ (Double.doubleToLongBits(this.w) >>> 32));
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.omega_a) ^ (Double.doubleToLongBits(this.omega_a) >>> 32));
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.omega_b) ^ (Double.doubleToLongBits(this.omega_b) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cubic other = (Cubic) obj;
        if (Double.doubleToLongBits(this.u) != Double.doubleToLongBits(other.u)) {
            return false;
        }
        if (Double.doubleToLongBits(this.w) != Double.doubleToLongBits(other.w)) {
            return false;
        }
        if (Double.doubleToLongBits(this.omega_a) != Double.doubleToLongBits(other.omega_a)) {
            return false;
        }
        if (Double.doubleToLongBits(this.omega_b) != Double.doubleToLongBits(other.omega_b)) {
            return false;
        }
        return true;
    }
}
