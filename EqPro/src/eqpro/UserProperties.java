package eqpro;

import componentmanager.ComponentManagerControllerModelView;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;

/**
 *
 * @author Hugo Redon Rivera
 */
public class UserProperties {
    private static ComponentManagerControllerModelView componentManager ;
    private static SimpleBooleanProperty componentListAsigned = new SimpleBooleanProperty();

    public static ComponentManagerControllerModelView getComponentManager() {
        return componentManager;
    }

    public static void setComponentManager(ComponentManagerControllerModelView aComponentManager) {
        componentManager = aComponentManager;
    }

    public static SimpleBooleanProperty getComponentListAsigned() {
        return componentListAsigned;
    }

    public static void setComponentListAsigned(SimpleBooleanProperty aComponentListAsigned) {
        componentListAsigned = aComponentListAsigned;
    }


}
