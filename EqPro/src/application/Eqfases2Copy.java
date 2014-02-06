/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.io.IOException;
import java.util.HashMap;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import termo.component.Component;

/**
 *
 * @author
 * Hugo
 */
public class Eqfases2Copy extends Application implements ListChangeListener<Component>{
    private static Stage primaryStage;
    public static  void setInerWindow(Stage stage){
	stage.initOwner(primaryStage);
	
    }

    private static ObservableList<Component> components = FXCollections.observableArrayList();
    private static ObservableList<CompositionTableItem> compositionItems = FXCollections.observableArrayList();
    private static HashMap<Component,CompositionTableItem> compositionMap = new HashMap();
    
    
    
    public static ObservableList<Component> getComponents() {
	return components;
    }
    public static void setComponents(ObservableList<Component> components){
	Eqfases2Copy.components = components;
    }

    public static ObservableList<CompositionTableItem> getCompositionItems() {
	return Eqfases2Copy.compositionItems;
    }
    public static void setCompositionItems(ObservableList<CompositionTableItem> aCompositionItems) {
	Eqfases2Copy.compositionItems = aCompositionItems;
    }
    
      @Override
    public void onChanged(ListChangeListener.Change<? extends Component> change) {
	
	while(change.next()){
	    if(change.wasAdded()){
		System.out.println("Component added");
		for(Component component: change.getAddedSubList()){
		    CompositionTableItem composition = new CompositionTableItem();
		    composition.setMolFractionValue("0.0");
		    composition.setComponentName(component.getName());
		    compositionMap.put(component, composition);
		    compositionItems.add(composition);
		}
	    }if(change.wasRemoved()){
		for(Component component: change.getRemoved()){
		    compositionItems.remove(compositionMap.get(component));
		}
	    }
	    
	
	}
	
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
	
	components.addListener(this);
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

