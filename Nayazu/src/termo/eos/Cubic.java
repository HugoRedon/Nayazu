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
  
    protected double omega_a;
    protected double omega_b;
    
    protected double a;
    protected double b;
    
    @Override
    public String getEquation(){
        return "P = \\frac{RT}{v - b} - \\frac{a} { v^2 + u b v + w b^2}";
    }

    /**
     *
     * @param temperature Temperature in Kelvin
     * @param volume Volume in [m3/mol]
     * @param components List of components of the mixture
     * @param fractions Mol fractions of the mixture
     * @return The calculated pressure with the current equation of state
     */
    public double calculatePressure(double temperature,double volume){    
        return ( Constants.R * temperature / (volume - b)    )
                - ( a / (Math.pow(volume,  2 ) +  this.getU()  * b  *  volume  +  this.getW()  *  Math.pow( b,  2 ) ) ) ;
    }
    
    public boolean oneRoot(double temperature, double pressure){
     
        double A = get_A(temperature, pressure);
        double B = get_B(temperature, pressure);
             
        double alpha = cubicSolutionAlpha(B);
        double beta = cubicSolutionBeta(A, B);
        double gama = cubicSolutionGama(A, B);
        
        double C = cubicSolutionC(beta, alpha);
        double D = cubicSolutionD(alpha, beta, gama);
        double Q = cubicSolutionQ(C, D);
        
        if(Q <= 0){
            return false;
        }else{
            return true;
        }
            
    }
    
    private double cubicSolutionQ(double C, double D){
	return  Math.pow(C, 3) + Math.pow( D , 2 );
    }
    
    private double cubicSolutionD(double alpha,double beta, double gama){
	return - Math.pow( alpha , 3 ) + 4.5d * alpha * beta - 13.5 * gama;
    }
    
    private double cubicSolutionC(double beta, double alpha){
	return 3 * beta - Math.pow( alpha , 2 );
    }
    
    private double cubicSolutionAlpha(double B){
	return 1-(this.getU() - 1 ) * B;
    }
    private double cubicSolutionBeta(double A,double B){
	return A - this.getU() * B - this.getU() * Math.pow(B, 2) + this.getW() * Math.pow(B, 2);
    }
    private double cubicSolutionGama(double A, double B){
	return A*B + this.getW() * Math.pow(B,2) + this.getW() * Math.pow(B, 3);
    }
    
    
    
    public double calculateCompresibilityFactor(double temperature, double pressure,Phase phase){
	
	double A = get_A(temperature, pressure);
	double B = get_B(temperature, pressure);
	
      
        double alpha = cubicSolutionAlpha(B);//1-(this.getU() - 1 ) * B;
        double beta =cubicSolutionBeta(A, B);// A - this.getU() * B - this.getU() * Math.pow(B, 2) + this.getW() * Math.pow(B, 2);
        double gama = cubicSolutionGama(A, B);//A*B + this.getW() * Math.pow(B,2) + this.getW() * Math.pow(B, 3);
        
        double C =cubicSolutionC(beta, alpha);// 3 * beta - Math.pow(alpha, 2);
        double D = cubicSolutionD(alpha, beta, gama);//- Math.pow(alpha, 3) + 4.5d * alpha * beta - 13.5 * gama;
        double Q = cubicSolutionQ(C, D);//Math.pow(C, 3) + Math.pow(D, 2);
        //falta agregar la extrapolación
        if(Q <= 0){
            double theta = Math.acos(-D / Math.sqrt(- Math.pow(C, 3)));
            
            double liquidz = (1d/3d) * (alpha + 2 * Math.sqrt(-C) * Math.cos((theta / 3) + 120*(Math.PI / 180)));
            double vaporz = (1d/3d) * (alpha + 2 * Math.sqrt(-C) * Math.cos(theta / 3 ) );
            
            if(liquidz < B){
                liquidz = vaporz;
            }
            
            if(phase.equals(Phase.LIQUID)){
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
    
    public double get_A(double temperature, double pressure){
        return    a * pressure /Math.pow(Constants.R * temperature,2);
    }
    public double get_B(double temperature, double pressure){
        return b * pressure / (Constants.R * temperature); 
    }
    
    
//      public double calculateCompresibilityFactor(){
//	
//	
//	
//	return  calculateCompresibilityFactor(A, B, phase);
//    }
    
    public double calculateMolarVolume(double temperature, double pressure, Phase phase){
	double z = calculateCompresibilityFactor(temperature, pressure,phase);
	return calculateMolarVolume(temperature, pressure ,z);
    }
    
 public double calculateMolarVolume(double temperature, double pressure,double z){
      return z * Constants.R * temperature / pressure;
    }
 public double calculateFugacity(
	    double temperature,
	    double pressure,
               double parciala,
               double parcialb,
	 Phase aPhase
               ){
           
//           double A = get_A();
//           double B = get_B();
           
           double z = calculateCompresibilityFactor(temperature, pressure,aPhase);
           double volume = calculateMolarVolume(temperature,pressure,z);
   
        double L = calculateL(volume, b);
	
        double lnfug = -Math.log((volume-b)/volume) + (z-1) * (parcialb/b) + (a / (Constants.R * temperature * b))*((parcialb/b) - (parciala/a))* L - Math.log(z);
        return Math.exp(lnfug);

    }
       
       public double calculateL(double volume, double b){
               double delta = Math.sqrt(Math.pow(this.getU(),2) - 4 * this.getW());
               
                 if( delta == 0 ){
                     return b / volume;
                 }else{
                     return  (1 / delta)* Math.log((2 * volume + (u + delta) * b)/(2 * volume  + (u - delta) * b));
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

  
   
    /**
     * @return the a
     */
    public double get_a() {
	return a;
    }

    /**
     * @param a the a to set
     */
    public void set_a(double a) {
	this.a = a;
    }

    /**
     * @return the b
     */
    public double get_b() {
	return b;
    }

    /**
     * @param b the b to set
     */
    public void set_b(double b) {
	this.b = b;
    }
}
