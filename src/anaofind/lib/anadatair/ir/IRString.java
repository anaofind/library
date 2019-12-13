package anaofind.lib.anadatair.ir;

import anaofind.lib.anadatair.*;
import anaofind.lib.anadatair.visitor.VisitorIR;

/**
 * ir value string
 * @author anaofind
 */
public class IRString implements IRValue{

	/**
	 * the value
	 */
	private String value;
	
	/**
	 * construct
	 * @param value the value
	 */
	public IRString(String value) {
		this.value = value;
	}

	/**
	 * getter value
	 * @return the value
	 */
	public String getValue() {
		return this.value;
	}
	
	@Override
	public void accept(VisitorIR visitor) {
		visitor.visitStringIR(this);
	}

	@Override
	public Anadatair toAnadatair() {
		AnadatairSettable air = new AnadatairSettable();
		air.addString(this.value);
		return air.toGettable();
	}
	
}
