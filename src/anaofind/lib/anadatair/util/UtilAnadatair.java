package anaofind.lib.anadatair.util;

import anaofind.lib.anadatair.*;
import anaofind.lib.anadatair.compilator.*;
import anaofind.lib.anadatair.compilator.Compilator.CompilatorException;

/**
 * util anadatair method
 * @author anaofind
 */
public class UtilAnadatair {

	/**
	 * decode json to anadatair
	 * @param json the json text
	 * @return the anadatair equivalent
	 * @throws CompilatorException 
	 */
	public static Anadatair jsonDecode(String json) throws CompilatorException {
		CompilatorJSON cmp = new CompilatorJSON(json);
		return cmp.getValue().toAnadatair();
	}
	
	/**
	 * encode anadatair to json string
	 * @param anadatair the anadatair to encode
	 * @return the json string equivalent to anadatair
	 */
	public static String jsonEncode(Anadatair anadatair) {
		return anadatair.toJson().toString();
	}
	
	/**
	 * decode object to anadatair
	 * @param value the object to decode
	 * @return
	 */
	public static Anadatair decode(Object value) {
		if (value.getClass().equals(Integer.class))  {
			return new AnadatairInteger((Integer) value);
		}
		if (value.getClass().equals(Double.class))  {
			return new AnadatairDouble((Double) value);
		}
		if (value.getClass().equals(Boolean.class))  {
			return new AnadatairBoolean((Boolean) value);
		}
		if (value.getClass().equals(String.class))  {
			return new AnadatairString((String) value);
		}
		if (value.getClass().equals(String.class))  {
			return new AnadatairString((String) value);
		}
		if (value.getClass().equals(Anadatair.class))  {
			return (Anadatair) value;
		}
		return null;
	}
}
