package anaofind.lib.anadatatext.json;

import anaofind.lib.anadatatext.ir.IRNull;

/**
 * json value null
 * @author anaofind
 */
public class JsonNull implements JsonConverter{

	@Override
	public String toIR() {
		return "" + null;
	}

	@Override
	public String toJson() {
		return new IRNull().toIR();
	}

}
