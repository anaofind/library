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
	public String getString(int index) {
		if (index == 0) {
			return this.value;
		}
		return null;
	}

	@Override
	public Integer getInteger(int index) {
		return null;
	}

	@Override
	public Long getLong(int index) {
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
		return TypeResolver.STRING;
	}

	@Override
	public JsonValue toJson() {
		return new JsonString(this.value);
	}

	@Override
	public boolean equals(Anadatair other) {
		return other.getType().equals(TypeResolver.STRING) && other.getString().equals(value);
	}
}
