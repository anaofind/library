package anaofind.lib.anadatair.ir;

import anaofind.lib.anadatair.AnadatairSettable;
import anaofind.lib.anadatair.Anadatair;
import anaofind.lib.anadatair.visitor.VisitorIR;

/**
 * ir value integer
 * @author anaofind
 */
public class IRInteger implements IRValue{

	/**
	 * the value
	 */
	private long value;
	
	/**
	 * construct
	 * @param value the integer value
	 */
	public IRInteger(int value) {
		this.value = value;
	}
	
	/**
	 * construct
	 * @param value the long value
	 */
	public IRInteger(long value) {
		this.value = value;
	}
	
	/**
	 * getter value
	 * @return the value
	 */
	public long getValue() {
		return this.value;
	}
		
	@Override
	public void accept(VisitorIR visitor) {
		visitor.visitIntegerIR(this);
	}

	@Override
	public Anadatair toAnadatair() {
		AnadatairSettable air = new AnadatairSettable();
		air.addInteger(this.value);
		return air.toGettable();
	}	
}
