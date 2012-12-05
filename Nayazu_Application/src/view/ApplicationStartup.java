package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Hugo Redon Rivera
 */
public class ApplicationStartup extends Application {
    
    private static Stage primaryStage;
    
    
    @Override
    public void start(Stage stage) throws Exception {
        ApplicationStartup.primaryStage = stage;
        
      // stage.initStyle(StageStyle.TRANSPARENT);
       
       Parent root = FXMLLoader.load(getClass().getResource("EOSComparerView.fxml"));
          
       
        Scene scene = new Scene(root,900,600);
        //primaryStage.setFullScreen(true);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
  
    public static void changeScene(Scene scene){
        primaryStage.setScene(scene);
        primaryStage.show();
    }
  
   

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
