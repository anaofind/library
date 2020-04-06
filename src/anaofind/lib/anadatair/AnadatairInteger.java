package anaofind.lib.anadatair;

import anaofind.lib.anadatair.json.JsonNumber;
import anaofind.lib.anadatair.json.JsonValue;
import anaofind.lib.anadatair.util.TypeResolver;

/**
 * anadatair integer type
 * @author anaofind
 *
 */
public class AnadatairInteger extends AnadatairPrimitive{

	/**
	 * the value
	 */
	private int value;
	
	/**
	 * construct
	 * @param value the value
	 */
	public AnadatairInteger(int value) {
		this.value = value;
	}
	
	@Override
	public String getString(int index) {
		return null;
	}

	@Override
	public Integer getInteger(int index) {
		if (index == 0) {
			return this.value;
		}
		return null;
	}
	

	@Override
	public Long getLong(int index) {
		if (index == 0) {
			return (long)this.value;
		}
		return null;
	}

	@Override
	public Double getDouble(int index) {
		if (index == 0) {
			return (double)this.value;
		}
		return null;
	}

	@Override
	public Boolean getBoolean(int index) {
		return null;
	}

	@Override
	public String getType() {
		return TypeResolver.INT;
	}

	@Override
	public JsonValue toJson() {
		return new JsonNumber(this.value);
	}

	@Override
	public boolean equals(Anadatair other) {
		return other.getType().equals(TypeResolver.INT) || (other.getType().equals(TypeResolver.DOUBLE) && other.getDouble() == value) || (other.getType().equals(TypeResolver.LONG) && other.getLong() == value);
	}
}
