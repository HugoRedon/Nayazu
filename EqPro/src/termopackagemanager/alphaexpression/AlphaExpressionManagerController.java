package termopackagemanager.alphaexpression;

import eqpro.EqPro;
import eqpro.UserProperties;
import eqpro.numerictextfieldcontroller.NumericTextFieldController;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import termo.component.Component;
import termo.eos.alpha.Alpha;
import termo.eos.alpha.AlphaFactory;
import termo.eos.alpha.TwoEquationsAlphaExpression;
import termo.substance.PureSubstance;

/**
 * FXML Controller class
 *
 * @author Chilpayate
 */
public class AlphaExpressionManagerController extends NumericTextFieldController implements Initializable {

    
    private AlphaModelView manager;

    @FXML TableView<PureSubstance> substancesTable;
    @FXML GridPane alphaGrid;
    
    @FXML ImageView alphaImageView;
    @FXML GridPane aboveAlphaGrid;
    
    @FXML protected void back(ActionEvent event)throws Exception{
        eqpro.EqPro.startTermoPackageManager();
    }
    @FXML protected void next(ActionEvent event)throws Exception{
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
        ObservableList<PureSubstance> substances = manager.getPureSubstanceList();
        for(PureSubstance substance : substances){
            Alpha alpha  = substance.getAlpha();
            alpha = (alpha == null)? AlphaFactory.getPengAndRobinsonExpression():alpha;
            substance.setAlpha(alpha);
            
        }
        substancesTable.setItems(manager.getPureSubstanceList());
        TableColumn<PureSubstance,String> componentColumn = new TableColumn<>("Componente");
        componentColumn.setCellValueFactory(new PropertyValueFactory<PureSubstance, String>("component"));
        
        TableColumn alphaColumn = new TableColumn<>("Alfa");
        alphaColumn.setCellValueFactory(new PropertyValueFactory<PureSubstance,String>("alpha"));
    
        
        alphaColumn.setCellFactory( new Callback<TableColumn<PureSubstance, Alpha>, TableCell<PureSubstance, Alpha>>() {
                @Override
                public TableCell<PureSubstance, Alpha> call(TableColumn<PureSubstance, Alpha> arg0) {
                    return new PureSubstanceTableCell(AlphaModelView.getExistentAlphas());
                }
            });
         substancesTable.getColumns().addAll(componentColumn,alphaColumn);
         //   group = new ToggleGroup();
//        try {
//            
//            
////            fillAlpha();
//            group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
//
//                @Override
//                public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
//                    try{
//                        if(t1 == null){
//                            //do nothing
//                        }else if(t1.getUserData() != null){
//
//                                if(t1.getUserData().getClass().equals(TwoEquationsAlphaExpression.class)){
//                                    TwoEquationsAlphaExpression alpha = (TwoEquationsAlphaExpression)t1.getUserData();
//
//                                    showAlphaFieldsInGrid(alpha.getAlphaBelowTc(),alphaGrid);
//                                    showAlphaFieldsInGrid(alpha.getAlphaAboveTc(), aboveAlphaGrid);
//                                }else {
//                                    alphaGrid.getChildren().clear();
//                                    aboveAlphaGrid.getChildren().clear();
//                                    showAlphaFieldsInGrid((Alpha)t1.getUserData(), alphaGrid);
//                                }  
//                        }
//                    }catch(Exception e){
//                        System.out.println("error" + e.getMessage() + " en :" + e.getStackTrace() );
//                    }
//                
//                }
//
//                private void showAlphaFieldsInGrid(Alpha alphaBelowTc, GridPane alphaGrid) throws Exception{
//                    Field[] fields = alphaBelowTc.getClass().getDeclaredFields();
//                        Field.setAccessible(fields, true);
//                        alphaGrid.getChildren().clear();
//                        for(Field field: fields){
//                            String name = field.getName();
//                            Label nameLabel = new Label(name);
//                            TextField textField = new TextField();
//                            textField.setId(name);
//                            textField.setText(String.valueOf(field.get(alphaBelowTc)));
//                            
//                            int alphaGridSize = alphaGrid.getChildren().size();
//                            boolean isAlpha = false;
//                          isAlpha = Alpha.class.isAssignableFrom(field.getType());
//                            if(!( isAlpha)){
//                                alphaGrid.add(nameLabel, 0, alphaGridSize);
//                                alphaGrid.add(textField,1,alphaGridSize);
//                            }
//                            
//                }}
//            });
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
        
        

  
}
