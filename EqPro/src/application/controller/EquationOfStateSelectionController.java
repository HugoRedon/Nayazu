
package application.controller;

import application.model.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

/**
 * FXML
 * Controller
 * class
 *
 * @author
 * Hugo
 */
public class EquationOfStateSelectionController  extends EQController implements Initializable,ChangeListener<Toggle> {

    @FXML private RadioButton rksmRB;
    @FXML private RadioButton prsvRB;
    
    @FXML private ToggleGroup equationTG;
    
    @FXML private RadioButton vdw1RB;
    @FXML private RadioButton vdw2RB;
    @FXML private RadioButton mkpRB;
    
    @FXML private RadioButton pangiotopoulosRB;
    @FXML private RadioButton sandovalRB;
    @FXML private RadioButton huronWilsonRB;
    @FXML private RadioButton huronNRTLRB;
    @FXML private RadioButton wongWilsonRB;
    @FXML private RadioButton wongNRTLRB;
    
    
    @FXML private ToggleGroup mixingRuleTG;
    
    
    /**
     * Initializes
     * the
     * controller
     * class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
	
	
	
	rksmRB.setUserData(EquationOfState.RedlichKwongSoaveMathias);
	prsvRB.setUserData(EquationOfState.PengRobinsonStryjekVera);
	
	
	vdw1RB.setUserData(MixingRuleType.VanDerWaals1);
	vdw2RB.setUserData(MixingRuleType.VanDerWaals2);
	mkpRB.setUserData(MixingRuleType.MathiasKlotzPrausnitz);
	
	pangiotopoulosRB.setUserData(MixingRuleType.PanagiotopoulosReid);
	sandovalRB.setUserData(MixingRuleType.SandovalWilczecVeraVera);
	huronWilsonRB.setUserData(MixingRuleType.HuronVidalWilson);
	huronNRTLRB.setUserData(MixingRuleType.HuronVidalNRTL);
	wongWilsonRB.setUserData(MixingRuleType.WongSandlerWilson);
	wongNRTLRB.setUserData(MixingRuleType.WongSandlerNRTL);
	
	equationTG.selectedToggleProperty().addListener(this);
	mixingRuleTG.selectedToggleProperty().addListener(this);
	
	
	equationTG.selectToggle(rksmRB);
	mixingRuleTG.selectToggle(vdw1RB);
	
    }    

    
    @Override
    public void changed(ObservableValue<? extends Toggle> ov, Toggle tOld, Toggle tnew) {
	
	if(tnew.getToggleGroup().equals(equationTG)){
	    Eqfases2Copy.getThermoPackage().setEquationOfState((EquationOfState)tnew.getUserData());
	}else if(tnew.getToggleGroup().equals(mixingRuleTG)){
	    Eqfases2Copy.getThermoPackage().setMixingRule((MixingRuleType)tnew.getUserData());
	}
    }
    
    

    @Override
    public void done() {
//	System.out.println("ecuacion de estado y alpha seleccionados");
//	
//	
//	System.out.println(equationTG.getSelectedToggle().toString());
//	
//	
//	if(equationTG.getSelectedToggle().equals(prsvRB)){
//	    Eqfases2Copy.setEos(EquationOfStateFactory.pengRobinsonBase());
//	    Eqfases2Copy.setAlpha(AlphaFactory.getStryjekAndVeraExpression());
//	}if(mixingRuleTG.getSelectedToggle().equals(vdw1RB)){
//	    Eqfases2Copy.setMixingRule(new VDWMixingRule());
//	}
    }

    @Override
    public void cancel() {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
