package eqpro;

import com.sun.org.apache.xpath.internal.compiler.Keywords;
import componentmanager.component.ComponentModelView;
import componentmanager.componentlist.ComponentListModelView;
import java.util.HashMap;
import termopackagemanager.CubicModelView;

/**
 *
 * @author Hugo Redon Rivera
 */
public final class UserProperties {
    

    private static HashMap properties = new HashMap();
    
    public final static String COMPONENT_MODEL_VIEW = "ComponentModelView";
    public final static String COMPONENT_LIST_MODEL_VIEW = "ComponentListModelView";
    public final static String CUBIC_MODEL_VIEW = "CubicModelView";
    
    public static ComponentListModelView getComponentListModelView(){
         ComponentListModelView manager = (ComponentListModelView)properties.get(UserProperties.COMPONENT_LIST_MODEL_VIEW);
        if(manager ==null){
             manager = new ComponentListModelView();
             properties.put(COMPONENT_LIST_MODEL_VIEW, manager);
        }
        return manager;
    }
    public static ComponentModelView getComponentModelView(){
        ComponentModelView manager =  ( ComponentModelView)properties.get(UserProperties.COMPONENT_MODEL_VIEW);
        if(manager == null){
            manager = new ComponentModelView();
            properties.put(COMPONENT_MODEL_VIEW, manager);
        }
        return manager;
    }
    public static CubicModelView getCubicModelView(){
        CubicModelView manager = (CubicModelView)properties.get(UserProperties.CUBIC_MODEL_VIEW);
        if(manager ==null){
            manager = new CubicModelView();
            properties.put(CUBIC_MODEL_VIEW,manager);
        }
        return manager;
    }
    
    
    

 
    



}
