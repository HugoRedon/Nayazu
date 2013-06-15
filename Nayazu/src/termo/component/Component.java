package termo.component;

import java.io.Serializable;
import java.util.Objects;
import termo.cp.CpEquation;

/**
 *
 * @author Hugo Redon Rivera
 */
public class Component implements Serializable {
    
    private double molecularWeight;
    private double lowerFlammabilityLimitTemperature;
    private double upperFlammabilityLimitTemperature;
    private double criticalCompressibilityFactor;
    private double acentricFactor;
    private double gibbsEnergyofFormationofIdealGasat298_15Kand101325Pa;
    private double vanDerWaalsReducedVolume;
    private double vanderWaalsArea;
    private double gibbsEnergyofFormationinStandardStateat298_15Kand101325Pa;
    private double radiusofGyration;
    private double solubilityParameterat298_15K;
    private double dipoleMoment;
    private double triplePointTemperature;
    private double criticalTemperature;
    private double enthalpyofFormationofIdealgasat298_15Kand101325Pa;
    private double absoluteEntropyofIdealGasat298_15Kand101325Pa;
    private double netEnthalpyofCombustionStandardState298_15K;
    private double criticalPressure;    
    private double normalBoilingPoint_1atm;
    private double enthalpyofFusionatMeltingPoint;
    private double criticalVolume;
    private double enthalpyorHeatofSublimation;
    private double dielectricConstant;
    private double refractiveIndexat298_15K;
    private double flashPoint;
    private double autoIgnitionTermperature;
    private double enthalpyofFormationinStandardStateat298_15Kand101325Pa;
    private double absoluteEntropyinStandardStateat298_15Kand101325Pa;
    private double meltingPoint_1atm;
    private double triplePointPressure;
    private double upperFlammabilityLimit;
    private double liquidMolarVolumeat298_15K;
    private double lowerFlammabilityLimit;
    
    private double prsvKappa;
    
    private double L_Twu;
    private double M_Twu;
    private double N_Twu;
    
    private double A_Mathias_Copeman;
    private double B_Mathias_Copeman;
    private double C_Mathias_Copeman;
    
    private double r_UNIQUAC;
    private double q_UNIQUAC;
//    private double qq_UNIQUAC;
    
    private CpEquation cp;
    
