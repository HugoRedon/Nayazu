package eqpro;

import componentmanager.component.ComponentModelView;
import componentmanager.components.ComponentsModelView;
import java.util.HashMap;
import termopackagemanager.CubicModelView;
import termopackagemanager.alphaexpression.AlphaModelView;

/**
 *
 * @author Hugo Redon Rivera
 */
public final class UserProperties {
    

    private static HashMap properties = new HashMap();
    
    public static ComponentsModelView getComponentListModelView(){    
        return (ComponentsModelView)getProperty(ComponentsModelView.class);
    }
    public static ComponentModelView getComponentModelView(){
        return (ComponentModelView)getProperty(ComponentModelView.class);
    }
    public static CubicModelView getCubicModelView(){
          return (CubicModelView)getProperty(CubicModelView.class);
    }
    public static AlphaModelView getAlphaModelView() {
         return (AlphaModelView)getProperty(AlphaModelView.class);
    }
    
    
    
    private static Object getProperty( Class clastype){
        String key = clastype.getName();
        
        Object obj = properties.get(key);
        if(obj ==null){
            try{
                
                obj  = clastype.newInstance();
                properties.put(key, obj);
                
            }catch(InstantiationException e){
                System.out.println("Error al intentar instanciar clase :"  + key + " mensaje: " + e.getMessage());
            }catch(IllegalAccessException e){
                System.out.println("No tienes permiso para  instanciar la clase :"  + key + " mensaje: " + e.getMessage());
            }
            
        }
        return properties.get(key);
    }
}
