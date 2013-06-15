package termopackagemanager.alphaexpression;

import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import termo.eos.alpha.Alpha;
import termo.substance.PureSubstance;

/**
 *
 * @author Hugo Redon Rivera
 */

  public class PureSubstanceTableCell extends TableCell <PureSubstance, Alpha> {
      ComboBox comboBox;
            public PureSubstanceTableCell( ObservableList<Alpha> choiceList, ChangeListener listener){
                comboBox = new ComboBox();          
                comboBox.setItems(choiceList);
                comboBox.getSelectionModel().selectedItemProperty().addListener(listener);
            }
            @Override
            protected void updateItem(Alpha item, boolean empty) {
                super.updateItem(item, empty);
                
                if (empty) {
                    setText(null);
                 } else {
                    comboBox.getSelectionModel().select(item);
                     setGraphic(comboBox);
                 }
            }

    @Override
    public void commitEdit(Alpha t) {
        super.commitEdit(t); //To change body of generated methods, choose Tools | Templates.
    }
            
    }