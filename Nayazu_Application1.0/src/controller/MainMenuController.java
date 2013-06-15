package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import view.ApplicationStartup;

/**
 *
 * @author Hugo Redon Rivera
 */
public class MainMenuController implements Initializable {

  @FXML protected void selectComponent(MouseEvent event) throws IOException{
      Pane pane = (Pane)FXMLLoader.load(ApplicationStartup.class.getResource("EOSComparerView.fxml"));
      ApplicationStartup.changeScene(new Scene(pane));
      
  }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
       @FXML protected void quit(MouseEvent event){
         System.exit(0);
     }
    
}
