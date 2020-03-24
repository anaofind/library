package anaofind.lib.anadatair;

import anaofind.lib.anadatair.json.JsonString;
import anaofind.lib.anadatair.json.JsonValue;

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
	public String getString(int index) {
		if (index == 0) {
			return this.value;
		}
		return null;
	}

	@Override
	public Long getInteger(int index) {
		return null;
	}

	@Override
	public Double getDouble(int index) {
		return null;
	}

	@Override
	public Boolean getBoolean(int index) {
		return null;
	}

	@Override
	public String getType() {
		return "string";
	}

	@Override
	public JsonValue toJson() {
		return new JsonString(this.value);
	}


}
