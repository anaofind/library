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
	public String getType() {
		return TypeResolver.DOUBLE;
	}

	@Override
	public JsonValue toJson() {
		return new JsonNumber(this.value);
	}
	
	@Override
	public boolean equals(Anadatair other) {
		switch (other.getType()) {
		case TypeResolver.INT : 
			return ((int) other.getValue()) == this.value;
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
