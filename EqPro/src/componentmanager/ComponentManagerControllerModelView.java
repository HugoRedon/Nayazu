package componentmanager;

import eqpro.UserProperties;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import termo.component.Component;
import termo.cp.DIPPR_107_Equation;

/**
 *
 * @author Hugo Redon Rivera
 */
public class ComponentManagerControllerModelView {
    private ObservableList<Component> components =FXCollections.observableArrayList();
   
    private ReadOnlyObjectProperty<Component> selectedComponentProperty;
    
    private SimpleStringProperty fileName = new SimpleStringProperty();
    
        private SimpleStringProperty criticalTemperatureProperty= new SimpleStringProperty();
        private SimpleStringProperty criticalPressureProperty= new SimpleStringProperty();
        private SimpleStringProperty acentricFactorProperty= new SimpleStringProperty();
        private SimpleStringProperty prsvKappaProperty= new SimpleStringProperty();
    
         private SimpleStringProperty L_TwuProperty= new SimpleStringProperty();
        private SimpleStringProperty M_TwuProperty= new SimpleStringProperty();
        private SimpleStringProperty N_TwuProperty= new SimpleStringProperty();
    
        private SimpleStringProperty A_Mathias_CopemanProperty= new SimpleStringProperty();
        private SimpleStringProperty B_Mathias_CopemanProperty= new SimpleStringProperty();
       private SimpleStringProperty C_Mathias_CopemanProperty= new SimpleStringProperty();
    
        private SimpleStringProperty r_UNIQUACProperty= new SimpleStringProperty();
        private SimpleStringProperty q_UNIQUACProperty= new SimpleStringProperty();
    
        private SimpleStringProperty nameProperty= new SimpleStringProperty();
        private SimpleStringProperty casNumberProperty= new SimpleStringProperty();
    
         private SimpleStringProperty A_dippr1007Property= new SimpleStringProperty();
        private SimpleStringProperty B_dippr1007Property= new SimpleStringProperty();
       private SimpleStringProperty C_dippr1007Property= new SimpleStringProperty();
         private SimpleStringProperty D_dippr1007Property= new SimpleStringProperty();
        private SimpleStringProperty E_dippr1007Property= new SimpleStringProperty();
        
        private SimpleStringProperty enthalpyIGProperty= new SimpleStringProperty();
        private SimpleStringProperty entropyIGProperty= new SimpleStringProperty();
    
