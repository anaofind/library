package anaofind.lib.anadatair.json;

import anaofind.lib.anadatair.Anadatair;
import anaofind.lib.anadatair.AnadatairSettable;

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
	public String toString() {
		return "" + this.value;
	}

	@Override
	public Anadatair toAnadatair() {
		AnadatairSettable air = new AnadatairSettable();
		air.addBoolean(this.value);
		return air.toGettable();
	}
	
	
}
