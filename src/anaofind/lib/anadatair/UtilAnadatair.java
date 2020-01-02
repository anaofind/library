package anaofind.lib.anadatair;

import anaofind.lib.anadatair.compilator.*;
import anaofind.lib.anadatair.translator.*;

/**
 * util anadatair method
 * @author anaofind
 */
public class UtilAnadatair {

	/**
	 * transform json text in anadatair value
	 * @param jsonText the json text
	 * @return the anadatair value equivalent
	 */
	public static Anadatair getAnadatairJSON(String jsonText) {
		CompilatorJSON cmp = new CompilatorJSON(jsonText);
		TranslatorJsonIr tsl = new TranslatorJsonIr(cmp.getValue());
		return tsl.getValue().toAnadatair();
	}
	
	
}
