package anaofind.lib.anadatair;

import anaofind.lib.anadatair.json.JsonBoolean;
import anaofind.lib.anadatair.json.JsonValue;
import anaofind.lib.anadatair.util.TypeResolver;

/**
 * anadatair boolean type
 * @author anaofind
 *
 */
public class AnadatairBoolean extends AnadatairPrimitive{

	/**
	 * the value
	 */
	private boolean value;
	
	/**
	 * contruct
	 * @param value the value
	 */
	public AnadatairBoolean(boolean value) {
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
		return null;
	}

	@Override
	public Boolean getBoolean(int index) {
		if (index == 0) {
			return this.value;
		}
		return null;
	}

	@Override
	public String getType() {
		return TypeResolver.BOOLEAN;
	}

	@Override
	public JsonValue toJson() {
		return new JsonBoolean(this.value);
	}

}
