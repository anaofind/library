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
	public String getType() {
		return TypeResolver.LONG;
	}

	@Override
	public JsonValue toJson() {
		return new JsonNumber(this.value);
	}

	@Override
	public Object getValue() {
		return this.value;
	}
}
