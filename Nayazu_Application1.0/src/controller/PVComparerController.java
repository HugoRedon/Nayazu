package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import termo.binaryParameter.BinaryInteractionParameter;
import termo.eos.Cubic;
import termo.eos.EOS;
import termo.eos.IdealGas;



/**
 * FXML Controller class
 *
 * @author Hugo Redon Rivera
 */
public class PVComparerController implements Initializable {
     
    @FXML Pane chartPane;
    @FXML TextField temperatureTextField;
    @FXML TextField minVolTextField;
    @FXML TextField maxVolTextField;
    @FXML Button isothermButton;   
    @FXML ListView isothermsListView ;
   
    
    LineChart<Number,Number> chart;
    NumberAxis xAxis;
    NumberAxis yAxis;
    
    Series<Number,Number> selectedSerie;
    
    ObservableList<Series<Number,Number>> isotherms  = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        xAxis = new NumberAxis();
        xAxis.setLabel("Volumen molar [L / mol]");
        yAxis = new NumberAxis();
        yAxis.setLabel("Presión [atm]");       
        chart = new LineChart<>(xAxis, yAxis);     
        chart.setCreateSymbols(false);
        chartPane.getChildren().add(chart);
        
        isothermButton.disableProperty().bind(UserProperties.arePropertiesReady().not());
            
        isothermsListView.setItems(isotherms);      
        chart.setData(isotherms);
        
       
         
          
      
    }  
    
    
    
    
    @FXML protected void addIsotherm(ActionEvent event){

        EOS selectedEos = UserProperties.getSelectedEOS();

        double temperature = Double.valueOf(temperatureTextField.getText());
        double minVolume = Double.valueOf(minVolTextField.getText());
        double maxVolume = Double.valueOf(maxVolTextField.getText());
        
        double step = (maxVolume- minVolume)/100.0d;
        Series isotherm = new XYChart.Series();
        
        String temperatureText = temperature + "K";
        isotherm.setName( selectedEos + " " +  temperatureText );
       
        for (double volume = minVolume; volume < maxVolume; volume += step){            
          //  double pressure = selectedEos.calculatePressure(temperature, volume);
           // isotherm.getData().add(new XYChart.Data(volume,pressure));
            double pressure;
            if(selectedEos.isCubic()){
                Cubic eos = (Cubic)selectedEos;
                BinaryInteractionParameter k = UserProperties.getInteractionParameters();
                 pressure = eos.calculatePressure(temperature, 
                         volume, 
                         UserProperties.getSelectedComponentsArrayList(), 
                         UserProperties.getFractions(),
                         k);               
            }else{
                IdealGas eos = (IdealGas)selectedEos;
                 pressure = eos.getPressure(temperature, volume);
            }
             isotherm.getData().add(new XYChart.Data(volume,pressure));
        }
       
        isotherms.add(isotherm);
    }
    
    
//    @FXML protected void addIsobar(ActionEvent event){
//        
//    }
    @FXML protected void cleanChart(ActionEvent event){
        // deberia ser solamente chart.clear() pero lanza error
        chartPane.getChildren().remove(chart);        
        chart = new LineChart<>(xAxis, yAxis);      
        chart.setCreateSymbols(false);
        chartPane.getChildren().add(chart);    
        isotherms = FXCollections.observableArrayList();
       isothermsListView.setItems(isotherms);
        chart.setData(isotherms);
    }
  
}
