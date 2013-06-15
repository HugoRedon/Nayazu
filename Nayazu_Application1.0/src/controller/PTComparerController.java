package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
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
public class PTComparerController implements Initializable {
    @FXML LineChart<Number,Number> lineChartPT;
    @FXML Pane chartPane;
    @FXML TextField volumeTextField;
    @FXML TextField minTempTextField;
    @FXML TextField maxTempTextField;
    @FXML Button isocorichButton;
    
    NumberAxis xAxis = new NumberAxis();
    NumberAxis yAxis = new NumberAxis();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        xAxis.setLabel("Temperatura [K]");
       yAxis.setLabel("Presión [atm]");
         
        lineChartPT = new LineChart<>(xAxis, yAxis);     
        lineChartPT.setCreateSymbols(false);
        chartPane.getChildren().add(lineChartPT);
        isocorichButton.disableProperty().bind(UserProperties.arePropertiesReady().not());
    }    
    
    @FXML protected void addIsocorich(ActionEvent event){
         EOS selectedEos = UserProperties.getSelectedEOS();

        double volume = Double.valueOf(volumeTextField.getText());
        double minTemp = Double.valueOf(minTempTextField.getText());
        double maxTemp = Double.valueOf(maxTempTextField.getText());
        
        double step = (maxTemp - minTemp)/100.0d;

        XYChart.Series isocorich = new XYChart.Series();
   
        String volumeText = volume + "[L/mol]";
        isocorich.setName( selectedEos + " " +  volumeText );
     
        for (double temp = minTemp; temp < maxTemp; temp += step){            
//            double pressure = 0;//selectedEos.calculatePressure(temp, volume);
           
             double pressure = 0;
            if(selectedEos.isCubic()){
                Cubic eos = (Cubic)selectedEos;
                BinaryInteractionParameter k = UserProperties.getInteractionParameters();
                pressure = eos.calculatePressure(temp, 
                        volume, 
                        UserProperties.getSelectedComponentsArrayList(),
                        UserProperties.getFractions(),
                        k
                        );
               
            }else{
                IdealGas eos = (IdealGas)selectedEos;
                 pressure = eos.getPressure(temp, volume);
            }
            
            isocorich.getData().add(new XYChart.Data(temp,pressure));
        }
        lineChartPT.getData().add(isocorich);
    }
    @FXML protected void cleanChart(ActionEvent event){
         // deberia ser solamente chart.clear() pero lanza error
        chartPane.getChildren().remove(lineChartPT);        
        lineChartPT = new LineChart<>(xAxis, yAxis);      
        lineChartPT.setCreateSymbols(false);
        chartPane.getChildren().add(lineChartPT);    
    }
    
}
