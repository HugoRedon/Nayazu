package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import termo.binaryParameter.BinaryInteractionParameter;
import termo.component.Component;
import termo.eos.Cubic;
import termo.eos.EOS;
import termo.eos.IdealGas;
import termo.phase.Phase;

/**
 * FXML Controller class
 *
 * @author Hugo Redon Rivera
 */
public class ZComparerController implements Initializable {
    @FXML TextField temperatureTextField, pressureTextField;
    @FXML TextField liquidzTextField ,vaporzTextField,liquidFugTextField,vaporFugTextField, liquidvTextField,vaporvTextField;
    @FXML Label faseLabel;
    @FXML Text liquidText,vaporText;
    @FXML ComboBox componentsComboBox;
    SimpleBooleanProperty oneRoot = new SimpleBooleanProperty(true);
    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        faseLabel.visibleProperty().bind(oneRoot.not());
        vaporzTextField.visibleProperty().bind(oneRoot.not());
        vaporFugTextField.visibleProperty().bind(oneRoot.not());
        vaporText.visibleProperty().bind(oneRoot.not());
        liquidText.visibleProperty().bind(oneRoot.not());
        vaporvTextField.visibleProperty().bind(oneRoot.not());
        
        componentsComboBox.setItems(UserProperties.getSelectedComponentsObservableList());
        componentsComboBox.visibleProperty().bind(UserProperties.needsMixingRule());
    
    }    
    
    @FXML protected void calculateCompresibility(ActionEvent event){
       EOS eos = UserProperties.getSelectedEOS();
       
       double temperature = Double.valueOf(temperatureTextField.getText());
       double pressure = Double.valueOf(pressureTextField.getText());
       
       ArrayList<Component> components = UserProperties.getSelectedComponentsArrayList();
       Component selectedComponent ;
       if(components.size() == 1){
           selectedComponent = components.get(0);
       }else{
           selectedComponent = (Component)componentsComboBox.getSelectionModel().getSelectedItem();
       }
       HashMap<Component,Double> fractions = UserProperties.getFractions();
       
        if(eos.isCubic()){
            Cubic cubiceos = (Cubic)eos;
            BinaryInteractionParameter k = UserProperties.getInteractionParameters();
            oneRoot.set(cubiceos.oneRoot(pressure, temperature, components, UserProperties.getFractions(),k));
            
            if(oneRoot.get()){
                double z  = cubiceos.calculateCompresibilityFactor(pressure, temperature,components,fractions,null,k);
                liquidzTextField.setText(String.valueOf(z));
                
                double vol = cubiceos.calculateVolume(temperature, pressure, components, fractions, null,k);
                liquidvTextField.setText(String.valueOf(vol));
                
                double fug = cubiceos.calculateFugacity(pressure, temperature, components, selectedComponent,fractions, null,k);
                liquidFugTextField.setText(String.valueOf(fug));
            }else{
               double liquidz  = cubiceos.calculateCompresibilityFactor(pressure, temperature,components,fractions,Phase.LIQUID,k);
               double vaporz =  cubiceos.calculateCompresibilityFactor(pressure, temperature,components,fractions,Phase.VAPOR,k);
               liquidzTextField.setText(String.valueOf(liquidz));
               vaporzTextField.setText(String.valueOf(vaporz));
               
               double liquidVol = cubiceos.calculateVolume(temperature, pressure, components, fractions, Phase.LIQUID,k);
                liquidvTextField.setText(String.valueOf(liquidVol));
                double vaporVol = cubiceos.calculateVolume(temperature, pressure, components, fractions, Phase.VAPOR,k);
                vaporvTextField.setText(String.valueOf(vaporVol));            
               
                double liquidFug = cubiceos.calculateFugacity(pressure, temperature, components, selectedComponent,fractions, Phase.LIQUID,k);
                liquidFugTextField.setText(String.valueOf(liquidFug));
               
                double vaporFug = cubiceos.calculateFugacity(pressure, temperature, components, selectedComponent,fractions, Phase.VAPOR,k);
                vaporFugTextField.setText(String.valueOf(vaporFug));
            }
           
        }else {
            IdealGas ideal = (IdealGas)eos;
            oneRoot.set(true);
            double z = ideal.getCompresibilityFactor();
            liquidzTextField.setText(String.valueOf(z));
            double fug = ideal.getFugacity();  
            liquidFugTextField.setText(String.valueOf(fug));
        }        
    }
}
