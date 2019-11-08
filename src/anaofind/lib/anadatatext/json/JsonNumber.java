package anaofind.lib.anadatatext.json;

import anaofind.lib.anadatatext.ir.*;

/**
 * json numeric
 * @author anaofind
 *
 */
public class JsonNumber implements JsonConverter{

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
	
	@Override
	public String toTextJson() {
		if (this.type.equals("integer")) {
			return "" + (int) this.value;
		}
		return "" + this.value;
	}

	@Override
	public String toTextIR() {
		if (this.type.equals("integer")) {
			return new IRInteger((int)this.value).toTextIR();
		}
		
		return new IRDouble(this.value).toTextIR();
	}

}
