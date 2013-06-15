package termopackagemanager.alphaexpression;

import eqpro.EqPro;
import eqpro.UserProperties;
import eqpro.numerictextfieldcontroller.NumericTextFieldController;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import termo.component.Component;
import termo.eos.alpha.Alpha;
import termo.eos.alpha.TwoEquationsAlphaExpression;
import termo.substance.PureSubstance;

/**
 * FXML Controller class
 *
 * @author Hugo Redon Rivera
 */
public class AlphaExpressionManagerController extends NumericTextFieldController implements Initializable {

    private AlphaModelView manager;

    @FXML TableView<PureSubstance> substancesTable;
    @FXML TableColumn<PureSubstance,Component> componentColumn ;
    @FXML TableColumn<PureSubstance,Alpha> alphaColumn;
    @FXML GridPane alphaGrid;
    
    @FXML ImageView alphaImageView;
    @FXML GridPane aboveAlphaGrid;
    
    @FXML protected void back(ActionEvent event)throws Exception{
        eqpro.EqPro.startTermoPackageManager();
    }
    @FXML protected void next(ActionEvent event)throws Exception{
        for(PureSubstance substance : manager.getPureSubstanceList()){
            System.out.println("Componente: "  + substance.getComponent());
            System.out.println("Alpha: " + substance.getAlpha());
        }
            
        if(UserProperties.getComponentListModelView().isMixture()){
            EqPro.startMixingRuleForm();//MIXING_RULE_FORM();
        }else {
            EqPro.startWelcomeForm();
        }   
    }


    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        manager = UserProperties.getAlphaModelView();
        substancesTable.setItems(manager.getPureSubstanceList());
        componentColumn.setCellValueFactory(new PropertyValueFactory<PureSubstance, Component>("component"));
        alphaColumn.setCellValueFactory(new PropertyValueFactory<PureSubstance,Alpha>("alpha"));    
        
        alphaColumn.setCellFactory( new Callback<TableColumn<PureSubstance, Alpha>,TableCell<PureSubstance, Alpha>>() {
                @Override
                public TableCell<PureSubstance, Alpha> call(TableColumn<PureSubstance, Alpha> arg0) {
                          
                    ComboBoxTableCell<PureSubstance,Alpha> tableCell =    new ComboBoxTableCell(AlphaModelView.getExistentAlphas());
                    return tableCell;
                    };
                });
       alphaColumn.setOnEditCommit(new EventHandler<CellEditEvent<PureSubstance, Alpha>>(){

            @Override
            public void handle(CellEditEvent<PureSubstance, Alpha> t) {
                Alpha newAlpha = t.getNewValue();
            t.getRowValue().setAlpha(newAlpha);

            if(newAlpha.getClass().equals(TwoEquationsAlphaExpression.class)){
                TwoEquationsAlphaExpression alpha = (TwoEquationsAlphaExpression)newAlpha;

                showAlphaFieldsInGrid(alpha.getAlphaBelowTc(),alphaGrid);
                showAlphaFieldsInGrid(alpha.getAlphaAboveTc(), aboveAlphaGrid);
            }else {
                alphaGrid.getChildren().clear();
                aboveAlphaGrid.getChildren().clear();
                showAlphaFieldsInGrid(newAlpha, alphaGrid);
            }  
            }
           
       });
        
       
        
        
        //manager.getShownAlpha().addListener(this);
         //   group = new ToggleGroup();
//        try {
//            
//            
////            fillAlpha();
//            group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
//
//                @Override
//                public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
//               
//                
//                }
//

//            
//        } catch (Exception ex) {
//            Logger.getLogger(AlphaExpressionManagerController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }   
//  
//    private void fillAlpha() throws Exception {
//     
//        for(Alpha alpha: AlphaModelView.getExistentAlphas()){
//            
//            ToggleButton toggle = new ToggleButton(alpha.getName());
//            TextField textField  = new TextField();
//            group.getToggles().add(toggle);
//            toggle.setUserData(alpha);
//            int size = grid.getChildren().size();
//            grid.add(toggle, 0, size);
//        }
//    }
        
        
        private void showAlphaFieldsInGrid(Alpha alphaBelowTc, GridPane alphaGrid) {
            Field[] fields = alphaBelowTc.getClass().getDeclaredFields();
                Field.setAccessible(fields, true);
                alphaGrid.getChildren().clear();
                for(Field field: fields){
                try {
                    String name = field.getName();
                    Label nameLabel = new Label(name);
                    TextField textField = new TextField();
                    textField.setId(name);
                    textField.setText(String.valueOf(field.get(alphaBelowTc)));

                    int alphaGridSize = alphaGrid.getChildren().size();
                    boolean isAlpha = false;
                  isAlpha = Alpha.class.isAssignableFrom(field.getType());
                    if(!( isAlpha)){
                        alphaGrid.add(nameLabel, 0, alphaGridSize);
                        alphaGrid.add(textField,1,alphaGridSize);
                    }
                } catch (        IllegalArgumentException | IllegalAccessException ex) {
                    Logger.getLogger(AlphaExpressionManagerController.class.getName()).log(Level.SEVERE, null, ex);
                }

        }}

}
