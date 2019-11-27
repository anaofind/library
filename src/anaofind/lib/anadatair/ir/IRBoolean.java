package anaofind.lib.anadatair.ir;

import anaofind.lib.anadatair.AIRSettable;
import anaofind.lib.anadatair.AIRValue;
import anaofind.lib.anadatair.visitor.VisitorIR;

/**
 * the ir value boolean
 * @author anaofind
 */
public class IRBoolean implements IRValue{

	/**
	 * the value
	 */
	private boolean value;
	
	/**
	 * construct 
	 * @param value the value
	 */
	public IRBoolean(boolean value) {
		this.value = value;
	}
	
	/**
	 * getter value 
	 * @return the value
	 */
	public boolean getValue() {
		return this.value;
	}

	@Override
	public void accept(VisitorIR visitor) {
		visitor.visitBooleanIR(this);
	}

	@Override
	public AIRValue toAIR() {
		AIRSettable air = new AIRSettable();
		air.addBoolean(this.value);
		return air.toGettable();
	}
}