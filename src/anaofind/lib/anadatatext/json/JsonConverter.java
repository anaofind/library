package anaofind.lib.anadatatext.json;

import anaofind.lib.anadatatext.ir.IRConverter;

/**
 * json value
 * @author anaofind
 *
 */
public interface JsonConverter extends IRConverter{

	/**
	 * to json
	 * @return the json equivalents
	 */
	public String toJson();
	
}
