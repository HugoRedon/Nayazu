package termo.eos;

/**
 *
 * @author Hugo Redon Rivera
 */
public abstract class EOS {
  
   private String name;
 
   
    /**
     * 
     * @return The name of the equation of state as String
     */
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    @Override
    public String toString(){
        return this.name;
    }
    
    /**
     * 
     * @return The representation of the Equation of State as String
     */
    public abstract String getEquation();
    

    /**
     * 
     * @return If components are needed for calculations as boolean
     */
//    public abstract boolean needsComponents();
//   
//    public abstract boolean isCubic();



}
