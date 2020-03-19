package anaofind.lib.anadatair.json;

import anaofind.lib.anadatair.Anadatair;
import anaofind.lib.anadatair.AnadatairSettable;

/**
 * json value null
 * @author anaofind
 */
public class JsonNull implements JsonValue{

	@Override
	public String toString() {
		return "null";
	}

	@Override
	public Anadatair toAnadatair() {
		AnadatairSettable air = new AnadatairSettable();
		return air.toGettable();
	}

	
	
}
