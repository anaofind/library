package anaofind.lib.anadatair.json;

import anaofind.lib.anadatair.Anadatair;
import anaofind.lib.anadatair.AnadatairDouble;
import anaofind.lib.anadatair.AnadatairInteger;
import anaofind.lib.anadatair.AnadatairLong;
import anaofind.lib.anadatair.util.TypeResolver;

/**
 * json numeric
 * @author anaofind
 *
 */
public class JsonNumber implements JsonValue{

	/**
	 * the value
	 */
	private double value;
	
	/**
	 * the type used
	 */
	private String type = TypeResolver.DOUBLE;
	
	/**
	 * construct
	 * @param value the value double
	 */
	public JsonNumber(double value) {
		this.value = value;
	}
	
	/**
	 * construct
	 * @param value the value integer
	 */
	public JsonNumber(int value) {
		this.value = value;
		this.type = TypeResolver.INT;
	}
	
	/**
	 * construct with value long
	 * @param value
	 */
	public JsonNumber(long value) {
		this.value = value;
		this.type = TypeResolver.LONG;
	}
	
	/**
	 * getter number type
	 * @return the number type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * getter value
	 * @return the value (double)
	 */
	public double getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		if (type.equals(TypeResolver.INT) || type.equals(TypeResolver.LONG)) {
			return "" + (long)value;	
		}
		return "" + value;
 	}

	@Override
	public Anadatair toAnadatair() {
		if (this.type.equals(TypeResolver.INT)) {
			return new AnadatairInteger((int)this.value);
		}
		if (this.type.equals(TypeResolver.LONG)) {
			return new AnadatairLong((long)this.value);
		}
		return new AnadatairDouble(this.value);
	}	
}
