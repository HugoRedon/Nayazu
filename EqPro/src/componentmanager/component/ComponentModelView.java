package componentmanager.component;

import componentmanager.components.ComponentsModelView;
import eqpro.UserProperties;
import javafx.beans.property.SimpleStringProperty;
import termo.component.Component;
import termo.cp.DIPPR_107_Equation;

/**
 *
 * @author Hugo Redon Rivera
 */
public class ComponentModelView {
    
    private SimpleStringProperty criticalTemperature = new SimpleStringProperty("0");
    private SimpleStringProperty criticalPressure = new SimpleStringProperty("0");
    private SimpleStringProperty acentricFactor = new SimpleStringProperty("0");
    private SimpleStringProperty prsvKappa = new SimpleStringProperty("0");

    private SimpleStringProperty L_Twu = new SimpleStringProperty("0");
    private SimpleStringProperty M_Twu = new SimpleStringProperty("0");
    private SimpleStringProperty N_Twu = new SimpleStringProperty("0");

    private SimpleStringProperty A_Mathias_Copeman = new SimpleStringProperty("0");
    private SimpleStringProperty B_Mathias_Copeman = new SimpleStringProperty("0");
    private SimpleStringProperty C_Mathias_Copeman = new SimpleStringProperty("0");

    private SimpleStringProperty r_UNIQUAC = new SimpleStringProperty("0");
    private SimpleStringProperty q_UNIQUAC = new SimpleStringProperty("0");

    private SimpleStringProperty name = new SimpleStringProperty("Nombre del componente");
    private SimpleStringProperty casNumber = new SimpleStringProperty("Número CAS");

    private SimpleStringProperty A_dippr1007 = new SimpleStringProperty("0");
    private SimpleStringProperty B_dippr1007 = new SimpleStringProperty("0");
    private SimpleStringProperty C_dippr1007 = new SimpleStringProperty("0");
    private SimpleStringProperty D_dippr1007 = new SimpleStringProperty("0");
    private SimpleStringProperty E_dippr1007 = new SimpleStringProperty("0");
  
    private SimpleStringProperty enthalpyofFormationofIdealgasat298_15Kand101325Pa = new SimpleStringProperty("0");
    private SimpleStringProperty absoluteEntropyofIdealGasat298_15Kand101325Pa = new SimpleStringProperty("0");
    
    private  boolean componentBeingModified;
    private Component componentToModify;
  
public void showComponent( Component componentToShow){
       
        name.set(componentToShow.getName());
        casNumber.set(componentToShow.getCasNumber());
        
        criticalTemperature.set(String.valueOf(componentToShow.getCriticalTemperature()));
        criticalPressure.set(String.valueOf(componentToShow.getCriticalPressure()));
        acentricFactor.set(String.valueOf(componentToShow.getAcentricFactor()));
        prsvKappa.set(String.valueOf(componentToShow.getPrsvKappa()));
        L_Twu.set(String.valueOf(componentToShow.getL_Twu()));
        M_Twu.set(String.valueOf(componentToShow.getM_Twu()));
        N_Twu.set(String.valueOf(componentToShow.getN_Twu()));
        A_Mathias_Copeman.set(String.valueOf(componentToShow.getA_Mathias_Copeman()));
        B_Mathias_Copeman.set(String.valueOf(componentToShow.getB_Mathias_Copeman()));
        C_Mathias_Copeman.set(String.valueOf(componentToShow.getC_Mathias_Copeman()));
        r_UNIQUAC.set(String.valueOf(componentToShow.getR_UNIQUAC()));
        q_UNIQUAC.set(String.valueOf(componentToShow.getQ_UNIQUAC()));
        
        DIPPR_107_Equation cpEquation = (DIPPR_107_Equation)componentToShow.getCp();
        
        A_dippr1007.set(String.valueOf(cpEquation.getA()));
        B_dippr1007.set(String.valueOf(cpEquation.getB()));
        C_dippr1007.set(String.valueOf(cpEquation.getC()));
        D_dippr1007.set(String.valueOf(cpEquation.getD()));
        E_dippr1007.set(String.valueOf(cpEquation.getE()));
        
        enthalpyofFormationofIdealgasat298_15Kand101325Pa.set(String.valueOf(componentToShow.getEnthalpyofFormationofIdealgasat298_15Kand101325Pa()));
        absoluteEntropyofIdealGasat298_15Kand101325Pa.set(String.valueOf(componentToShow.getAbsoluteEntropyofIdealGasat298_15Kand101325Pa()));

}
    
protected boolean ok()throws Exception{
         ComponentsModelView listManager = UserProperties.getComponentListModelView();
        Component component= createNewComponent();
        
        if(isComponentBeingModified()){
            listManager.saveChanges(component, getComponentToModify());
            componentBeingModified = false;
             eqpro.EqPro.startComponentListManager();
        }else{
              boolean success =  listManager.addComponentToList(component);
              if(success){
                    eqpro.EqPro.startComponentListManager();
                }else{
                  return false;
                }
        }
        return true;
}

    protected Component createNewComponent(){
        Component c = new Component();
        
        c.setName(name.get());
        c.setCasNumber(casNumber.get());

        c.setCriticalTemperature(Double.valueOf(criticalTemperature.get()));
        c.setCriticalPressure(Double.valueOf(criticalPressure.get()));
        c.setAcentricFactor(Double.valueOf(acentricFactor.get()));
        c.setL_Twu(Double.valueOf(L_Twu.get()));
        c.setM_Twu(Double.valueOf(M_Twu.get()));
        c.setN_Twu(Double.valueOf(N_Twu.get()));
        c.setA_Mathias_Copeman(Double.valueOf(A_Mathias_Copeman.get()));
        c.setB_Mathias_Copeman(Double.valueOf(B_Mathias_Copeman.get()));
        c.setC_Mathias_Copeman(Double.valueOf(C_Mathias_Copeman.get()));
        c.setR_UNIQUAC(Double.valueOf(r_UNIQUAC.get()));
        c.setPrsvKappa(Double.valueOf(prsvKappa.get()));
        
        c.setEnthalpyofFormationofIdealgasat298_15Kand101325Pa(Double.valueOf(enthalpyofFormationofIdealgasat298_15Kand101325Pa.get()));
        c.setAbsoluteEntropyofIdealGasat298_15Kand101325Pa(Double.valueOf(absoluteEntropyofIdealGasat298_15Kand101325Pa.get()));

        c.setCp(getCpEquation());
        return c;
    }
    private DIPPR_107_Equation getCpEquation(){
        DIPPR_107_Equation cp = new DIPPR_107_Equation();
        
        cp.setA(Double.valueOf(A_dippr1007.get()));
        cp.setB(Double.valueOf(B_dippr1007.get()));
        cp.setC(Double.valueOf(C_dippr1007.get()));
        cp.setD(Double.valueOf(D_dippr1007.get()));
        cp.setE(Double.valueOf(E_dippr1007.get()));
        
        return cp;
    }

    public Component getComponentToModify() {
        return componentToModify;
    }
    
    public void setComponentToModify(Component componentToModify) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException {
        this.componentToModify = componentToModify;
       showComponent(componentToModify);
    }

    public boolean isComponentBeingModified() {
        return componentBeingModified;
    }

    public void setComponentBeingModified(boolean componentBeingModified) {
        this.componentBeingModified = componentBeingModified;
    }
    
    
    
    
}
