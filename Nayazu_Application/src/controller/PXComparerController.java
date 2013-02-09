package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import termo.component.BinaryInteractionParameters;
import termo.component.Component;
import termo.component.VanDerWaalsParameters;
import termo.data.ExperimentalDataBinary;
import termo.eos.Cubic;
import termo.eos.EOS;
import termo.equilibrium.BubblePoint;
import termo.equilibrium.DewPoint;
import termo.equilibrium.EquilibriaPhaseSolution;
import termo.equilibrium.PhaseEquilibria;
import termo.optimization.VDWBinaryParameterOptimizer;
import view.ApplicationStartup;

/**
 * FXML Controller class
 *
 * @author Hugo Redon Rivera
 */
public class PXComparerController implements Initializable {
   
    @FXML TextField pressureTemperatureTextField, k12TextField,k21TextField;
    @FXML ComboBox component1ComboBox, component2ComboBox;
    @FXML ToggleButton dewToggle,bubbleToggle,symmetricToggle,tempToggleButton,pressToggleButton;
    @FXML ToggleGroup typePointToggleGroup,pressTempToggleGroup;
    @FXML LineChart<Number,Number> PXChart;
    @FXML Label tempPressLabel;
    
    ObservableList<Series<Number,Number>> lineCharts = FXCollections.observableArrayList();;
    Series solutionLine;
    Series mixtureLine;
    
    Series vaporExpLine;
    Series liquidExpLine;
    
    VanDerWaalsParameters  kinteraction;
    Component component1;
    Component component2;
    SimpleBooleanProperty component1Selected;
    SimpleBooleanProperty component2Selected;
    SimpleBooleanProperty twoComponentsSelected;
    SimpleBooleanProperty binaryInteractionSelected;
    SimpleBooleanProperty temperatureSelected;
    SimpleBooleanProperty typeSelected;
    PhaseEquilibria bubbleCalculator;
    PhaseEquilibria dewCalculator;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        k12TextField.setText(String.valueOf(0.0));
        k21TextField.setText(String.valueOf(0.0));
        solutionLine =new XYChart.Series();
        mixtureLine = new XYChart.Series();
        
        liquidExpLine = new XYChart.Series();
        vaporExpLine = new XYChart.Series();
       
           int index = 0 ;
           for(double i = 0.0; i <= 1.01; i += 0.01){
                solutionLine.getData().add(index, new XYChart.Data<>(i, 0 * i));
                mixtureLine.getData().add(index,new XYChart.Data<>(1-i, (1-i) * 0));         
                 index++;
            }
  bubbleCalculator = new BubblePoint();
           dewCalculator = new DewPoint();         
        
        lineCharts.addAll(solutionLine,mixtureLine);
        PXChart.setData(lineCharts);
     //   PXChart.setCreateSymbols(false);
        kinteraction = new VanDerWaalsParameters();
        kinteraction.setSymmetric(false);   
        ObservableList<Component> allComponents = UserProperties.getSelectedComponentsObservableList();
        component1ComboBox.setItems(allComponents);
        component2ComboBox.setItems(allComponents);
        component1Selected = new SimpleBooleanProperty(false);
        component2Selected = new SimpleBooleanProperty(false);
        twoComponentsSelected = new SimpleBooleanProperty(false);
        binaryInteractionSelected = new SimpleBooleanProperty(true);
        temperatureSelected = new SimpleBooleanProperty(false);
        typeSelected = new SimpleBooleanProperty(false);
        twoComponentsSelected.bind(component1Selected.and(component2Selected));       
        
