package anaofind.lib.anadatext.data;

/**
 * value data
 * @author anaofind
 */
public interface DataValue {
	
	/**
	 * get data value (for object) 
	 * @param attributeName the attribute name 
	 * @return the data value
	 */
	default DataValue getDataValue(String attributeName) {
		return null;
	}

	/**
	 * get array object
	 * @return the array object
	 */
	default DataObject[] getArrayObject() {
		return new DataObject[0];
	}
	
	/**
	 * get array array
	 * @return the array array
	 */
	default DataArray[] getArrayArray() {
		return new DataArray[0];
	}
	
	/**
	 * get array int (for array)
	 * @return the array of int
	 */
	default int[] getArrayInteger() {
		return new int[0];
	}
	
	/**
	 * get array string
	 * @return the array string
	 */ 
	default String[] getArrayString() {
		return new String[0];
	}
	
	/**
	 * get array boolean
	 * @return the array boolean
	 */
	default boolean[] getArrayBoolean() {
		return new boolean[0];
	}
	
	/**
	 * get array double
	 * @return the array double
	 */
	default double[] getArrayDouble() {
		return new double[0];
	}
	
	/**
	 * is null
	 * @return boolean : true if is null | false else
	 */
	public boolean isNull();
	
	/**
	 * is empty
	 * @return boolean : true if is empty | false else
	 */
	public boolean isEmpty();
	
	/**
	 * get description
	 * @return the descritpion
	 */
	public String getContainsString();
}
