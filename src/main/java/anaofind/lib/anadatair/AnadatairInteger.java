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
	public String getType() {
		return TypeResolver.INT;
	}

	@Override
	public JsonValue toJson() {
		return new JsonNumber(this.value);
	}
	
	@Override
	public boolean equals(Anadatair other) {
		switch (other.getType()) {
		case TypeResolver.DOUBLE : 
			return ((double) other.getValue()) == this.value;
		case TypeResolver.LONG : 
			return ((long) other.getValue() == this.value);
		}
		return other.getValue().equals(this.getValue());
	}

	@Override
	public Object getValue() {
		return this.value;
	}
}
