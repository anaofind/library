package anaofind.lib.anadatatext.json;

import anaofind.lib.anadatatext.ir.IRBoolean;

/**
 * json boolean
 * @author anaofind
 *
 */
public class JsonBoolean implements JsonConverter{

	/**
	 * the value
	 */
	private boolean value;
	
	/**
	 * construct
	 * @param value the value
	 */
	public JsonBoolean (boolean value) {
		this.value = value;
	}
	
	@Override
	public String toJson() {
		return "" + value;
	}

	@Override
	public String toIR() {
		return new IRBoolean(this.value).toIR();
	}


	
}
