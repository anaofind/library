package anaofind.lib.anadatair.data;

/**
 * data value
 * @author anaofind
 */
public interface DataValue {

	/**
	 * optimize
	 */
	public void optimize();
	
	/**
	 * get data value
	 * @param attribute the attribute
 	 * @return the data
	 */
	public DataValue getData(String attribute);
	
	/**
	 * get data value
	 * @param index the index
	 * @return the data
	 */
	public DataValue getData(int index);
	
	/**
	 * get data
	 * @return the data if only one data
	 */
	public DataValue getData();
	
	/**
	 * get string value
	 * @param attribute the attribute
	 * @return the string
	 */
	public String getString(String attribute);
	
	/**
	 * get string value 
	 * @param index the index
	 * @return the string
	 */
	public String getString(int index);

	/**
	 * get string
	 * @return the string if only one string
	 */
	public String getString();
	
	/**
	 * get integer value
	 * @param attribute the attribute
	 * @return the integer
	 */
	public Integer getInteger(String attribute);
	
	/**
	 * get integer value 
	 * @param index the index
	 * @return the integer
	 */
	public Integer getInteger(int index);

	/**
	 * get integer
	 * @return the integer if only one integer
	 */
	public Integer getInteger();

	/**
	 * get double value
	 * @param attribute the attribute
	 * @return the double
	 */
	public Double getDouble(String attribute);
	
	/**
	 * get double value 
	 * @param index the index
	 * @return the double
	 */
	public Double getDouble(int index);
	
	/**
	 * get double value
	 * @return the double if only one double
	 */
	public Double getDouble();
	
	/**
	 * get boolean value
	 * @param attribute the attribute
	 * @return the boolean
	 */
	public Boolean getBoolean(String attribute);
	
	/**
	 * get boolean value 
	 * @param index the index
	 * @return the boolean
	 */
	public Boolean getBoolean(int index);

	/**
	 * get boolean
	 * @return the boolean if only one boolean
	 */
	public Boolean getBoolean();
	
	/**
	 * get size
	 */
	public int size();
	
	/**
	 * get type of data
	 * @return the type of data
	 */
	public String getType();
}
