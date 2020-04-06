package anaofind.lib.anadatair;

import anaofind.lib.anadatair.json.JsonNumber;
import anaofind.lib.anadatair.json.JsonValue;
import anaofind.lib.anadatair.util.TypeResolver;

/**
 * anadatair long type
 * @author anaofind
 */
public class AnadatairLong extends AnadatairPrimitive{

	/**
	 * the value
	 */
	private long value;
	
	/**
	 * construct 
	 * @param value the value
	 */
	public AnadatairLong(long value) {
		this.value = value;
	}
	
	@Override
	public String getString(int index) {
		return null;
	}

	@Override
	public Integer getInteger(int index) {
		return null;
	}

	@Override
	public Long getLong(int index) {
		if (index == 0) {
			return this.value;
		}
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
		return TypeResolver.LONG;
	}

	@Override
	public boolean equals(Anadatair other) {
		return other.getType().equals(TypeResolver.LONG) && other.getLong() == this.value;
	}

	@Override
	public JsonValue toJson() {
		return new JsonNumber(this.value);
	}

}
