package anaofind.lib.anadatair.json;

import java.util.Objects;

import anaofind.lib.anadatair.visitor.VisitorJSON;

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
	public void accept(VisitorJSON visitor) {
		visitor.visitStringJSON(this);
	}

	@Override
	public String toString() {
		return String.format("\"%s\"", this.value);
	}
	
	
	
}
