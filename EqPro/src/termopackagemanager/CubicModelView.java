package termopackagemanager;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import termo.eos.Cubic;

/**
 *
 * @author Hugo Redon Rivera
 */
public class CubicModelView {

    private SimpleBooleanProperty selectedProperty = new SimpleBooleanProperty();
    private SimpleBooleanProperty editableProperty = new SimpleBooleanProperty();
    
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty u = new SimpleStringProperty();
    private SimpleStringProperty w= new SimpleStringProperty();
    private SimpleStringProperty omega_a= new SimpleStringProperty();
    private SimpleStringProperty omega_b= new SimpleStringProperty();
    
    
    private Cubic selectedCubic;
    

    public void showCubic(Cubic cubic){       
        name.set(cubic.getName());
        u.set(String.valueOf(cubic.getU()));
        w.set(String.valueOf(cubic.getW()));
        omega_a.set(String.valueOf(cubic.getOmega_a()));
        omega_b.set(String.valueOf(cubic.getOmega_b()));
    }
     void showEmpty() {
         name.set("");
        u.set("");
        w.set("");
        omega_a.set("");
        omega_b.set("");
    }



    public SimpleStringProperty getName() {
        return name;
    }

    public void setName(SimpleStringProperty name) {
        this.name = name;
    }

    public SimpleStringProperty getU() {
        return u;
    }

    public void setU(SimpleStringProperty u) {
        this.u = u;
    }

    public SimpleStringProperty getW() {
        return w;
    }

    public void setW(SimpleStringProperty w) {
        this.w = w;
    }

    public SimpleStringProperty getOmega_a() {
        return omega_a;
    }

    public void setOmega_a(SimpleStringProperty omega_a) {
        this.omega_a = omega_a;
    }

    public SimpleStringProperty getOmega_b() {
        return omega_b;
    }

    public void setOmega_b(SimpleStringProperty omega_b) {
        this.omega_b = omega_b;
    }

    public SimpleBooleanProperty getSelectedProperty() {
        return selectedProperty;
    }

    public void setSelectedProperty(SimpleBooleanProperty selectedProperty) {
        this.selectedProperty = selectedProperty;
    }

    public SimpleBooleanProperty getEditableProperty() {
        return editableProperty;
    }

    public void setEditableProperty(SimpleBooleanProperty editableProperty) {
        this.editableProperty = editableProperty;
    }

    public Cubic getSelectedCubic() {
        return selectedCubic;
    }

    public void setSelectedCubic(Cubic selectedCubic) {
        this.selectedCubic = selectedCubic;
        showCubic(selectedCubic);
    }



   

   

    
    
    
}
