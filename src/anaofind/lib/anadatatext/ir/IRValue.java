package anaofind.lib.anadatatext.ir;

import anaofind.lib.anadatext.data.DataValue;

/**
 * ir value 
 * @author anaofind
 */
public interface IRValue extends IRConverter{
	
	/**
	 * to data value
	 * @return the data value equivalent
	 */
	public DataValue toDataValue();
	
}
