package anaofind.lib.anadatair.json;

import anaofind.lib.anadatair.visitor.VisitorJSON;

/**
 * json value null
 * @author anaofind
 */
public class JsonNull implements JsonValue{

	@Override
	public void accept(VisitorJSON visitor) {
		visitor.visitNullJSON(this);
	}

	@Override
	public String toString() {
		return "null";
	}

	
	
}
