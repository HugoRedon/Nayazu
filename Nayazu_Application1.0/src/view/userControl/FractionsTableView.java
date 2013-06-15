package view.userControl;

import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;

/**
 *
 * @author Hugo Redon Rivera
 */
public class FractionsTableView extends TableView {
    TableColumn componentTableColumn;
    TableColumn fractionTableColumn;
    
    public FractionsTableView(){
        componentTableColumn = new TableColumn("Componente");
        fractionTableColumn = new TableColumn("Fracción mol");
        
        componentTableColumn.setVisible(true);
        fractionTableColumn.setVisible(true);
        
         componentTableColumn.setEditable(false);
        fractionTableColumn.setEditable(true);
        
         componentTableColumn.setCellValueFactory(new PropertyValueFactory<FractionsRow,String>("component"));  
        fractionTableColumn.setCellValueFactory(new PropertyValueFactory<FractionsRow,Double>("fraction"));
            
        Callback<TableColumn, TableCell> cellFactory =
              new Callback<TableColumn, TableCell>() { 
                  @Override
                  public TableCell call(TableColumn p) {
                      return new EditingCell();
                  }
              };
        
        fractionTableColumn.setCellFactory(cellFactory);   
       fractionTableColumn.setOnEditCommit(
            new EventHandler<TableColumn.CellEditEvent<FractionsRow, Double>>() {
                @Override
                 public void handle(TableColumn.CellEditEvent<FractionsRow, Double> t) {
                    FractionsRow onEditFractionRow = (FractionsRow) t.getTableView().getItems().get(t.getTablePosition().getRow());
                        onEditFractionRow.setFraction(t.getNewValue());
                }
            });
       //getColumns().clear();
       getColumns().addAll(componentTableColumn,fractionTableColumn);
       
    }
    
    
    
    public static class EditingCell extends TableCell<FractionsRow, Double> {
        private TextField textField;
 
        public EditingCell() {
        }
        
        @Override public void startEdit() {
            super.startEdit();
 
            if (textField == null) {
                createTextField();
            }
            setText(null);
            setGraphic(textField);
            textField.selectAll();
        }
        
        @Override public void cancelEdit() {
            super.cancelEdit();
            setText(String.valueOf(getItem())) ;
            setGraphic(null);
        }
        
        @Override public void updateItem(Double item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }
     
        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            
          
            
            textField.setOnKeyReleased(new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent event) {
                   if (event.getCode() == KeyCode.ENTER) {
                        commitEdit( Double.valueOf(textField.getText()));
                    } else if (event.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                }
            });
            
        }
 
        private String getString() {
            return getItem() == null ? "0" : getItem().toString();
        }
    } 
    
}
