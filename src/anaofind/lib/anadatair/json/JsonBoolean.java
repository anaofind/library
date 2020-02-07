package anaofind.lib.anadatair.json;

import anaofind.lib.anadatair.visitor.VisitorJSON;

/**
 * json boolean
 * @author anaofind
 *
 */
public class JsonBoolean implements JsonValue{

	/**
	 * the value
	 */
	private boolean value;
	
	/**
	 * construct
	 * @param value the value
	 */
	public JsonBoolean (boolean value) {
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
	public void accept(VisitorJSON visitor) {
		visitor.visitBooleanJSON(this);
	}

	@Override
	public String toString() {
		return "" + this.value;
	}
	
	
}
