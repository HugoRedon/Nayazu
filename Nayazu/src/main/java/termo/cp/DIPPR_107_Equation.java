package termo.cp;

import java.io.Serializable;
import java.util.Objects;
import termo.Constants;
import termo.component.Component;

/**
 *
 * @author Hugo Redon Rivera
 */
public class DIPPR_107_Equation implements CpEquation,Serializable{
    
    private String equation;
    private double A;
    private double B;
    private double C;
    private double D;
    private double E;

    public DIPPR_107_Equation(Component component) {
	this.A = component.getA_dippr107Cp();
	this.B = component.getB_dippr107Cp();
	this.C = component.getC_dippr107Cp();
	this.D = component.getD_dippr107Cp();
	this.E = component.getE_dippr107Cp();
    }
    
    
    
       @Override
    public String getMathEquation() {
        return "<math xmlns='http://www.w3.org/1998/Math/MathML'><mrow><mi>Y</mi><mo>&nbsp;</mo><mo>&#x3d;</mo><mo>&nbsp;</mo><mi>A</mi><mo>&nbsp;</mo><mo>&#x2b;</mo><mo>&nbsp;</mo><mi>B</mi><mo>&sdot;</mo><msup><mfenced open='[' close=']' separators=','><mrow><mfrac><mrow><mfrac><mi>C</mi><mrow><mi>T</mi></mrow></mfrac></mrow><mrow><mi mathvariant='normal'>sinh</mi><mfenced open='(' close=')' separators=','><mrow><mfrac><mi>C</mi><mrow><mi>T</mi></mrow></mfrac></mrow></mfenced></mrow></mfrac></mrow></mfenced><mrow><mn>2</mn></mrow></msup><mo>&#x2b;</mo><mo>&nbsp;</mo><mi mathvariant='normal'>D</mi><mo>&nbsp;</mo><mo>&sdot;</mo><mo>&nbsp;</mo><msup><mfenced open='[' close=']' separators=','><mrow><mfrac><mrow><mfrac><mi>E</mi><mrow><mi>T</mi></mrow></mfrac></mrow><mrow><mi mathvariant='normal'>cosh</mi><mfenced open='(' close=')' separators=','><mrow><mfrac><mi>E</mi><mrow><mi>T</mi></mrow></mfrac></mrow></mfenced></mrow></mfrac></mrow></mfenced><mrow><mn>2</mn></mrow></msup></mrow></math>";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.equation);
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.A) ^ (Double.doubleToLongBits(this.A) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.B) ^ (Double.doubleToLongBits(this.B) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.C) ^ (Double.doubleToLongBits(this.C) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.D) ^ (Double.doubleToLongBits(this.D) >>> 32));
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.E) ^ (Double.doubleToLongBits(this.E) >>> 32));
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
        final DIPPR_107_Equation other = (DIPPR_107_Equation) obj;
        if (!Objects.equals(this.equation, other.equation)) {
            return false;
        }
        if (Double.doubleToLongBits(this.A) != Double.doubleToLongBits(other.A)) {
            return false;
        }
        if (Double.doubleToLongBits(this.B) != Double.doubleToLongBits(other.B)) {
            return false;
        }
        if (Double.doubleToLongBits(this.C) != Double.doubleToLongBits(other.C)) {
            return false;
        }
        if (Double.doubleToLongBits(this.D) != Double.doubleToLongBits(other.D)) {
            return false;
        }
        if (Double.doubleToLongBits(this.E) != Double.doubleToLongBits(other.E)) {
            return false;
        }
        return true;
    }

    
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
