package anaofind.lib.anadatatext.json;

import anaofind.lib.anadatatext.ir.IRNull;

/**
 * json value null
 * @author anaofind
 */
public class JsonNull implements JsonConverter{

	@Override
	public String toTextIR() {
		return new IRNull().toTextIR();
	}
	
	@Override
	public String toTextJson() {
		return "null";
	}

}