        k21TextField.editableProperty().bind(symmetricToggle.selectedProperty().not());
       k21TextField.disableProperty().bind(k21TextField.editableProperty().not());
        k21TextField.editableProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(!newValue ){
                    k21TextField.textProperty().bind(k12TextField.textProperty());
                }else{
                    k21TextField.textProperty().unbind();
                }
            }
        });
        typeSelected.bind(dewToggle.selectedProperty().or(bubbleToggle.selectedProperty()));
    }    
    Stage secondaryStage;
    
     @FXML protected void deployExperimentalDataWindow(ActionEvent event ) throws IOException{
    
        UserProperties.setComponent1(component1);
       UserProperties.setComponent2(component2);

       UserProperties.setBinaryComponentsObservable(component1ComboBox.getItems());
         
         if(secondaryStage == null){
            secondaryStage = new Stage();
            Pane pane = (Pane)FXMLLoader.load(ApplicationStartup.class.getResource("BinaryExperimentalDataWindow.fxml"));
            Scene scene = new Scene(pane,626,472);

            secondaryStage.setScene(scene);
       }
       secondaryStage.show();
       secondaryStage.toFront();
    }
    
    protected void drawExperimentalData(
            ArrayList<ExperimentalDataBinary> dataList
            ){
        
        for (ExperimentalDataBinary data : dataList){
            double x = data.getLiquidFraction();
            double t = data.getTemperature();
            double y = data.getVaporFraction();
            
            XYChart.Data<Number,Number> xt = new XYChart.Data<>();
            xt.setXValue(x);
            xt.setYValue(t);
            liquidExpLine.getData().add(xt);
            
            XYChart.Data<Number,Number> yt = new XYChart.Data<>();
            yt.setXValue(y);
            yt.setYValue(t);    
            vaporExpLine.getData().add(yt);
        }
        lineCharts.addAll(liquidExpLine,vaporExpLine);
    }
    @FXML protected void drawExperimental(ActionEvent event){
        ArrayList<ExperimentalDataBinary> exper = UserProperties.getExperimentalDataArrayList();
        drawExperimentalData(exper);
    }
    
   @FXML  protected void optimizeBinaryParameter(ActionEvent event){
       VDWBinaryParameterOptimizer optimizer = new VDWBinaryParameterOptimizer();
       Cubic eos =(Cubic) UserProperties.getSelectedEOS();
       ArrayList<ExperimentalDataBinary> exp = UserProperties.getExperimentalDataArrayList();
       double k12 = optimizer.optimizeParameter(eos, exp);
       
       kinteraction.setValue(component1, component2, k12, true);
       k12TextField.setText(String.valueOf(k12));
       drawDiagram();
   }

    protected void drawDiagram(){
     
        getKInteractions();
        double pressureTemperature =Double.valueOf( pressureTemperatureTextField.getText());
        double tol = 0.00001;
        
        EOS eos = UserProperties.getSelectedEOS();
        if(eos.isCubic()){
            Cubic cubic =(Cubic) eos;
           ToggleButton type = (ToggleButton)typePointToggleGroup.getSelectedToggle();
           ToggleButton pressTemp = (ToggleButton)pressTempToggleGroup.getSelectedToggle();
           PhaseEquilibria calculator = (type.equals(bubbleToggle))? bubbleCalculator: dewCalculator;
            if(pressTemp.equals(pressToggleButton)){
              drawPressureDiagram(pressureTemperature, cubic, kinteraction,tol,calculator);  
            }else {
                drawTemperatureDiagram(pressureTemperature,cubic,kinteraction,tol,calculator);
            }
        }
    }
    

     protected void drawTemperatureDiagram(double pressure, 
            Cubic cubic,
            BinaryInteractionParameters kinteraction,
            double tol,
            PhaseEquilibria temperatureCalculator
            ){
            ArrayList<EquilibriaPhaseSolution> diagramT = 
                    temperatureCalculator.getTemperatureDiagram(pressure, component1, component2, kinteraction, cubic, tol);
         
                for(EquilibriaPhaseSolution sol : diagramT){
                    int index = diagramT.indexOf(sol);
                    XYChart.Data<Number,Number> solutionData= ( XYChart.Data<Number,Number> ) this.solutionLine.getData().get(index);
                    XYChart.Data<Number,Number> mixtureData= ( XYChart.Data<Number,Number> ) mixtureLine.getData().get(index);
       
                    solutionData.setXValue(sol.getSolutionFractions().get(component1));
                    solutionData.setYValue(sol.getTemperature());
    
                    mixtureData.setXValue(sol.getMixtureFractions().get(component1));
                    mixtureData.setYValue(sol.getTemperature());
                }
    }
     
     
     protected void drawPressureDiagram(double temperature,
            Cubic cubic,
            BinaryInteractionParameters kinteraction,
            double tol,
            PhaseEquilibria pressureCalculator){
         ArrayList<EquilibriaPhaseSolution> diagramP = 
                   pressureCalculator.getPressureDiagram(temperature, component1, component2, kinteraction, cubic, tol);
         
               for(EquilibriaPhaseSolution sol : diagramP){
                    int index = diagramP.indexOf(sol);
                    XYChart.Data<Number,Number> solutionData= ( XYChart.Data<Number,Number> ) this.solutionLine.getData().get(index);
                    XYChart.Data<Number,Number> mixtureData= ( XYChart.Data<Number,Number> ) mixtureLine.getData().get(index);
       
                    solutionData.setXValue(sol.getSolutionFractions().get(component1));
                    solutionData.setYValue(sol.getPressure());
    
                    mixtureData.setXValue(sol.getMixtureFractions().get(component1));
                    mixtureData.setYValue(sol.getPressure());
                }
     }
           
    @FXML protected void selectComponent1(ActionEvent event){
        component1 = (Component)component1ComboBox.getSelectionModel().getSelectedItem();
        actualizeBinaryInteractions();
        component1Selected.set(true);
    }
    
    protected void actualizeBinaryInteractions(){
            VanDerWaalsParameters k = (VanDerWaalsParameters)UserProperties.getInteractionParameters();
        double k12 = k.getValue(component1, component2);
        double k21 = k.getValue(component2, component1);
        symmetricToggle.setSelected(false);
        k12TextField.setText(String.valueOf(k12));
        k21TextField.setText(String.valueOf(k21));
    }
    
    @FXML protected void selectComponent2(ActionEvent event){
        component2 = (Component)component2ComboBox.getSelectionModel().getSelectedItem();
        actualizeBinaryInteractions();
        component2Selected.set(true);
    }

        @FXML protected void paintIt(ActionEvent event){
            drawDiagram();
        }
        
        @FXML protected void pressureKind(ActionEvent event){
            tempPressLabel.setText("Temperatura:");
        }
        @FXML protected void temperatureKind(ActionEvent event){
            tempPressLabel.setText("Presión");
        }
        
        

    private void getKInteractions() {
        double k12 =Double.valueOf( k12TextField.getText());
        double k21 = Double.valueOf(k21TextField.getText());
        boolean isSymmetric = symmetricToggle.isSelected();
        kinteraction.setValue(component1, component2, k12, isSymmetric );        
        kinteraction.setValue(component2, component1, k21, isSymmetric);
    }
}
