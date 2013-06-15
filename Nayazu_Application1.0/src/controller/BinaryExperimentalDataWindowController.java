package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import termo.component.Component;
import termo.data.ExperimentalDataBinary;

/**
 * FXML Controller class
 *
 * @author Hugo Redon Rivera
 */
public class BinaryExperimentalDataWindowController implements Initializable {

    @FXML TableView dataTable;
    @FXML TableColumn temperatureTableColumn,pressureTableColumn,xTableColumn,yTableColumn;
    @FXML TextField tempTextField,pressTextField,xTextField,yTextField;
    @FXML ComboBox referenceComboBox;
    
    ObservableList<ExperimentalDataBinary> data = FXCollections.observableArrayList();
    
    Component methanol;
    Component water;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
        temperatureTableColumn.setCellValueFactory(new PropertyValueFactory<ExperimentalDataBinary,Double>("temperature"));
        pressureTableColumn.setCellValueFactory(new PropertyValueFactory<ExperimentalDataBinary,Double>("pressure")); 
        xTableColumn.setCellValueFactory(new PropertyValueFactory<ExperimentalDataBinary,Double>("liquidFraction")); 
        yTableColumn.setCellValueFactory(new PropertyValueFactory<ExperimentalDataBinary,Double>("vaporFraction")); 
        
        referenceComboBox.setItems(
                UserProperties.getBinaryComponentsObservable()
                );
        
        dataTable.setItems(data);
    }    
    
     ArrayList<ExperimentalDataBinary> experimental;
    private void setPredefined(){
        
        Component comp1  =UserProperties.getComponent1();
        Component comp2 = UserProperties.getComponent2();
        
         experimental =  new ArrayList<>();
        
        double pressure = 0.14991;
       
        methanol = comp1;       
        water =comp2;
        
        experimental = new ArrayList<>();

        experimental.add(new ExperimentalDataBinary(0, methanol, water, 327.4, pressure, 0, 0));
        experimental.add(new ExperimentalDataBinary(0, methanol, water,318.05, pressure, 0.1, 0.433386));
        experimental.add(new ExperimentalDataBinary(0, methanol, water,312.4, pressure, 0.2, 0.62119));
        experimental.add(new ExperimentalDataBinary(0, methanol, water,308.55, pressure, 0.3, 0.725289));
        experimental.add(new ExperimentalDataBinary(0, methanol, water,305.7, pressure, 0.4, 0.79242));     
        experimental.add(new ExperimentalDataBinary(0, methanol, water,303.5, pressure, 0.5, 0.840656));
        experimental.add(new ExperimentalDataBinary(0, methanol, water,301.7, pressure, 0.6, 0.87857));
        experimental.add(new ExperimentalDataBinary(0, methanol, water,300.1, pressure, 0.7, 0.910835));
        experimental.add(new ExperimentalDataBinary(0, methanol, water,298.7, pressure, 0.8, 0.940365));
        experimental.add(new ExperimentalDataBinary(0, methanol, water,297.4, pressure, 0.9, 0.969404));
        experimental.add(new ExperimentalDataBinary(0, methanol, water,296.1, pressure, 1, 1));

    }
    @FXML  protected void rapidoejemplo(ActionEvent event){
        setPredefined();
        data.addAll(experimental);
    }
    

        
    @FXML protected void addData(ActionEvent event){
        double temperature =Double.valueOf( tempTextField.getText());
        double pressure =Double.valueOf(pressTextField.getText());
        double x = Double.valueOf(xTextField.getText());
        double y = Double.valueOf(yTextField.getText());
        
        Component referenceComponent = (Component)referenceComboBox.getSelectionModel().getSelectedItem();
        
        Component comp1  =UserProperties.getComponent1();
        Component comp2 = UserProperties.getComponent2();
        
        Component nonReferComponent = comp1.equals(referenceComponent)? comp2: comp1;
        
        int size =dataTable.getItems().size();
    //    int index = size - 1;
        
        ExperimentalDataBinary toAdd = new ExperimentalDataBinary(size, referenceComponent, nonReferComponent, temperature, pressure, x, y);
        
        data.add(toAdd);
    }
    
    @FXML protected void saveExperimentalData(ActionEvent event){
       // ExperimentalDataBinary[] experimental =(ExperimentalDataBinary[] ) dataTable.getItems().toArray();
        
        ArrayList<ExperimentalDataBinary> experimentalData = new ArrayList<>();
     //   experimentalData.addAll(Arrays.asList(experimental));
        for(Object datad : dataTable.getItems()){
            ExperimentalDataBinary adata = (ExperimentalDataBinary)datad;
            experimentalData.add(adata);
        }
        
        
        UserProperties.setExperimentalDataArrayList(experimentalData);
        
    }  
}
