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
	private long value;
	
	/**
	 * construct
	 * @param value the long value
	 */
	public AnadatairInteger(long value) {
		this.value = value;
	}
	
	/**
	 * construct
	 * @param value the integer value
	 */
	public AnadatairInteger(int value) {
		this.value = value;
	}

	@Override
	public String getString(int index) {
		return null;
	}

	@Override
	public Long getInteger(int index) {
		if (index == 0) {
			return this.value;
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
		return other.getType().equals(TypeResolver.INT) || other.getType().equals(TypeResolver.DOUBLE) && other.getDouble() == value;
	}
}
