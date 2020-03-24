package anaofind.lib.anadatair;

import anaofind.lib.anadatair.json.JsonNumber;
import anaofind.lib.anadatair.json.JsonValue;
import anaofind.lib.anadatair.util.TypeResolver;

/**
 * anadatair double type
 * @author leo_r
 *
 */
public class AnadatairDouble extends AnadatairPrimitive{
	/**
	 * the value
	 */
	private double value;
	
	/**
	 * construct
	 * @param value the long value
	 */
	public AnadatairDouble(double value) {
		this.value = value;
	}

	@Override
	public String getString(int index) {
		return null;
	}

	@Override
	public Long getInteger(int index) {
		return null;
	}

	@Override
	public Double getDouble(int index) {
		if (index == 0) {
			return this.value;
		}
		return null;
	}

	@Override
	public Boolean getBoolean(int index) {
		return null;
	}

	@Override
	public String getType() {
		return TypeResolver.DOUBLE;
	}

	@Override
	public JsonValue toJson() {
		return new JsonNumber(this.value);
	}
}