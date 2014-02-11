package termo.substance;

import org.junit.Test;
import static org.junit.Assert.*;
import termo.component.Component;
import termo.eos.Cubic;
import termo.eos.EquationOfStateFactory;
import termo.eos.alpha.Alpha;
import termo.eos.alpha.AlphaFactory;
import termo.phase.Phase;

/**
 *
 * @author
 * Hugo
 */
public class PureSubstanceTest {
     PureSubstance substance ; 
    public PureSubstanceTest() {
	Component ethane = new Component();
	
	ethane.setAcentricFactor(0.09781);
	ethane.setCriticalTemperature(305.43);
	ethane.setCriticalPressure(48.1595*101325);
	ethane.setPrsvKappa(0.02669);//en eqfases2 tiene un signo negativo ...
	
	Cubic eos = EquationOfStateFactory.pengRobinsonBase();
	Alpha alpha = AlphaFactory.getStryjekAndVeraExpression();
	
	
	
	substance = new PureSubstance();
	substance.setPhase(Phase.LIQUID);
	substance.setCubicEquationOfState(eos);
	substance.setAlpha(alpha);
	substance.setComponent(ethane);
    }

    @Test
    public void testCalculateFugacity() {
	System.out.println("calculateFugacity");
	double temperature = 0.0;
	double pressure = 0.0;
	Phase aPhase = null;
	PureSubstance instance = new PureSubstance();
	double expResult = 0.0;
	double result = instance.calculateFugacity(temperature, pressure);
	assertEquals(expResult, result, 0.0);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

//    @Test
//    public void testEquals() {
//	System.out.println("equals");
//	PureSubstance substance = null;
//	PureSubstance instance = new PureSubstance();
//	boolean expResult = false;
//	boolean result = instance.equals(substance);
//	assertEquals(expResult, result);
//	// TODO review the generated test code and remove the default call to fail.
//	fail("The test case is a prototype.");
//    }
//
//    @Test
//    public void testToString() {
//	System.out.println("toString");
//	PureSubstance instance = new PureSubstance();
//	String expResult = "";
//	String result = instance.toString();
//	assertEquals(expResult, result);
//	// TODO review the generated test code and remove the default call to fail.
//	fail("The test case is a prototype.");
//    }
    
    @Test
    public void testCalculateMolarVolume(){
	System.out.println("calculateMolarVolume: in PureSubstance it is not a formal test");
	double temperature = 200;
	double pressure = 1*101325;
	
	double expResult = 52.815879/1000;
	double result = substance.calculateMolarVolume(temperature, pressure);
	assertEquals(expResult, result, 1e-3);
	
    }
    
    @Test
    public void testCalculateCompresibilityFactor(){
	System.out.println("calculateCompresibilityFactor :in PureSubstance it is not a formal test");
	double temperature = 200;
	double pressure = 1*101325;
	
	double expResult = 0.003218;
	double result = substance.calculateCompresibilityFactor(temperature, pressure);
	assertEquals(expResult, result, 1e-3);
    }
    
    @Test
    public void testCalculateIdealGasEnthalpy() {
	System.out.println("calculateIdealGasEnthalpy");
	double temperature = 0.0;
	PureSubstance instance = new PureSubstance();
	double expResult = 0.0;
	double result = instance.calculateIdealGasEnthalpy(temperature);
	assertEquals(expResult, result, 0.0);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    @Test
    public void testTemperatureParcial_a() {
	System.out.println("temperatureParcial_a");
	double temperature = 0.0;
	PureSubstance instance = new PureSubstance();
	double expResult = 0.0;
	double result = instance.temperatureParcial_a(temperature);
	assertEquals(expResult, result, 0.0);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    @Test
    public void testOneOver_N_Parcial_a() {
	System.out.println("oneOver_N_Parcial_a");
	double temperature = 0.0;
	PureSubstance pureSubstance = null;
	PureSubstance instance = new PureSubstance();
	double expResult = 0.0;
	double result = instance.oneOver_N_Parcial_a(temperature, pureSubstance);
	assertEquals(expResult, result, 0.0);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    @Test
    public void testCalculate_a_cubicParameter() {
	System.out.println("calculate_a_cubicParameter");
	double temperature = 0.0;
	PureSubstance instance = new PureSubstance();
	double expResult = 0.0;
	double result = instance.calculate_a_cubicParameter(temperature);
	assertEquals(expResult, result, 0.0);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    @Test
    public void testCalculate_b_cubicParameter() {
	System.out.println("calculate_b_cubicParameter");
	PureSubstance instance = new PureSubstance();
	double expResult = 0.0;
	double result = instance.calculate_b_cubicParameter();
	assertEquals(expResult, result, 0.0);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    @Test
    public void testCalculateIdealGasEntropy() {
	System.out.println("calculateIdealGasEntropy");
	double temperature = 0.0;
	double pressure = 0.0;
	PureSubstance instance = new PureSubstance();
	double expResult = 0.0;
	double result = instance.calculateIdealGasEntropy(temperature, pressure);
	assertEquals(expResult, result, 0.0);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

   
   
    
    @Test
    public void testGetAcentricFactorBasedVaporPressure() {
	System.out.println("getAcentricFactorBasedVaporPressure");
	double temperature = 298;
	
	double expResult = 41.57334499;
	double result = substance.getAcentricFactorBasedVaporPressure(temperature);
	assertEquals(expResult, result/101325, 1e-3);
	
    }
}