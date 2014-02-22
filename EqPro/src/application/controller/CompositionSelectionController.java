
package application.controller;

import application.model.CompositionTableItem;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import termo.component.Component;

/**
 * FXML
 * Controller
 * class
 *
 * @author
 * Hugo
 */
public class CompositionSelectionController implements Initializable {

    @FXML private TableView<CompositionTableItem> compositionsTV;
    @FXML private TableColumn molFractionTC;
    @FXML private TableColumn componentNameTC;
    @FXML private TextField totalMolFractionTF;
    
    /**
     * Initializes
     * the
     * controller
     * class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
			
	molFractionTC.setCellFactory(TextFieldTableCell.forTableColumn());
	componentNameTC.setCellValueFactory(new PropertyValueFactory("componentName"));
	molFractionTC.setCellValueFactory(new PropertyValueFactory<CompositionTableItem,String>("molFractionValue"));
	
	compositionsTV.setItems( application.model.Eqfases2Copy.getCompositionItems());
    }    
    
  
    @FXML protected void onEditComposition(TableColumn.CellEditEvent<CompositionTableItem, String> t){
	((CompositionTableItem) t.getTableView().getItems().get(
                t.getTablePosition().getRow())
                ).setMolFractionValue(t.getNewValue());
    }

  
}
