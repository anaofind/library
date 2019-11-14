package anaofind.lib.anadatair.ir;

import anaofind.lib.anadatair.data.*;

/**
 * ir value null
 * @author anaofind
 */
public class IRNull implements IRConverter{

	@Override
	public String toTextIR() {
		return "(null:)";
	}

	@Override
	public DataValue toDataValue() {
		return new DataSettable().toGettable();
	}

}
