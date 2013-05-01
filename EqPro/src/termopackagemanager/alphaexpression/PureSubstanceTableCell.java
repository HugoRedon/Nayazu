package termopackagemanager.alphaexpression;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import termo.eos.alpha.Alpha;
import termo.substance.PureSubstance;

/**
 *
 * @author Hugo Redon Rivera
 */

  public class PureSubstanceTableCell extends TableCell <PureSubstance, Alpha>{
      ComboBox comboBox;

            public PureSubstanceTableCell( ObservableList<Alpha> choiceList){
                comboBox = new ComboBox();
                comboBox.setItems(choiceList);
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

    }
