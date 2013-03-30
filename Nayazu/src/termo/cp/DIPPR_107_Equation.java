package termo.cp;

import termo.Constants;

/**
 *
 * @author Hugo Redon Rivera
 */
public class DIPPR_107_Equation implements CpEquation{

    private double A;
    private double B;
    private double C;
    private double D;
    private double E;
    
    @Override
    public double cp(double temperature) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
        @Override
    public double Enthalpy(double temperature, double referenceTemperature, double enthalpyReference) {
        return enthalpyReference + IH(temperature) - IH(referenceTemperature);
        
    }
        
    private double IH(double T) {
        return A * T  + B * C * (1/Math.tanh(C /T)) - D * E * Math.tanh(E / T);
    }

        @Override
    public double idealGasEntropy(double temperature, double referenceTemeperature, double pressure , double referencePressure, double entropyReference) {
        return entropyReference +IS(temperature) - IS(referenceTemeperature) - Constants.R * Math.log(pressure / referencePressure);
    }
    private double IS(double T) {
      return A * Math.log(T) 
              +B * ((C / (T * Math.tanh(C / T)))- Math.log(Math.sinh(C /T))) 
              - D*((E /T)* Math.tanh(E /T) - Math.log(Math.cosh(E/T)));
    }


    /**
     * @return the A
     */
    public double getA() {
        return A;
    }

    /**
     * @param A the A to set
     */
    public void setA(double A) {
        this.A = A;
    }

    /**
     * @return the B
     */
    public double getB() {
        return B;
    }

    /**
     * @param B the B to set
     */
    public void setB(double B) {
        this.B = B;
    }

    /**
     * @return the C
     */
    public double getC() {
        return C;
    }

    /**
     * @param C the C to set
     */
    public void setC(double C) {
        this.C = C;
    }

    /**
     * @return the D
     */
    public double getD() {
        return D;
    }

    /**
     * @param D the D to set
     */
    public void setD(double D) {
        this.D = D;
    }

    /**
     * @return the E
     */
    public double getE() {
        return E;
    }

    /**
     * @param E the E to set
     */
    public void setE(double E) {
        this.E = E;
    }







}
