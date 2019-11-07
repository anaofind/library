package anaofind.lib.anadatatext.json;

import anaofind.lib.anadatatext.ir.IRValue;

/**
 * json value
 * @author anaofind
 *
 */
public interface JsonValue extends IRValue{

	/**
	 * to json
	 * @return the json equivalents
	 */
	public String toJson();
	
}
