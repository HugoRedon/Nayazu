
package eqpro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Chilpayate
 */
public class EqPro extends Application {
    private static Stage stage ;


    
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(EqPro.WelcomeFXML));
        
        EqPro.stage = stage;
        loadFxml(root);
        EqPro.stage.show();
    }
    public static void  loadFxml(Parent parent){
       Scene scene = new Scene(parent);
        stage.setScene(scene);
    }

    
    public final static String componentManagerFXML = "/componentmanager/ComponentManager.fxml";
    public final static String termoPackageFXML = "/termopackagemanager/TermoPackageManager.fxml";
    public final static String WelcomeFXML = "/eqpro/Welcome.fxml";
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

