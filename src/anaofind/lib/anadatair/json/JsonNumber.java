package anaofind.lib.anadatair.json;

import anaofind.lib.anadatair.Anadatair;
import anaofind.lib.anadatair.AnadatairSettable;

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
	private String type = "double";
	
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
		this.type = "integer";
	}
	
	public JsonNumber(long value) {
		this.value = value;
		this.type = "integer";
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
		if (type.equals("integer")) {
			return "" + (int)value;	
		}
		return "" + value;
 	}

	@Override
	public Anadatair toAnadatair() {
		AnadatairSettable air = new AnadatairSettable();
		if (this.type.equals("integer")) {
			air.addInteger((int)this.value);
		} else {
			air.addDouble(this.value);	
		}
		return air.toGettable();
	}
	
	
	
}
