/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package termo.eos.mixingRule;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;
import termo.activityModel.WilsonActivityModel;
import termo.binaryParameter.ActivityModelBinaryParameter;
import termo.binaryParameter.BinaryInteractionParameter;
import termo.binaryParameter.InteractionParameter;
import termo.component.Component;
import termo.component.VanDerWaalsParameters;
import termo.eos.Cubic;
import termo.eos.EquationOfStateFactory;
import termo.eos.alpha.Alpha;
import termo.eos.alpha.AlphaFactory;
import termo.phase.Phase;
import termo.substance.MixtureSubstance;
import termo.substance.PureSubstance;

/**
 *
 * @author
 * Hugo
 */
public class HuronVidalMixingRuleTest {
    Component ethane;
    Component propane;
    HashMap<PureSubstance, Double> fractions;
    HuronVidalMixingRule instance;
    InteractionParameter k = new ActivityModelBinaryParameter();
    
    ArrayList<Component> components = new ArrayList<>();
    PureSubstance ci;
    public HuronVidalMixingRuleTest() {
	ethane = new Component();
	
	ethane.setName("Ethane");
	ethane.setAcentricFactor(0.09781);
	ethane.setCriticalTemperature(305.43);
	ethane.setCriticalPressure(48.1595*101325);
	ethane.setPrsvKappa(0.02669);
	
	propane = new Component();
	
	 propane.setName("Propane");
	propane.setAcentricFactor(0.15416);
	propane.setCriticalTemperature(369.82);
	propane.setCriticalPressure(41.9396*101325);
	propane.setPrsvKappa(0.03136);
	
	components.add(ethane);
	components.add(propane);
	
	
	fractions = new HashMap();
	
	Cubic eos = EquationOfStateFactory.pengRobinsonBase();
	Alpha alpha = AlphaFactory.getStryjekAndVeraExpression();
	
	ci = new PureSubstance(eos, alpha, ethane, Phase.VAPOR);
	PureSubstance cj = new PureSubstance(eos, alpha, propane, Phase.VAPOR);
	
	fractions.put(ci, 0.3);
	fractions.put(cj, 0.7);
	
	
	 instance = new HuronVidalMixingRule(new WilsonActivityModel(),eos);
	
	
    }

    @Test
    public void testA() {
	System.out.println("a");
	double temperature = 298;

	
	
	double expResult = 9.70355653e5;
	double result = instance.a(temperature, fractions, k);
	assertEquals(expResult, result, 1e-3);

    }

    @Test
    public void testB() {
	System.out.println("b");
	HashMap<PureSubstance, Double> fractions = null;
	HuronVidalMixingRule instance = null;
	double expResult = 0.0;
	double result = instance.b(fractions);
	assertEquals(expResult, result, 0.0);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    @Test
    public void testOneOverNParcial_aN2RespectN() {
	System.out.println("oneOverNParcial_aN2RespectN");
	double temperature = 298;
	

	
	double expResult = 0.0;
	double result = instance.oneOverNParcial_aN2RespectN(temperature, ci, fractions, k);
	assertEquals(expResult, result, 1e-3);

    }
    
    
    @Test
    public void vdwFugacitiy(){
	VDWMixingRule vdw = new VDWMixingRule();
	InteractionParameter k = new InteractionParameter();
	
	
	MixtureSubstance ms = new MixtureSubstance(EquationOfStateFactory.pengRobinsonBase(), AlphaFactory.getStryjekAndVeraExpression(), vdw, components, Phase.LIQUID, k);
	
	ms.setMolarFractions(fractions);
	double expResult = 0;
	double result = ms.calculateFugacity(ci, 298, 101325);
		
	fail();
    }
    
    
    @Test public void huronFugacity(){
	InteractionParameter k = new ActivityModelBinaryParameter();
	
	MixtureSubstance ms = new MixtureSubstance(EquationOfStateFactory.pengRobinsonBase(), AlphaFactory.getStryjekAndVeraExpression(), instance, components,Phase.LIQUID,k);
	
	ms.setMolarFractions(fractions);
	double expResult = 24.8948;
	double result = ms.calculateFugacity(ci, 298, 101325);
	assertEquals(expResult, result,1e-3);
	
    }

    @Test
    public void testTemperatureParcial_a() {
	System.out.println("temperatureParcial_a");
	double temperature = 0.0;
	ArrayList<Component> components = null;
	HashMap<Component, Double> fractions = null;
	HashMap<Component, Double> single_as = null;
	HashMap<Component, Double> single_bs = null;
	HashMap<Component, Double> alphaDerivatives = null;
	BinaryInteractionParameter k = null;
	HuronVidalMixingRule instance = null;
	double expResult = 0.0;
	double result = instance.temperatureParcial_a(temperature, components, fractions, single_as, single_bs, alphaDerivatives, k);
	assertEquals(expResult, result, 0.0);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    @Test
    public void testGetL() {
	System.out.println("getL");
	HuronVidalMixingRule instance = null;
	double expResult = 0.0;
	double result = instance.getL();
	assertEquals(expResult, result, 0.0);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }

    @Test
    public void testSetL() {
	System.out.println("setL");
	double L = 0.0;
	HuronVidalMixingRule instance = null;
	instance.setL(L);
	// TODO review the generated test code and remove the default call to fail.
	fail("The test case is a prototype.");
    }
}