        public ComponentManagerControllerModelView(){
            
        }
        
        
        protected void saveObjects( )   {
        
          try {
              String file = getSaveFileName() + ".eqProComp";
               FileOutputStream fout = new FileOutputStream(file);
              BufferedOutputStream buf = new BufferedOutputStream(fout);
                try (XMLEncoder encoder = new XMLEncoder(buf)) {
                    encoder.writeObject(components.toArray());
                } 
            
            } catch (Exception ex) {
                Logger.getLogger(ComponentManagerController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
    }
        
        protected String  getSaveFileName(){
                return  getChooser().showSaveDialog(null).getPath();
        }
        
        protected FileChooser getChooser(){
                FileChooser chooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivo de componentes  EqPro (*.eqProComp)","*.eqProComp");
                File initialDirectory = new File("C:\\");
                chooser.setInitialDirectory(initialDirectory);
                chooser.getExtensionFilters().add(extFilter);
                return chooser;
        }
        
        protected String  getLoadFileName(){
                return  getChooser().showOpenDialog(null).getPath();
        }
        
         protected void loadObjects( ){
               try {
                   components.clear();
                FileInputStream fin = new FileInputStream(getLoadFileName());
                BufferedInputStream buff = new BufferedInputStream(fin);
                        
                XMLDecoder decoder = new XMLDecoder(buff);


                Object[] arrayComp ;
                arrayComp = (Object[]) decoder.readObject();
                for(Object ob : arrayComp){
                    Component c = (Component)ob;
                    components.add(c);
                
              decoder.close();
            }
            } catch (Exception ex) {
                Logger.getLogger(ComponentManagerController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
        
        protected void addNewComponent(){
            getComponents().add(createNewComponent());
            SimpleBooleanProperty bo = new SimpleBooleanProperty();
            bo.setValue(Boolean.TRUE);
            UserProperties.setComponentListAsigned(bo);
        }
        
    protected Component createNewComponent(){
        Component c = new Component();

        
       
            c.setName(nameProperty.get());
            c.setCasNumber(casNumberProperty.get());
 
            c.setCriticalTemperature(Double.valueOf(criticalTemperatureProperty.get()));
            c.setCriticalPressure(Double.valueOf(criticalPressureProperty.get()));
            c.setAcentricFactor(Double.valueOf(acentricFactorProperty.get()));
            c.setL_Twu(Double.valueOf(L_TwuProperty.get()));
            c.setM_Twu(Double.valueOf(M_TwuProperty.get()));
            c.setN_Twu(Double.valueOf(N_TwuProperty.get()));
            c.setA_Mathias_Copeman(Double.valueOf(A_Mathias_CopemanProperty.get()));
            c.setB_Mathias_Copeman(Double.valueOf(B_Mathias_CopemanProperty.get()));
            c.setC_Mathias_Copeman(Double.valueOf(C_Mathias_CopemanProperty.get()));
            c.setR_UNIQUAC(Double.valueOf(r_UNIQUACProperty.get()));
            c.setPrsvKappa(Double.valueOf(prsvKappaProperty.get()));

            c.setCp(getCpEquation());
        
        
            return c;
            
      
    }
    private DIPPR_107_Equation getCpEquation(){
        DIPPR_107_Equation cp = new DIPPR_107_Equation();
        
        cp.setA(Double.valueOf(A_dippr1007Property.get()));
        cp.setB(Double.valueOf(B_dippr1007Property.get()));
        cp.setC(Double.valueOf(C_dippr1007Property.get()));
        cp.setD(Double.valueOf(D_dippr1007Property.get()));
        cp.setE(Double.valueOf(E_dippr1007Property.get()));
        
        return cp;
    }
        
        
        
     protected void showSelectedComponent( ){
        Component component = getSelectedComponentProperty().getValue();
        if(component ==null){
            return;
        }
        nameProperty.set(component.getName());
        casNumberProperty.set(component.getCasNumber());
        
        criticalTemperatureProperty.set(String.valueOf(component.getCriticalTemperature()));
        criticalPressureProperty.set(String.valueOf(component.getCriticalPressure()));
        acentricFactorProperty.set(String.valueOf(component.getAcentricFactor()));
        prsvKappaProperty.set(String.valueOf(component.getPrsvKappa()));
        L_TwuProperty.set(String.valueOf(component.getL_Twu()));
        M_TwuProperty.set(String.valueOf(component.getM_Twu()));
        N_TwuProperty.set(String.valueOf(component.getN_Twu()));
        A_Mathias_CopemanProperty.set(String.valueOf(component.getA_Mathias_Copeman()));
        B_Mathias_CopemanProperty.set(String.valueOf(component.getB_Mathias_Copeman()));
        C_Mathias_CopemanProperty.set(String.valueOf(component.getC_Mathias_Copeman()));
        r_UNIQUACProperty.set(String.valueOf(component.getR_UNIQUAC()));
        q_UNIQUACProperty.set(String.valueOf(component.getQ_UNIQUAC()));
        
        DIPPR_107_Equation cpEquation = (DIPPR_107_Equation)component.getCp();
        
        getA_dippr1007Property().set(String.valueOf(cpEquation.getA()));
        getB_dippr1007Property().set(String.valueOf(cpEquation.getB()));
        getC_dippr1007Property().set(String.valueOf(cpEquation.getC()));
        getD_dippr1007Property().set(String.valueOf(cpEquation.getD()));
        getE_dippr1007Property().set(String.valueOf(cpEquation.getE()));

    }
     

    public SimpleStringProperty getCriticalTemperatureProperty() {
        return criticalTemperatureProperty;
    }

    public void setCriticalTemperatureProperty(SimpleStringProperty criticalTemperatureProperty) {
        this.criticalTemperatureProperty = criticalTemperatureProperty;
    }

    public SimpleStringProperty getCriticalPressureProperty() {
        return criticalPressureProperty;
    }

    public void setCriticalPressureProperty(SimpleStringProperty criticalPressureProperty) {
        this.criticalPressureProperty = criticalPressureProperty;
    }

    public SimpleStringProperty getAcentricFactorProperty() {
        return acentricFactorProperty;
    }

    public void setAcentricFactorProperty(SimpleStringProperty acentricFactorProperty) {
        this.acentricFactorProperty = acentricFactorProperty;
    }

    public SimpleStringProperty getPrsvKappaProperty() {
        return prsvKappaProperty;
    }

    public void setPrsvKappaProperty(SimpleStringProperty prsvKappaProperty) {
        this.prsvKappaProperty = prsvKappaProperty;
    }

    public SimpleStringProperty getL_TwuProperty() {
        return L_TwuProperty;
    }

    public void setL_TwuProperty(SimpleStringProperty L_TwuProperty) {
        this.L_TwuProperty = L_TwuProperty;
    }

    public SimpleStringProperty getM_TwuProperty() {
        return M_TwuProperty;
    }

    public void setM_TwuProperty(SimpleStringProperty M_TwuProperty) {
        this.M_TwuProperty = M_TwuProperty;
    }

    public SimpleStringProperty getN_TwuProperty() {
        return N_TwuProperty;
    }

    public void setN_TwuProperty(SimpleStringProperty N_TwuProperty) {
        this.N_TwuProperty = N_TwuProperty;
    }

    public SimpleStringProperty getA_Mathias_CopemanProperty() {
        return A_Mathias_CopemanProperty;
    }

    public void setA_Mathias_CopemanProperty(SimpleStringProperty A_Mathias_CopemanProperty) {
        this.A_Mathias_CopemanProperty = A_Mathias_CopemanProperty;
    }

    public SimpleStringProperty getB_Mathias_CopemanProperty() {
        return B_Mathias_CopemanProperty;
    }

    public void setB_Mathias_CopemanProperty(SimpleStringProperty B_Mathias_CopemanProperty) {
        this.B_Mathias_CopemanProperty = B_Mathias_CopemanProperty;
    }

    public SimpleStringProperty getC_Mathias_CopemanProperty() {
        return C_Mathias_CopemanProperty;
    }

    public void setC_Mathias_CopemanProperty(SimpleStringProperty C_Mathias_CopemanProperty) {
        this.C_Mathias_CopemanProperty = C_Mathias_CopemanProperty;
    }

    public SimpleStringProperty getR_UNIQUACProperty() {
        return r_UNIQUACProperty;
    }

    public void setR_UNIQUACProperty(SimpleStringProperty r_UNIQUACProperty) {
        this.r_UNIQUACProperty = r_UNIQUACProperty;
    }

    public SimpleStringProperty getQ_UNIQUACProperty() {
        return q_UNIQUACProperty;
    }

    public void setQ_UNIQUACProperty(SimpleStringProperty q_UNIQUACProperty) {
        this.q_UNIQUACProperty = q_UNIQUACProperty;
    }

    public SimpleStringProperty getNameProperty() {
        return nameProperty;
    }

    public void setNameProperty(SimpleStringProperty nameProperty) {
        this.nameProperty = nameProperty;
    }

    public SimpleStringProperty getCasNumberProperty() {
        return casNumberProperty;
    }

    public void setCasNumberProperty(SimpleStringProperty casNumberProperty) {
        this.casNumberProperty = casNumberProperty;
    }

    public SimpleStringProperty getA_dippr1007Property() {
        return A_dippr1007Property;
    }

    public void setA_dippr1007Property(SimpleStringProperty A_dippr1007Property) {
        this.A_dippr1007Property = A_dippr1007Property;
    }

    public SimpleStringProperty getB_dippr1007Property() {
        return B_dippr1007Property;
    }

    public void setB_dippr1007Property(SimpleStringProperty B_dippr1007Property) {
        this.B_dippr1007Property = B_dippr1007Property;
    }

    public SimpleStringProperty getC_dippr1007Property() {
        return C_dippr1007Property;
    }

    public void setC_dippr1007Property(SimpleStringProperty C_dippr1007Property) {
        this.C_dippr1007Property = C_dippr1007Property;
    }

    public SimpleStringProperty getD_dippr1007Property() {
        return D_dippr1007Property;
    }

    public void setD_dippr1007Property(SimpleStringProperty D_dippr1007Property) {
        this.D_dippr1007Property = D_dippr1007Property;
    }

    public SimpleStringProperty getE_dippr1007Property() {
        return E_dippr1007Property;
    }

    public void setE_dippr1007Property(SimpleStringProperty E_dippr1007Property) {
        this.E_dippr1007Property = E_dippr1007Property;
    }

    public SimpleStringProperty getEnthalpyIG() {
        return enthalpyIGProperty;
    }

    public void setEnthalpyIG(SimpleStringProperty enthalpyIG) {
        this.enthalpyIGProperty = enthalpyIG;
    }

    public SimpleStringProperty getEntropyIG() {
        return entropyIGProperty;
    }

    public void setEntropyIG(SimpleStringProperty entropyIG) {
        this.entropyIGProperty = entropyIG;
    }

    public ReadOnlyObjectProperty<Component> getSelectedComponentProperty() {
        return selectedComponentProperty;
    }

    public void setSelectedComponentProperty(ReadOnlyObjectProperty<Component> selectedComponentProperty) {
        this.selectedComponentProperty = selectedComponentProperty;
    }

    public ObservableList<Component> getComponents() {
        return components;
    }

    public void setComponents(ObservableList<Component> components) {
        this.components = components;
    }

}
