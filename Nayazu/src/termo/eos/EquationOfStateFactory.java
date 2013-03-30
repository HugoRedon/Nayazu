package termo.eos;

/**
 *
 * @author Hugo Redon Rivera
 */
public class EquationOfStateFactory {

    private static Cubic pengRobinsonBase(){
        Cubic pengRobinson = new Cubic();
        pengRobinson.set_u(2);
        pengRobinson.set_w(-1);
         pengRobinson.setOmega_a(0.45723553);
        pengRobinson.setOmega_b(0.077796074);
        return pengRobinson;
    }
    
    
    
//    public static Cubic pengRobinson(){
//        Cubic pengRobinson = pengRobinsonBase();
//        pengRobinson.setName(EOSNames.PengRobinson);
//        
////        Alpha_R alpha = new Soave_PengRobinsonAlpha();
////        alpha.setR(PR_RKS_Constants.r_PengRobinson);
//        
//        pengRobinson.setAlpha(AlphaFactory.getPengAndRobinsonExpression());
//        return pengRobinson;
//    }
        
     
    
//    public static CubicAlpha pengRobinsonStryjekVera(){
//        CubicAlpha prsv = pengRobinsonBase();
//        prsv.setName(EOSNames.PengRobinsonStryjekVera);
//        
////        Alpha_R alpha = new Mathias_StryjekVera();
////        alpha.setR(PR_RKS_Constants.r_PengRobinson);
//        
//         prsv.setAlpha(AlphaFactory.getStryjekAndVeraExpression());
//        return prsv;
//    }
    
    
    public static IdealGas idealGas(){
        return new IdealGas();
    }
    
    private static Cubic redlichKwongSoaveBase(){
        Cubic rks = new Cubic();
        rks.set_u(1);
        rks.set_w(0);
        
        rks.setOmega_a(0.42748023);
        rks.setOmega_b(0.08664035);
        return rks;
    }
//    public static CubicAlpha redlichKwongSoave(){
//        CubicAlpha rks = redlichKwongSoaveBase();
//        rks.setName(EOSNames.RedlichKwongSoave);
//        
////        Alpha_R alpha = new Soave_PengRobinsonAlpha();
////        alpha.setR(PR_RKS_Constants.r_RedlichKwongSoave);
//        
//        rks.setAlpha(AlphaFactory.getSoaveExpression());
//        return rks;
//    }
//    public static CubicAlpha redlichKwongSoaveMathias(){
//        CubicAlpha rksm = redlichKwongSoaveBase();
//        rksm.setName(EOSNames.RedlichKwongSoaveMathias);
//        
////          Alpha_R alpha = new Mathias_StryjekVera();
////        alpha.setR(PR_RKS_Constants.r_RedlichKwongSoave);
//        
//        rksm.setAlpha(AlphaFactory.getMathiasExpression());
//        return rksm;
//    }
    
    public static Cubic twoSimTassone(){
        Cubic tst = new Cubic();
        
        tst.setName(EOSNames.TwuSimTassone);
//        tst.setAlpha(new TwuEquation());
        
        tst.set_u(2.5d);
        tst.set_w(-1.5d);
        
        tst.setOmega_a(0.470507);
        tst.setOmega_b(0.0740740);
        
        return tst;
    }
    
    public static Cubic vanDerWaals(){
        //return new VanDerWaals();
        Cubic vanDerWaals = new Cubic();
        vanDerWaals.set_u(0);
        vanDerWaals.set_w(0);
        vanDerWaals.setOmega_a(27d/64d);
        vanDerWaals.setOmega_b(1d/8d);
        
        return vanDerWaals;

    }
//    public static Cubic pengRobinson_Twu() throws Exception{
//        Cubic pengRobinson_Twu = pengRobinsonBase();
//        pengRobinson_Twu.setAlpha(new TwuEquation());
//        
//        pengRobinson_Twu.setName(EOSNames.PengRobinson_Twu);
//        return pengRobinson_Twu;
//        
//    }
}
