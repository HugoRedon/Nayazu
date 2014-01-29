/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author
 * Hugo
 */
public class Eqfases2Copy extends Application {
    private static Stage primaryStage;
    public static  void setInerWindow(Stage stage){
	stage.initOwner(primaryStage);
	
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
	Eqfases2Copy.primaryStage = primaryStage;
	Parent p = FXMLLoader.load(getClass().getResource("eqfases2.fxml"));
	Scene scene = new Scene(p);
	primaryStage.setScene(scene);
	primaryStage.show();
    }

    /**
     * The
     * main()
     * method
     * is
     * ignored
     * in
     * correctly
     * deployed
     * JavaFX
     * application.
     * main()
     * serves
     * only
     * as
     * fallback
     * in
     * case
     * the
     * application
     * can
     * not
     * be
     * launched
     * through
     * deployment
     * artifacts,
     * e.g.,
     * in
     * IDEs
     * with
     * limited
     * FX
     * support.
     * NetBeans
     * ignores
     * main().
     *
     * @param
     * args
     * the
     * command
     * line
     * arguments
     */
    public static void main(String[] args) {
	launch(args);
    }
}
