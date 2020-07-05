package anaofind.lib.anadatair;

import anaofind.lib.anadatair.json.JsonString;
import anaofind.lib.anadatair.json.JsonValue;
import anaofind.lib.anadatair.util.TypeResolver;

/**
 * anadatair string type
 * @author anaofind
 *
 */
public class AnadatairString extends AnadatairPrimitive{
	
	/**
	 * the value
	 * @return the value
	 */
	private String value;
	
	/**
	 * construct
	 * @param value
	 */
	public AnadatairString(String value) {
		this.value = value;
	}

	@Override
	public String getType() {
		return TypeResolver.STRING;
	}

	@Override
	public JsonValue toJson() {
		return new JsonString(this.value);
	}

	@Override
	public Object getValue() {
		return this.value;
	}
}
