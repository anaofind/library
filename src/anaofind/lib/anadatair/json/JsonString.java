package anaofind.lib.anadatair.json;

import java.util.Objects;

import anaofind.lib.anadatair.ir.IRString;

/**
 * json string
 * @author anaofind
 */
public class JsonString implements JsonConverter{

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
	public String toTextJson() {
		return "\"" + this.value + "\"";
	}

	@Override
	public String toTextIR() {
		return new IRString(this.value).toTextIR();
	}

}
