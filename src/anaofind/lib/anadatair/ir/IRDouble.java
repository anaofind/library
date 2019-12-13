package anaofind.lib.anadatair.ir;

import anaofind.lib.anadatair.AnadatairSettable;
import anaofind.lib.anadatair.Anadatair;
import anaofind.lib.anadatair.visitor.VisitorIR;

/**
 * ir value double
 * @author anaofind
 */
public class IRDouble implements IRValue{

	/**
	 * the value
	 */
	private double value;
	
	/**
	 * construct
	 * @param value the value
	 */
	public IRDouble(double value) {
		this.value = value;
	}
	
	/**
	 * getter value
	 * @return the value
	 */
	public double getValue() {
		return this.value;
	}
	
	@Override
	public void accept(VisitorIR visitor) {
		visitor.visitDoubleIR(this);
	}

	@Override
	public Anadatair toAnadatair() {
		AnadatairSettable air = new AnadatairSettable();
		air.addDouble(this.value);
		return air.toGettable();
	}

}
