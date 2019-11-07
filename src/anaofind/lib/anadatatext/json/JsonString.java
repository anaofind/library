package anaofind.lib.anadatatext.json;

import java.util.Objects;

import anaofind.lib.anadatatext.ir.IRString;

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
	
	@Override
	public String toJson() {
		return "\"" + this.value + "\"";
	}

	@Override
	public String toIR() {
		return new IRString(this.value).toIR();
	}

}
