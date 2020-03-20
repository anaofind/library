package anaofind.lib.anadatair;

import anaofind.lib.anadatair.compilator.*;
import anaofind.lib.anadatair.compilator.Compilator.CompilatorException;

/**
 * util anadatair method
 * @author anaofind
 */
public class UtilAnadatair {

	/**
	 * transform json to anadatair
	 * @param json the json text
	 * @return the anadatair equivalent
	 * @throws CompilatorException 
	 */
	public static Anadatair jsonToAnadatair(String json) throws CompilatorException {
		CompilatorJSON cmp = new CompilatorJSON(json);
		return cmp.getValue().toAnadatair();
	}
	
}