    private int dipprChemID;
    private String name;
    private String casNumber;
    private String formula;
    private String smiles;
    private String Structure;
    private String family;
    private String subFamily;
    private String standardState;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.molecularWeight) ^ (Double.doubleToLongBits(this.molecularWeight) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.lowerFlammabilityLimitTemperature) ^ (Double.doubleToLongBits(this.lowerFlammabilityLimitTemperature) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.upperFlammabilityLimitTemperature) ^ (Double.doubleToLongBits(this.upperFlammabilityLimitTemperature) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.criticalCompressibilityFactor) ^ (Double.doubleToLongBits(this.criticalCompressibilityFactor) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.acentricFactor) ^ (Double.doubleToLongBits(this.acentricFactor) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.gibbsEnergyofFormationofIdealGasat298_15Kand101325Pa) ^ (Double.doubleToLongBits(this.gibbsEnergyofFormationofIdealGasat298_15Kand101325Pa) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.vanDerWaalsReducedVolume) ^ (Double.doubleToLongBits(this.vanDerWaalsReducedVolume) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.vanderWaalsArea) ^ (Double.doubleToLongBits(this.vanderWaalsArea) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.gibbsEnergyofFormationinStandardStateat298_15Kand101325Pa) ^ (Double.doubleToLongBits(this.gibbsEnergyofFormationinStandardStateat298_15Kand101325Pa) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.radiusofGyration) ^ (Double.doubleToLongBits(this.radiusofGyration) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.solubilityParameterat298_15K) ^ (Double.doubleToLongBits(this.solubilityParameterat298_15K) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.dipoleMoment) ^ (Double.doubleToLongBits(this.dipoleMoment) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.triplePointTemperature) ^ (Double.doubleToLongBits(this.triplePointTemperature) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.criticalTemperature) ^ (Double.doubleToLongBits(this.criticalTemperature) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.enthalpyofFormationofIdealgasat298_15Kand101325Pa) ^ (Double.doubleToLongBits(this.enthalpyofFormationofIdealgasat298_15Kand101325Pa) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.absoluteEntropyofIdealGasat298_15Kand101325Pa) ^ (Double.doubleToLongBits(this.absoluteEntropyofIdealGasat298_15Kand101325Pa) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.netEnthalpyofCombustionStandardState298_15K) ^ (Double.doubleToLongBits(this.netEnthalpyofCombustionStandardState298_15K) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.criticalPressure) ^ (Double.doubleToLongBits(this.criticalPressure) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.normalBoilingPoint_1atm) ^ (Double.doubleToLongBits(this.normalBoilingPoint_1atm) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.enthalpyofFusionatMeltingPoint) ^ (Double.doubleToLongBits(this.enthalpyofFusionatMeltingPoint) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.criticalVolume) ^ (Double.doubleToLongBits(this.criticalVolume) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.enthalpyorHeatofSublimation) ^ (Double.doubleToLongBits(this.enthalpyorHeatofSublimation) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.dielectricConstant) ^ (Double.doubleToLongBits(this.dielectricConstant) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.refractiveIndexat298_15K) ^ (Double.doubleToLongBits(this.refractiveIndexat298_15K) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.flashPoint) ^ (Double.doubleToLongBits(this.flashPoint) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.autoIgnitionTermperature) ^ (Double.doubleToLongBits(this.autoIgnitionTermperature) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.enthalpyofFormationinStandardStateat298_15Kand101325Pa) ^ (Double.doubleToLongBits(this.enthalpyofFormationinStandardStateat298_15Kand101325Pa) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.absoluteEntropyinStandardStateat298_15Kand101325Pa) ^ (Double.doubleToLongBits(this.absoluteEntropyinStandardStateat298_15Kand101325Pa) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.meltingPoint_1atm) ^ (Double.doubleToLongBits(this.meltingPoint_1atm) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.triplePointPressure) ^ (Double.doubleToLongBits(this.triplePointPressure) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.upperFlammabilityLimit) ^ (Double.doubleToLongBits(this.upperFlammabilityLimit) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.liquidMolarVolumeat298_15K) ^ (Double.doubleToLongBits(this.liquidMolarVolumeat298_15K) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.lowerFlammabilityLimit) ^ (Double.doubleToLongBits(this.lowerFlammabilityLimit) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.prsvKappa) ^ (Double.doubleToLongBits(this.prsvKappa) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.L_Twu) ^ (Double.doubleToLongBits(this.L_Twu) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.M_Twu) ^ (Double.doubleToLongBits(this.M_Twu) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.N_Twu) ^ (Double.doubleToLongBits(this.N_Twu) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.A_Mathias_Copeman) ^ (Double.doubleToLongBits(this.A_Mathias_Copeman) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.B_Mathias_Copeman) ^ (Double.doubleToLongBits(this.B_Mathias_Copeman) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.C_Mathias_Copeman) ^ (Double.doubleToLongBits(this.C_Mathias_Copeman) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.r_UNIQUAC) ^ (Double.doubleToLongBits(this.r_UNIQUAC) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.q_UNIQUAC) ^ (Double.doubleToLongBits(this.q_UNIQUAC) >>> 32));
        hash = 97 * hash + Objects.hashCode(this.getCp());
        hash = 97 * hash + this.dipprChemID;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.casNumber);
        hash = 97 * hash + Objects.hashCode(this.formula);
        hash = 97 * hash + Objects.hashCode(this.smiles);
        hash = 97 * hash + Objects.hashCode(this.Structure);
        hash = 97 * hash + Objects.hashCode(this.family);
        hash = 97 * hash + Objects.hashCode(this.subFamily);
        hash = 97 * hash + Objects.hashCode(this.standardState);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Component other = (Component) obj;
        if (Double.doubleToLongBits(this.molecularWeight) != Double.doubleToLongBits(other.molecularWeight)) {
            return false;
        }
        if (Double.doubleToLongBits(this.lowerFlammabilityLimitTemperature) != Double.doubleToLongBits(other.lowerFlammabilityLimitTemperature)) {
            return false;
        }
        if (Double.doubleToLongBits(this.upperFlammabilityLimitTemperature) != Double.doubleToLongBits(other.upperFlammabilityLimitTemperature)) {
            return false;
        }
        if (Double.doubleToLongBits(this.criticalCompressibilityFactor) != Double.doubleToLongBits(other.criticalCompressibilityFactor)) {
            return false;
        }
        if (Double.doubleToLongBits(this.acentricFactor) != Double.doubleToLongBits(other.acentricFactor)) {
            return false;
        }
        if (Double.doubleToLongBits(this.gibbsEnergyofFormationofIdealGasat298_15Kand101325Pa) != Double.doubleToLongBits(other.gibbsEnergyofFormationofIdealGasat298_15Kand101325Pa)) {
            return false;
        }
        if (Double.doubleToLongBits(this.vanDerWaalsReducedVolume) != Double.doubleToLongBits(other.vanDerWaalsReducedVolume)) {
            return false;
        }
        if (Double.doubleToLongBits(this.vanderWaalsArea) != Double.doubleToLongBits(other.vanderWaalsArea)) {
            return false;
        }
        if (Double.doubleToLongBits(this.gibbsEnergyofFormationinStandardStateat298_15Kand101325Pa) != Double.doubleToLongBits(other.gibbsEnergyofFormationinStandardStateat298_15Kand101325Pa)) {
            return false;
        }
        if (Double.doubleToLongBits(this.radiusofGyration) != Double.doubleToLongBits(other.radiusofGyration)) {
            return false;
        }
        if (Double.doubleToLongBits(this.solubilityParameterat298_15K) != Double.doubleToLongBits(other.solubilityParameterat298_15K)) {
            return false;
        }
        if (Double.doubleToLongBits(this.dipoleMoment) != Double.doubleToLongBits(other.dipoleMoment)) {
            return false;
        }
        if (Double.doubleToLongBits(this.triplePointTemperature) != Double.doubleToLongBits(other.triplePointTemperature)) {
            return false;
        }
        if (Double.doubleToLongBits(this.criticalTemperature) != Double.doubleToLongBits(other.criticalTemperature)) {
            return false;
        }
        if (Double.doubleToLongBits(this.enthalpyofFormationofIdealgasat298_15Kand101325Pa) != Double.doubleToLongBits(other.enthalpyofFormationofIdealgasat298_15Kand101325Pa)) {
            return false;
        }
        if (Double.doubleToLongBits(this.absoluteEntropyofIdealGasat298_15Kand101325Pa) != Double.doubleToLongBits(other.absoluteEntropyofIdealGasat298_15Kand101325Pa)) {
            return false;
        }
        if (Double.doubleToLongBits(this.netEnthalpyofCombustionStandardState298_15K) != Double.doubleToLongBits(other.netEnthalpyofCombustionStandardState298_15K)) {
            return false;
        }
        if (Double.doubleToLongBits(this.criticalPressure) != Double.doubleToLongBits(other.criticalPressure)) {
            return false;
        }
        if (Double.doubleToLongBits(this.normalBoilingPoint_1atm) != Double.doubleToLongBits(other.normalBoilingPoint_1atm)) {
            return false;
        }
        if (Double.doubleToLongBits(this.enthalpyofFusionatMeltingPoint) != Double.doubleToLongBits(other.enthalpyofFusionatMeltingPoint)) {
            return false;
        }
        if (Double.doubleToLongBits(this.criticalVolume) != Double.doubleToLongBits(other.criticalVolume)) {
            return false;
        }
        if (Double.doubleToLongBits(this.enthalpyorHeatofSublimation) != Double.doubleToLongBits(other.enthalpyorHeatofSublimation)) {
            return false;
        }
        if (Double.doubleToLongBits(this.dielectricConstant) != Double.doubleToLongBits(other.dielectricConstant)) {
            return false;
        }
        if (Double.doubleToLongBits(this.refractiveIndexat298_15K) != Double.doubleToLongBits(other.refractiveIndexat298_15K)) {
            return false;
        }
        if (Double.doubleToLongBits(this.flashPoint) != Double.doubleToLongBits(other.flashPoint)) {
            return false;
        }
        if (Double.doubleToLongBits(this.autoIgnitionTermperature) != Double.doubleToLongBits(other.autoIgnitionTermperature)) {
            return false;
        }
        if (Double.doubleToLongBits(this.enthalpyofFormationinStandardStateat298_15Kand101325Pa) != Double.doubleToLongBits(other.enthalpyofFormationinStandardStateat298_15Kand101325Pa)) {
            return false;
        }
        if (Double.doubleToLongBits(this.absoluteEntropyinStandardStateat298_15Kand101325Pa) != Double.doubleToLongBits(other.absoluteEntropyinStandardStateat298_15Kand101325Pa)) {
            return false;
        }
        if (Double.doubleToLongBits(this.meltingPoint_1atm) != Double.doubleToLongBits(other.meltingPoint_1atm)) {
            return false;
        }
        if (Double.doubleToLongBits(this.triplePointPressure) != Double.doubleToLongBits(other.triplePointPressure)) {
            return false;
        }
        if (Double.doubleToLongBits(this.upperFlammabilityLimit) != Double.doubleToLongBits(other.upperFlammabilityLimit)) {
            return false;
        }
        if (Double.doubleToLongBits(this.liquidMolarVolumeat298_15K) != Double.doubleToLongBits(other.liquidMolarVolumeat298_15K)) {
            return false;
        }
        if (Double.doubleToLongBits(this.lowerFlammabilityLimit) != Double.doubleToLongBits(other.lowerFlammabilityLimit)) {
            return false;
        }
        if (Double.doubleToLongBits(this.prsvKappa) != Double.doubleToLongBits(other.prsvKappa)) {
            return false;
        }
        if (Double.doubleToLongBits(this.L_Twu) != Double.doubleToLongBits(other.L_Twu)) {
            return false;
        }
        if (Double.doubleToLongBits(this.M_Twu) != Double.doubleToLongBits(other.M_Twu)) {
            return false;
        }
        if (Double.doubleToLongBits(this.N_Twu) != Double.doubleToLongBits(other.N_Twu)) {
            return false;
        }
        if (Double.doubleToLongBits(this.A_Mathias_Copeman) != Double.doubleToLongBits(other.A_Mathias_Copeman)) {
            return false;
        }
        if (Double.doubleToLongBits(this.B_Mathias_Copeman) != Double.doubleToLongBits(other.B_Mathias_Copeman)) {
            return false;
        }
        if (Double.doubleToLongBits(this.C_Mathias_Copeman) != Double.doubleToLongBits(other.C_Mathias_Copeman)) {
            return false;
        }
        if (Double.doubleToLongBits(this.r_UNIQUAC) != Double.doubleToLongBits(other.r_UNIQUAC)) {
            return false;
        }
        if (Double.doubleToLongBits(this.q_UNIQUAC) != Double.doubleToLongBits(other.q_UNIQUAC)) {
            return false;
        }
        if (!Objects.equals(this.cp, other.cp)) {
            return false;
        }
        if (this.dipprChemID != other.dipprChemID) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.casNumber, other.casNumber)) {
            return false;
        }
        if (!Objects.equals(this.formula, other.formula)) {
            return false;
        }
        if (!Objects.equals(this.smiles, other.smiles)) {
            return false;
        }
        if (!Objects.equals(this.Structure, other.Structure)) {
            return false;
        }
        if (!Objects.equals(this.family, other.family)) {
            return false;
        }
        if (!Objects.equals(this.subFamily, other.subFamily)) {
            return false;
        }
        if (!Objects.equals(this.standardState, other.standardState)) {
            return false;
        }
        return true;
    }

   

   
    
  
  
    
    @Override
    public String toString(){
        return name;
    }

    /**
     * @return the Molecular Weight  [kg/kmol]
     */
    public double getMolecularWeight() {
        return molecularWeight;
    }

    /**
     * @param molecularWeight the Molecular Weight to set in [kg/kmol]
     */
    public void setMolecularWeight(double molecularWeight) {
        this.molecularWeight = molecularWeight;
    }

    /**
     * @return the Lower Flammability Limit Temperature [K]
     */
    public double getLowerFlammabilityLimitTemperature() {
        return lowerFlammabilityLimitTemperature;
    }

    /**
     * @param lowerFlammabilityLimitTemperature the Lower Flammability Limit Temperature to set in [K]
     */
    public void setLowerFlammabilityLimitTemperature(double lowerFlammabilityLimitTemperature) {
        this.lowerFlammabilityLimitTemperature = lowerFlammabilityLimitTemperature;
    }

    /**
     * @return the Upper Flammability Limit Temperature [K]
     */
    public double getUpperFlammabilityLimitTemperature() {
        return upperFlammabilityLimitTemperature;
    }

    /**
     * @param upperFlammabilityLimitTemperature the Upper Flammability Limit Temperature to set in [K]
     */
    public void setUpperFlammabilityLimitTemperature(double upperFlammabilityLimitTemperature) {
        this.upperFlammabilityLimitTemperature = upperFlammabilityLimitTemperature;
    }

    /**
     * @return the Critical Compressibility Factor
     */
    public double getCriticalCompressibilityFactor() {
        return criticalCompressibilityFactor;
    }

    /**
     * @param criticalCompressibilityFactor the Critical Compressibility Factor to set
     */
    public void setCriticalCompressibilityFactor(double criticalCompressibilityFactor) {
        this.criticalCompressibilityFactor = criticalCompressibilityFactor;
    }

    /**
     * @return the Acentric Factor
     */
    public double getAcentricFactor() {
        return acentricFactor;
    }

    /**
     * @param acentricFactor the Acentric Factor to set
     */
    public void setAcentricFactor(double acentricFactor) {
        this.acentricFactor = acentricFactor;
    }

    /**
     * @return the Gibbs Energy of Formation of Ideal Gas at 298.15[K] and 101325 [Pa] in [J/kmol]
     */
    public double getGibbsEnergyofFormationofIdealGasat298_15Kand101325Pa() {
        return gibbsEnergyofFormationofIdealGasat298_15Kand101325Pa;
    }

    /**
     * @param gibbsEnergyofFormationofIdealGasat298_15Kand101325Pa the Gibbs Energy of Formation of Ideal Gas at 298.15 [K] and 101325 [Pa] to set in [J/kmol]
     */
    public void setGibbsEnergyofFormationofIdealGasat298_15Kand101325Pa(double gibbsEnergyofFormationofIdealGasat298_15Kand101325Pa) {
        this.gibbsEnergyofFormationofIdealGasat298_15Kand101325Pa = gibbsEnergyofFormationofIdealGasat298_15Kand101325Pa;
    }

    /**
     * @return the van der Waals Reduced Volume in [m^3/kmol]
     */
    public double getVanDerWaalsReducedVolume() {
        return vanDerWaalsReducedVolume;
    }

    /**
     * @param vanDerWaalsReducedVolume the van der Waals Reduced Volume to set in [m^3/kmol]
     */
    public void setVanDerWaalsReducedVolume(double vanDerWaalsReducedVolume) {
        this.vanDerWaalsReducedVolume = vanDerWaalsReducedVolume;
    }

    /**
     * @return the van der WaalsArea in [m^2/kmol]
     */
    public double getVanderWaalsArea() {
        return vanderWaalsArea;
    }

    /**
     * @param vanderWaalsArea the van der Waals Area to set in [m^2/kmol]
     */
    public void setVanderWaalsArea(double vanderWaalsArea) {
        this.vanderWaalsArea = vanderWaalsArea;
    }

    /**
     * @return the gibbsEnergyofFormationinStandardStateat298_15Kand101325Pa
     */
    public double getGibbsEnergyofFormationinStandardStateat298_15Kand101325Pa() {
        return gibbsEnergyofFormationinStandardStateat298_15Kand101325Pa;
    }

    /**
     * @param gibbsEnergyofFormationinStandardStateat298_15Kand101325Pa the gibbsEnergyofFormationinStandardStateat298_15Kand101325Pa to set
     */
    public void setGibbsEnergyofFormationinStandardStateat298_15Kand101325Pa(double gibbsEnergyofFormationinStandardStateat298_15Kand101325Pa) {
        this.gibbsEnergyofFormationinStandardStateat298_15Kand101325Pa = gibbsEnergyofFormationinStandardStateat298_15Kand101325Pa;
    }

    /**
     * @return the radiusofGyration
     */
    public double getRadiusofGyration() {
        return radiusofGyration;
    }

    /**
     * @param radiusofGyration the radiusofGyration to set
     */
    public void setRadiusofGyration(double radiusofGyration) {
        this.radiusofGyration = radiusofGyration;
    }

    /**
     * @return the solubilityParameterat298_15K
     */
    public double getSolubilityParameterat298_15K() {
        return solubilityParameterat298_15K;
    }

    /**
     * @param solubilityParameterat298_15K the solubilityParameterat298_15K to set
     */
    public void setSolubilityParameterat298_15K(double solubilityParameterat298_15K) {
        this.solubilityParameterat298_15K = solubilityParameterat298_15K;
    }

    /**
     * @return the dipoleMoment
     */
    public double getDipoleMoment() {
        return dipoleMoment;
    }

    /**
     * @param dipoleMoment the dipoleMoment to set
     */
    public void setDipoleMoment(double dipoleMoment) {
        this.dipoleMoment = dipoleMoment;
    }

    /**
     * @return the triplePointTemperature
     */
    public double getTriplePointTemperature() {
        return triplePointTemperature;
    }

    /**
     * @param triplePointTemperature the triplePointTemperature to set
     */
    public void setTriplePointTemperature(double triplePointTemperature) {
        this.triplePointTemperature = triplePointTemperature;
    }

    /**
     * @return the criticalTemperature
     */
    public double getCriticalTemperature() {
        return criticalTemperature;
    }

    /**
     * @param criticalTemperature the criticalTemperature to set
     */
    public void setCriticalTemperature(double criticalTemperature) {
        this.criticalTemperature = criticalTemperature;
    }

    /**
     * @return the enthalpyofFormationofIdealgasat298_15Kand101325Pa
     */
    public double getEnthalpyofFormationofIdealgasat298_15Kand101325Pa() {
        return enthalpyofFormationofIdealgasat298_15Kand101325Pa;
    }

    /**
     * @param enthalpyofFormationofIdealgasat298_15Kand101325Pa the enthalpyofFormationofIdealgasat298_15Kand101325Pa to set
     */
    public void setEnthalpyofFormationofIdealgasat298_15Kand101325Pa(double enthalpyofFormationofIdealgasat298_15Kand101325Pa) {
        this.enthalpyofFormationofIdealgasat298_15Kand101325Pa = enthalpyofFormationofIdealgasat298_15Kand101325Pa;
    }

    /**
     * @return the absoluteEntropyofIdealGasat298_15Kand101325Pa
     */
    public double getAbsoluteEntropyofIdealGasat298_15Kand101325Pa() {
        return absoluteEntropyofIdealGasat298_15Kand101325Pa;
    }

    /**
     * @param absoluteEntropyofIdealGasat298_15Kand101325Pa the absoluteEntropyofIdealGasat298_15Kand101325Pa to set
     */
    public void setAbsoluteEntropyofIdealGasat298_15Kand101325Pa(double absoluteEntropyofIdealGasat298_15Kand101325Pa) {
        this.absoluteEntropyofIdealGasat298_15Kand101325Pa = absoluteEntropyofIdealGasat298_15Kand101325Pa;
    }

    /**
     * @return the netEnthalpyofCombustionStandardState298_15K
     */
    public double getNetEnthalpyofCombustionStandardState298_15K() {
        return netEnthalpyofCombustionStandardState298_15K;
    }

    /**
     * @param netEnthalpyofCombustionStandardState298_15K the netEnthalpyofCombustionStandardState298_15K to set
     */
    public void setNetEnthalpyofCombustionStandardState298_15K(double netEnthalpyofCombustionStandardState298_15K) {
        this.netEnthalpyofCombustionStandardState298_15K = netEnthalpyofCombustionStandardState298_15K;
    }

    /**
     * @return the criticalPressure
     */
    public double getCriticalPressure() {
        return criticalPressure;
    }

    /**
     * @param criticalPressure the criticalPressure to set
     */
    public void setCriticalPressure(double criticalPressure) {
        this.criticalPressure = criticalPressure;
    }

    /**
     * @return the normalBoilingPoint_1atm
     */
    public double getNormalBoilingPoint_1atm() {
        return normalBoilingPoint_1atm;
    }

    /**
     * @param normalBoilingPoint_1atm the normalBoilingPoint_1atm to set
     */
    public void setNormalBoilingPoint_1atm(double normalBoilingPoint_1atm) {
        this.normalBoilingPoint_1atm = normalBoilingPoint_1atm;
    }

    /**
     * @return the enthalpyofFusionatMeltingPoint
     */
    public double getEnthalpyofFusionatMeltingPoint() {
        return enthalpyofFusionatMeltingPoint;
    }

    /**
     * @param enthalpyofFusionatMeltingPoint the enthalpyofFusionatMeltingPoint to set
     */
    public void setEnthalpyofFusionatMeltingPoint(double enthalpyofFusionatMeltingPoint) {
        this.enthalpyofFusionatMeltingPoint = enthalpyofFusionatMeltingPoint;
    }

    /**
     * @return the criticalVolume
     */
    public double getCriticalVolume() {
        return criticalVolume;
    }

    /**
     * @param criticalVolume the criticalVolume to set
     */
    public void setCriticalVolume(double criticalVolume) {
        this.criticalVolume = criticalVolume;
    }

    /**
     * @return the enthalpyorHeatofSublimation
     */
    public double getEnthalpyorHeatofSublimation() {
        return enthalpyorHeatofSublimation;
    }

    /**
     * @param enthalpyorHeatofSublimation the enthalpyorHeatofSublimation to set
     */
    public void setEnthalpyorHeatofSublimation(double enthalpyorHeatofSublimation) {
        this.enthalpyorHeatofSublimation = enthalpyorHeatofSublimation;
    }

    /**
     * @return the dielectricConstant
     */
    public double getDielectricConstant() {
        return dielectricConstant;
    }

    /**
     * @param dielectricConstant the dielectricConstant to set
     */
    public void setDielectricConstant(double dielectricConstant) {
        this.dielectricConstant = dielectricConstant;
    }

    /**
     * @return the refractiveIndexat298_15K
     */
    public double getRefractiveIndexat298_15K() {
        return refractiveIndexat298_15K;
    }

    /**
     * @param refractiveIndexat298_15K the refractiveIndexat298_15K to set
     */
    public void setRefractiveIndexat298_15K(double refractiveIndexat298_15K) {
        this.refractiveIndexat298_15K = refractiveIndexat298_15K;
    }

    /**
     * @return the flashPoint
     */
    public double getFlashPoint() {
        return flashPoint;
    }

    /**
     * @param flashPoint the flashPoint to set
     */
    public void setFlashPoint(double flashPoint) {
        this.flashPoint = flashPoint;
    }

    /**
     * @return the autoIgnitionTermperature
     */
    public double getAutoIgnitionTermperature() {
        return autoIgnitionTermperature;
    }

    /**
     * @param autoIgnitionTermperature the autoIgnitionTermperature to set
     */
    public void setAutoIgnitionTermperature(double autoIgnitionTermperature) {
        this.autoIgnitionTermperature = autoIgnitionTermperature;
    }

    /**
     * @return the enthalpyofFormationinStandardStateat298_15Kand101325Pa
     */
    public double getEnthalpyofFormationinStandardStateat298_15Kand101325Pa() {
        return enthalpyofFormationinStandardStateat298_15Kand101325Pa;
    }

    /**
     * @param enthalpyofFormationinStandardStateat298_15Kand101325Pa the enthalpyofFormationinStandardStateat298_15Kand101325Pa to set
     */
    public void setEnthalpyofFormationinStandardStateat298_15Kand101325Pa(double enthalpyofFormationinStandardStateat298_15Kand101325Pa) {
        this.enthalpyofFormationinStandardStateat298_15Kand101325Pa = enthalpyofFormationinStandardStateat298_15Kand101325Pa;
    }

    /**
     * @return the absoluteEntropyinStandardStateat298_15Kand101325Pa
     */
    public double getAbsoluteEntropyinStandardStateat298_15Kand101325Pa() {
        return absoluteEntropyinStandardStateat298_15Kand101325Pa;
    }

    /**
     * @param absoluteEntropyinStandardStateat298_15Kand101325Pa the absoluteEntropyinStandardStateat298_15Kand101325Pa to set
     */
    public void setAbsoluteEntropyinStandardStateat298_15Kand101325Pa(double absoluteEntropyinStandardStateat298_15Kand101325Pa) {
        this.absoluteEntropyinStandardStateat298_15Kand101325Pa = absoluteEntropyinStandardStateat298_15Kand101325Pa;
    }

    /**
     * @return the meltingPoint_1atm
     */
    public double getMeltingPoint_1atm() {
        return meltingPoint_1atm;
    }

    /**
     * @param meltingPoint_1atm the meltingPoint_1atm to set
     */
    public void setMeltingPoint_1atm(double meltingPoint_1atm) {
        this.meltingPoint_1atm = meltingPoint_1atm;
    }

    /**
     * @return the triplePointPressure
     */
    public double getTriplePointPressure() {
        return triplePointPressure;
    }

    /**
     * @param triplePointPressure the triplePointPressure to set
     */
    public void setTriplePointPressure(double triplePointPressure) {
        this.triplePointPressure = triplePointPressure;
    }

    /**
     * @return the upperFlammabilityLimit
     */
    public double getUpperFlammabilityLimit() {
        return upperFlammabilityLimit;
    }

    /**
     * @param upperFlammabilityLimit the upperFlammabilityLimit to set
     */
    public void setUpperFlammabilityLimit(double upperFlammabilityLimit) {
        this.upperFlammabilityLimit = upperFlammabilityLimit;
    }

    /**
     * @return the liquidMolarVolumeat298_15K
     */
    public double getLiquidMolarVolumeat298_15K() {
        return liquidMolarVolumeat298_15K;
    }

    /**
     * @param liquidMolarVolumeat298_15K the liquidMolarVolumeat298_15K to set
     */
    public void setLiquidMolarVolumeat298_15K(double liquidMolarVolumeat298_15K) {
        this.liquidMolarVolumeat298_15K = liquidMolarVolumeat298_15K;
    }

    /**
     * @return the lowerFlammabilityLimit
     */
    public double getLowerFlammabilityLimit() {
        return lowerFlammabilityLimit;
    }

    /**
     * @param lowerFlammabilityLimit the lowerFlammabilityLimit to set
     */
    public void setLowerFlammabilityLimit(double lowerFlammabilityLimit) {
        this.lowerFlammabilityLimit = lowerFlammabilityLimit;
    }

    /**
     * @return the prsvKappa
     */
    public double getPrsvKappa() {
        return prsvKappa;
    }

    /**
     * @param prsvKappa the prsvKappa to set
     */
    public void setPrsvKappa(double prsvKappa) {
        this.prsvKappa = prsvKappa;
    }

    /**
     * @return the dipprChemID
     */
    public int getDipprChemID() {
        return dipprChemID;
    }

    /**
     * @param dipprChemID the dipprChemID to set
     */
    public void setDipprChemID(int dipprChemID) {
        this.dipprChemID = dipprChemID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the casName
     */
    public String getCasNumber() {
        return casNumber;
    }

    /**
     * @param casName the casName to set
     */
    public void setCasNumber(String casNumber) {
        this.casNumber = casNumber;
    }

    /**
     * @return the formula
     */
    public String getFormula() {
        return formula;
    }

    /**
     * @param formula the formula to set
     */
    public void setFormula(String formula) {
        this.formula = formula;
    }

    /**
     * @return the smiles
     */
    public String getSmiles() {
        return smiles;
    }

    /**
     * @param smiles the smiles to set
     */
    public void setSmiles(String smiles) {
        this.smiles = smiles;
    }

    /**
     * @return the Structure
     */
    public String getStructure() {
        return Structure;
    }

    /**
     * @param Structure the Structure to set
     */
    public void setStructure(String Structure) {
        this.Structure = Structure;
    }

    /**
     * @return the family
     */
    public String getFamily() {
        return family;
    }

    /**
     * @param family the family to set
     */
    public void setFamily(String family) {
        this.family = family;
    }

    /**
     * @return the subFamily
     */
    public String getSubFamily() {
        return subFamily;
    }

    /**
     * @param subFamily the subFamily to set
     */
    public void setSubFamily(String subFamily) {
        this.subFamily = subFamily;
    }

    /**
     * @return the standardState
     */
    public String getStandardState() {
        return standardState;
    }

    /**
     * @param standardState the standardState to set
     */
    public void setStandardState(String standardState) {
        this.standardState = standardState;
    }

    /**
     * @return the L_Twu
     */
    public double getL_Twu() {
        return L_Twu;
    }

    /**
     * @param L_Twu the L_Twu to set
     */
    public void setL_Twu(double L_Twu) {
        this.L_Twu = L_Twu;
    }

    /**
     * @return the M_Twu
     */
    public double getM_Twu() {
        return M_Twu;
    }

    /**
     * @param M_Twu the M_Twu to set
     */
    public void setM_Twu(double M_Twu) {
        this.M_Twu = M_Twu;
    }

    /**
     * @return the N_Twu
     */
    public double getN_Twu() {
        return N_Twu;
    }

    /**
     * @param N_Twu the N_Twu to set
     */
    public void setN_Twu(double N_Twu) {
        this.N_Twu = N_Twu;
    }

    /**
     * @return the A_Mathias_Copeman
     */
    public double getA_Mathias_Copeman() {
        return A_Mathias_Copeman;
    }

    /**
     * @param A_Mathias_Copeman the A_Mathias_Copeman to set
     */
    public void setA_Mathias_Copeman(double A_Mathias_Copeman) {
        this.A_Mathias_Copeman = A_Mathias_Copeman;
    }

    /**
     * @return the B_Mathias_Copeman
     */
    public double getB_Mathias_Copeman() {
        return B_Mathias_Copeman;
    }

    /**
     * @param B_Mathias_Copeman the B_Mathias_Copeman to set
     */
    public void setB_Mathias_Copeman(double B_Mathias_Copeman) {
        this.B_Mathias_Copeman = B_Mathias_Copeman;
    }

    /**
     * @return the C_Mathias_Copeman
     */
    public double getC_Mathias_Copeman() {
        return C_Mathias_Copeman;
    }

    /**
     * @param C_Mathias_Copeman the C_Mathias_Copeman to set
     */
    public void setC_Mathias_Copeman(double C_Mathias_Copeman) {
        this.C_Mathias_Copeman = C_Mathias_Copeman;
    }

    /**
     * @return the r_UNIQUAC
     */
    public double getR_UNIQUAC() {
        return r_UNIQUAC;
    }

    /**
     * @param r_UNIQUAC the r_UNIQUAC to set
     */
    public void setR_UNIQUAC(double r_UNIQUAC) {
        this.r_UNIQUAC = r_UNIQUAC;
    }

    /**
     * @return the q_UNIQUAC
     */
    public double getQ_UNIQUAC() {
        return q_UNIQUAC;
    }

    /**
     * @param q_UNIQUAC the q_UNIQUAC to set
     */
    public void setQ_UNIQUAC(double q_UNIQUAC) {
        this.q_UNIQUAC = q_UNIQUAC;
    }

//    /**
//     * @return the qq_UNIQUAC
//     */
//    public double getQq_UNIQUAC() {
//        return qq_UNIQUAC;
//    }
//
//    /**
//     * @param qq_UNIQUAC the qq_UNIQUAC to set
//     */
//    public void setQq_UNIQUAC(double qq_UNIQUAC) {
//        this.qq_UNIQUAC = qq_UNIQUAC;
//    }

    public CpEquation getCp() {
        return cp;
    }

    public void setCp(CpEquation cp) {
        this.cp = cp;
    }


    
}
