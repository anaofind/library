package anaofind.lib.anadatair.ir;

import anaofind.lib.anadatair.AIRSettable;
import anaofind.lib.anadatair.AIRValue;
import anaofind.lib.anadatair.visitor.VisitorIR;

/**
 * ir value integer
 * @author anaofind
 */
public class IRInteger implements IRValue{

	/**
	 * the value
	 */
	private int value;
	
	/**
	 * construct
	 * @param value the value
	 */
	public IRInteger(int value) {
		this.value = value;
	}
	
	/**
	 * getter value
	 * @return the value
	 */
	public int getValue() {
		return this.value;
	}
		
	@Override
	public void accept(VisitorIR visitor) {
		visitor.visitIntegerIR(this);
	}

	@Override
	public AIRValue toAIR() {
		AIRSettable air = new AIRSettable();
		air.addInteger(this.value);
		return air.toGettable();
	}	
}
