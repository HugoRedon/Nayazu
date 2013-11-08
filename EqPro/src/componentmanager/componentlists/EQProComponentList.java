package componentmanager.componentlists;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import termo.component.Component;

/**
 *
 * @author Hugo Redon Rivera
 */
public class EQProComponentList {
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty filePath = new SimpleStringProperty();
    private ObservableList<Component> components = FXCollections.observableArrayList();

    @Override
    public String toString() {
        return  getName().get() ;
    }

    public SimpleStringProperty getName() {
        return name;
    }
    public void setName(String name){
        this.name.set(name);
    }

    public void setName(SimpleStringProperty name) {
        this.name = name;
    }

    public ObservableList<Component> getComponents() {
        return components;
    }

    public void setComponents(ObservableList<Component> components) {
        this.components = components;
    }

    public SimpleStringProperty getFilePath() {
        return filePath;
    }

    public void setFilePath(SimpleStringProperty filePath) {
        this.filePath = filePath;
    }
    
    
}
