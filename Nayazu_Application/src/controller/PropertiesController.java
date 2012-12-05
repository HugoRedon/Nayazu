package controller;


import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import termo.component.Component;
import termo.eos.EOSNames;
import termo.eos.mixingRule.MixingRule;
import termo.userControl.FractionsRow;
import termo.userControl.FractionsTableView;
import view.ApplicationStartup;

/**
 * FXML Controller class
 *
 * @author Hugo Redon Rivera
 */
public class PropertiesController implements Initializable {
    @FXML TreeView packageTreeView;
    
    @FXML TitledPane eosTitledPane;
    @FXML TitledPane componentsTitledPane;
    @FXML TitledPane mixingRulesTitledPane;
    
    @FXML ListView listViewOfComponents;
    @FXML ListView mixingRuleListView; 
    
    @FXML FractionsTableView fractionsTableView;
//    @FXML TableColumn componentTableColumn;
//    @FXML TableColumn fractionTableColumn;
//    
    Stage secondaryStage;
    HashMap listOfEos;
    
    ObservableList<FractionsRow>  fractionsRowObservableList;
    
    
     /* Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        fillTreeView();
        fillMixingRulesListView();
        
        
        listViewOfComponents.setItems(UserProperties.getSelectedComponentsObservableList());
        
        componentsTitledPane.visibleProperty().bind(UserProperties.needsComponentes());   
        mixingRulesTitledPane.visibleProperty().bind(UserProperties.needsMixingRule());       
        fractionsTableView.visibleProperty().bind(UserProperties.needsMixingRule());
        
        
        eosTitledPane.setExpanded(true);        
        fractionsTableView.setEditable(true);
       
      // fractionsTableView = new FractionsTableView();
       fractionsRowObservableList = FXCollections.observableArrayList();
        
       addFractionsListener();
       fractionsTableView.setItems(fractionsRowObservableList);      
    }    
    
    
   
    
   
 
    public void addFractionsListener(){
        
       final ObservableList<Component>  componentsObs = UserProperties.getSelectedComponentsObservableList();
       final HashMap<Component,SimpleDoubleProperty>fractionsObservable = UserProperties.getFractionsObservable();
       
        componentsObs.addListener(new ListChangeListener<Component>(){
       
            @Override
            public void onChanged(ListChangeListener.Change<? extends Component> c) {
                
                //should use c.wasAdded() but throws Exception
               fractionsRowObservableList.clear();
               for(Component component: componentsObs){                 
                   
                        SimpleDoubleProperty theFraction = new SimpleDoubleProperty();
                        fractionsRowObservableList.add(new FractionsRow(component,theFraction));   
                        fractionsObservable.put(component, theFraction);
               }
                
            }
            
        });
    }
    
    
    private void fillMixingRulesListView(){
        HashMap<String,MixingRule> listOfMixingRules = UserProperties.getListOfMixingRule();
        MixingRule vdwMR = listOfMixingRules.get("vdwMR");
        
        mixingRuleListView.getItems().add(vdwMR);
    }
    
    
    private void fillTreeView(){
        listOfEos = UserProperties.getListOfEOS();
        
        TreeItem root = new TreeItem("EOS" );        
        TreeItem cubic = new TreeItem("Cubic EOS");      
        TreeItem prEos = new TreeItem(EOSNames.PengRobinson);
        TreeItem rksEos = new TreeItem(EOSNames.RedlichKwongSoave);
        TreeItem tstEos = new TreeItem(EOSNames.TwuSimTassone);
        
        TreeItem idealGasView = new TreeItem(listOfEos.get("ig"),getEOSIcon());
        TreeItem vdwView =  new TreeItem(listOfEos.get("vdw"),getEOSIcon());
        
        TreeItem prView =  new TreeItem(listOfEos.get("pr"),getEOSIcon());
        TreeItem prsvView =  new TreeItem(listOfEos.get("prsv"),getEOSIcon());
       // TreeItem prstView = new TreeItem(listOfEos.get("prt"),getEOSIcon());
        
        TreeItem rksView =  new TreeItem(listOfEos.get("rks"),getEOSIcon());
        TreeItem rksmView =  new TreeItem(listOfEos.get("rksm"),getEOSIcon());
       // TreeItem rkstView = new TreeItem(listOfEos.get("rkst"),getEOSIcon());
        
        TreeItem tstView = new TreeItem(listOfEos.get("tst"),getEOSIcon());
        //TreeItem tstsprView = new TreeItem(listOfEos.get("tstspr"),getEOSIcon());
       // TreeItem tstmsvView = new TreeItem(listOfEos.get("tstmsv"),getEOSIcon());
        
        packageTreeView.setRoot(root);
        root.getChildren().addAll(idealGasView,cubic);        
        cubic.getChildren().addAll(vdwView,tstEos,prEos,rksEos);
        prEos.getChildren().addAll(prView,prsvView);
        rksEos.getChildren().addAll(rksView,rksmView);
        tstEos.getChildren().addAll(tstView);
        
        root.setExpanded(true);
        cubic.setExpanded(true);
        prEos.setExpanded(true);
        rksEos.setExpanded(true);
        tstEos.setExpanded(true);
        
        
        
    }
    
    private ImageView getEOSIcon(){ 
        //should do from css
         Image image = new Image(ApplicationStartup.class.getResourceAsStream("images/eos.png"));
        ImageView icon = new ImageView(image); 
        
        icon.setFitHeight(25);
        icon.setFitWidth(25);
        
        return icon;
    }
    
    @FXML protected void chooseComponent(ActionEvent event ) throws IOException{
       if(secondaryStage == null){
        secondaryStage = new Stage();
        Pane pane = (Pane)FXMLLoader.load(ApplicationStartup.class.getResource("ComponentSelectionView.fxml"));
        Scene scene = new Scene(pane,300,300);
        secondaryStage.setScene(scene);
       }
       
       secondaryStage.show();
       secondaryStage.toFront();
    }
    @FXML protected void removeComponent(ActionEvent event){
        Component toRemove = (Component)listViewOfComponents.getSelectionModel().getSelectedItem();   
        UserProperties.removeComponent(toRemove);
    }
    @FXML protected void selectedPackage(MouseEvent event){        
        int index = packageTreeView.getSelectionModel().getSelectedIndex();
        Object selectedEos  = packageTreeView.getTreeItem(index).getValue();
       // EOS selectedEos = (EOS) packageTreeView.getSelectionModel().getSelectedIndex();    
        if(listOfEos.containsValue(selectedEos)){
            UserProperties.setSelectedPackage( selectedEos);
        }       
    }
    @FXML protected void selectMixingRule(MouseEvent event){
        MixingRule mr = (MixingRule) mixingRuleListView.getSelectionModel().getSelectedItem();
        UserProperties.setSelectedMixingRule(mr);      
    }
   
    
    
}

