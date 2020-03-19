package anaofind.lib.anadatair.json;

import java.util.Objects;

import anaofind.lib.anadatair.Anadatair;
import anaofind.lib.anadatair.AnadatairSettable;

/**
 * json string
 * @author anaofind
 */
public class JsonString implements JsonValue{

	/**
	 * the value
	 */
	private String value;

	/**
	 * construct
	 * @param value the value
	 */
	public JsonString(String value) {
		Objects.requireNonNull(value);
		this.value = value;
	}

	/**
	 * getter value
	 * @return the value
	 */
	public String getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return String.format("\"%s\"", this.value);
	}

	@Override
	public Anadatair toAnadatair() {
		AnadatairSettable air = new AnadatairSettable();
		air.addString(this.value);
		return air.toGettable();
	}



}
