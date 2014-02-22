package application.model;

public class ThermoPackage{
    
    private EquationOfState equationOfState;
    private MixingRuleType mixingRule;

    /**
     * @return the equationOfState
     */
    public EquationOfState getEquationOfState() {
	return equationOfState;
    }

    /**
     * @param equationOfState the equationOfState to set
     */
    public void setEquationOfState(EquationOfState equationOfState) {
	this.equationOfState = equationOfState;
    }

    /**
     * @return the mixingRule
     */
    public MixingRuleType getMixingRule() {
	return mixingRule;
    }

    /**
     * @param mixingRule the mixingRule to set
     */
    public void setMixingRule(MixingRuleType mixingRule) {
	this.mixingRule = mixingRule;
    }

}

