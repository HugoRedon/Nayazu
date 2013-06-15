package eqpro;

import componentmanager.components.ComponentsController;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Chilpayate
 */
public class EqPro extends Application {
    private static Stage stage ;
    
    public static void minimize(){
        stage.setIconified(true);
        //stage.setFullScreen(false);
    }
    public static void maximize(){
        stage.setFullScreen(true);
    }
    public static void close(){
        stage.close();
    }

    public static void startAlphaExpressionForm()throws IOException {
        startAForm(ALPHA_EXPRESSION_FXML);
    }

    public static void startMixingRuleForm() {
       //To change body of generated methods, choose Tools | Templates.
    }
    public static void startForm(Class controllerClass)throws Exception{
        startAForm(controllerClass.getResource("Welcome.fxml").getPath());
        
        
    }




    @Override
    public void start(Stage stage) throws Exception {
      stage.initStyle(StageStyle.UNDECORATED);
        EqPro.stage = stage;
        
       
      startForm(WelcomeController.class);
        //startWelcomeForm();
        EqPro.stage.show();
        stage.centerOnScreen();
        initialX= stage.getX();
        initialY =stage.getY();
        
    }

    
    public static void startWelcomeForm() throws IOException{
        startAForm(WelcomeFXML);
    }
    public static void startComponentListManager() throws IOException {
            startAForm( COMPONENT_LIST_MANAGER_FORM);
    }
    public static void startComponentManager( ) throws IOException{
        startAForm( COMPONENT_FORM);
    }
     public static void startTermoPackageManager() throws IOException {
        startAForm(TERMO_PACKAGE_FORM);
    }
     static double  initialX =0;
     static double initialY = 0;
     
    
     
    public static void startAForm( String theFormString)throws IOException{
    
             final  Parent node  = FXMLLoader.load( EqPro.class.getResource(theFormString));
             node.lookup(theFormString);
             
             
            
             node.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent me) {
                    if (me.getButton() != MouseButton.MIDDLE) {
                        initialX = me.getSceneX();
                        initialY = me.getSceneY();
                        
                    }
                }
            });

            node.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent me) {
                    if (me.getButton() != MouseButton.MIDDLE) {
                        node.getScene().getWindow().setX(me.getScreenX() - initialX);
                        node.getScene().getWindow().setY(me.getScreenY() - initialY);
                    }
                }
            });
            
            Scene scene = new Scene(node);
            
            stage.setScene(scene);
          //  stage.centerOnScreen();
    }
   public final static String MIXING_RULE_FORM = "/termopackagemanager/mixingrule/MixingRuleForm.fxml";
    public final static String COMPONENT_FORM = "/componentmanager/component/ComponentForm.fxml";
    public final static String COMPONENT_LIST_MANAGER_FORM = "/componentmanager/componentlist/ComponentListForm.fxml";
    
    public final static String TERMO_PACKAGE_FORM = "/termopackagemanager/TermoPackageManager.fxml";
    public final static String WelcomeFXML = "/eqpro/Welcome.fxml";
    public final static String ALPHA_EXPRESSION_FXML= "/termopackagemanager/alphaexpression/AlphaExpressionManager.fxml";
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

