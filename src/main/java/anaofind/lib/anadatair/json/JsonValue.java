package anaofind.lib.anadatair.json;

import anaofind.lib.anadatair.Anadatair;

/**
 * json value
 * @author anaofind
 *
 */
public interface JsonValue{

	/** 
	 * to anadatair
	 */
	public Anadatair toAnadatair();
	
	/**
	 * pretty string
	 * @param the tabulation
	 * @return the string pretty of json value
	 */
	default String prettyString(int tabulation) {
		return toString();
	}
}
