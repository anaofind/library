package anaofind.lib.anadatair.ir;

import anaofind.lib.anadatair.data.*;

/**
 * the data value
 * @author anaofind
 *
 */
public interface IRConverter {

	/**
	 * to intermediate representation
	 * @return the ir text
	 */
	public String toTextIR();
	
	/**
	 * to data value
	 * @return the data value equivalent
	 */
	public DataValue toDataValue();
}
