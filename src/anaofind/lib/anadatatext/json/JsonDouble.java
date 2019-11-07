package anaofind.lib.anadatatext.json;

import anaofind.lib.anadatatext.ir.IRDouble;

/**
 * json double
 * @author anaofind
 *
 */
public class JsonDouble implements JsonValue{

	/**
	 * the value
	 */
	private double value;
	
	/**
	 * construct 
	 * @param value the value
	 */
	public JsonDouble (double value) {
		this.value = value;
	}
	
	@Override
	public String toJson() {
		return "" + this.value;
	}

	@Override
	public String toIR() {
		return new IRDouble(this.value).toIR();
	}

}
