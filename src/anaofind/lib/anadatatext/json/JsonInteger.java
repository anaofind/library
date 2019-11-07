package anaofind.lib.anadatatext.json;

import anaofind.lib.anadatatext.ir.IRInteger;

/**
 * json numeric
 * @author anaofind
 *
 */
public class JsonInteger implements JsonValue{

	/**
	 * the value
	 */
	private int value;
	
	/**
	 * construct
	 * @param value the value
	 */
	public JsonInteger(int value) {
		this.value = value;
	}
	
	@Override
	public String toJson() {
		return "" + this.value;
	}

	@Override
	public String toIR() {
		return new IRInteger(this.value).toIR();
	}

}
