package anaofind.lib.anadatatext.ir;

import anaofind.lib.anadatext.data.DataNull;
import anaofind.lib.anadatext.data.DataValue;

/**
 * ir value null
 * @author anaofind
 */
public class IRNull implements IRValue{

	@Override
	public String toIR() {
		return "(null:)";
	}

	@Override
	public DataValue toDataValue() {
		return new DataNull();
	}

}
