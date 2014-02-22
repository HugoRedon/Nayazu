/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application.model;

import java.util.ArrayList;
import java.util.HashMap;
import termo.component.Component;
import termo.eos.Cubic;
import termo.eos.alpha.Alpha;

/**
 *
 * @author
 * Hugo
 */
public class Package {
    ThermoPackage eos;
    
    
    ArrayList<Component> components = new ArrayList();
    HashMap<Component,Double> fractions = new HashMap();
}



enum Operation{
    ELV,
    ELL,
    OnePhase,
    MathiasPolar,
    PhaseEnvelope,
    BinaryDiagram
}


