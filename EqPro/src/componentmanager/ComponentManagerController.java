package componentmanager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import termo.component.Component;
import termo.cp.DIPPR_107_Equation;

/**
 * FXML Controller class
 *
 * @author Chilpayate
 */
public class ComponentManagerController implements Initializable {
    @FXML    private TextField criticalTemperature;
   @FXML     private TextField criticalPressure;
    @FXML    private TextField acentricFactor;
    @FXML    private TextField prsvKappa;
    
   @FXML      private TextField L_Twu;
    @FXML    private TextField M_Twu;
    @FXML    private TextField N_Twu;
    
  @FXML      private TextField A_Mathias_Copeman;
  @FXML      private TextField B_Mathias_Copeman;
  @FXML     private TextField C_Mathias_Copeman;
    
 @FXML       private TextField r_UNIQUAC;
  @FXML      private TextField q_UNIQUAC;
    
   @FXML     private TextField name;
  @FXML      private TextField casNumber;
    
   @FXML      private TextField A_dippr1007;
  @FXML      private TextField B_dippr1007;
  @FXML     private TextField C__dippr1007;
   @FXML      private TextField D__dippr1007;
  @FXML      private TextField E__dippr1007;
  
  @FXML private ListView componentsListView;

  @FXML private Label errorLabel;
  
    private ObservableList<Component> components = FXCollections.observableArrayList();
    private SimpleStringProperty componentsFileName = new SimpleStringProperty();
  
//    @FXML
//    private ImageView cpImgView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        componentsListView.setItems(components);
        
        componentsFileName.setValue("");
        componentsListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
                    if(mouseEvent.getClickCount() == 2){
                        doubleClickOnComponent();
                    }
                }
            }
        });
    }    
    @FXML
     protected void createComponent(ActionEvent event){
        Component c = new Component();
        
        try{
            c.setName(name.getText());
            c.setCasNumber(casNumber.getText());
            
            if(name.getText().equals("") || casNumber.getText().equals("")){
                errorLabel.setText( "Ingresa un nombre y numero Cas válido") ;
                return;
            }
//            for(Component component: components){
//                if(casNumber.getText().equals(component.getCasNumber())){
//                    errorLabel.setText("Este componente ya existe");
//                    return;
//                }
//             
//            }

            c.setCriticalTemperature(Double.valueOf(criticalTemperature.getText()));
            c.setCriticalPressure(Double.valueOf(criticalPressure.getText()));
            c.setAcentricFactor(Double.valueOf(acentricFactor.getText()));
            c.setL_Twu(Double.valueOf(L_Twu.getText()));
            c.setM_Twu(Double.valueOf(M_Twu.getText()));
            c.setN_Twu(Double.valueOf(N_Twu.getText()));
            c.setA_Mathias_Copeman(Double.valueOf(A_Mathias_Copeman.getText()));
            c.setB_Mathias_Copeman(Double.valueOf(B_Mathias_Copeman.getText()));
            c.setC_Mathias_Copeman(Double.valueOf(C_Mathias_Copeman.getText()));
            c.setR_UNIQUAC(Double.valueOf(r_UNIQUAC.getText()));
            c.setPrsvKappa(Double.valueOf(prsvKappa.getText()));

            c.setCp(getCpEquation());
        
        
            components.add(c);
        }catch(java.lang.NumberFormatException e){
            errorLabel.setText("Ingresaste letras en un campo numérico");
        }
    }
    
    private DIPPR_107_Equation getCpEquation(){
        DIPPR_107_Equation cp = new DIPPR_107_Equation();
        
        cp.setA(Double.valueOf(A_dippr1007.getText()));
        cp.setB(Double.valueOf(B_dippr1007.getText()));
        cp.setC(Double.valueOf(C__dippr1007.getText()));
        cp.setD(Double.valueOf(D__dippr1007.getText()));
        cp.setE(Double.valueOf(E__dippr1007.getText()));
        
        return cp;
    }
    @FXML
    protected void saveObjects(ActionEvent event)   {
        
          try {
                FileOutputStream fout = new FileOutputStream("c:\\components.eq");
            try (ObjectOutputStream oos = new ObjectOutputStream(fout)) {
                oos.writeObject(components.toArray());
            }
            } catch (Exception ex) {
                Logger.getLogger(ComponentManagerController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
    }
    
    
    @FXML
    protected void loadObjects(ActionEvent event){
               try {
                   components.clear();
                FileInputStream fout = new FileInputStream("c:\\components.eq");
            try (ObjectInputStream oos = new ObjectInputStream(fout)) {
                 
                Object[] arrayComp ;
                arrayComp = (Object[]) oos.readObject();
                for(Object ob : arrayComp){
                    Component c = (Component)ob;
                    components.add(c);
                }
                if(arrayComp.length !=0){
                    showComponent((Component)arrayComp[0]);
                }
            }
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(ComponentManagerController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
 
    protected void doubleClickOnComponent(){
        Component component = (Component)componentsListView.getSelectionModel().getSelectedItem();
        showComponent(component);
        
    }
    protected void showComponent(Component component){
        
        
        name.setText(String.valueOf(component.getName()));
        casNumber.setText(String.valueOf(component.getCasNumber()));
        
        criticalTemperature.setText(String.valueOf(component.getCriticalTemperature()));
        criticalPressure.setText(String.valueOf(component.getCriticalPressure()));
        acentricFactor.setText(String.valueOf(component.getAcentricFactor()));
        prsvKappa.setText(String.valueOf(component.getPrsvKappa()));
        L_Twu.setText(String.valueOf(component.getL_Twu()));
        M_Twu.setText(String.valueOf(component.getM_Twu()));
        N_Twu.setText(String.valueOf(component.getN_Twu()));
        A_Mathias_Copeman.setText(String.valueOf(component.getA_Mathias_Copeman()));
        B_Mathias_Copeman.setText(String.valueOf(component.getB_Mathias_Copeman()));
        C_Mathias_Copeman.setText(String.valueOf(component.getC_Mathias_Copeman()));
        r_UNIQUAC.setText(String.valueOf(component.getR_UNIQUAC()));
        q_UNIQUAC.setText(String.valueOf(component.getQ_UNIQUAC()));
        
        DIPPR_107_Equation cpEquation = (DIPPR_107_Equation)component.getCpIdealGasEquation();
        
        A_dippr1007.setText(String.valueOf(cpEquation.getA()));
        B_dippr1007.setText(String.valueOf(cpEquation.getB()));
        C__dippr1007.setText(String.valueOf(cpEquation.getC()));
        D__dippr1007.setText(String.valueOf(cpEquation.getD()));
        E__dippr1007.setText(String.valueOf(cpEquation.getE()));

    }
    @FXML protected void regresar(ActionEvent event) throws IOException{
        
        Parent root = FXMLLoader.load(getClass().getResource(eqpro.EqPro.WelcomeFXML));
        eqpro.EqPro.loadFxml(root);
        
    }
    

}
