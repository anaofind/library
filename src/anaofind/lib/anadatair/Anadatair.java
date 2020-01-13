package anaofind.lib.anadatair;

/**
 * air value
 * @author anaofind
 */
public interface Anadatair {

	/**
	 * optimize
	 */
	public void optimize();
	
	/**
	 * constains attribute
	 * @param attribute the attribute
	 * @return boolean : true if contains attribute | false else
	 */
	public boolean contains(String attribute);
		
	/**
	 * contains index
	 * @param index the index
	 * @return boolean : true if contains index | false else
	 */
	public boolean contains(int index);
	
	/**
	 * contains
	 * @param attribute the attribute
	 * @param type the type
	 * @return boolean : true if contains | false else
	 */
	public boolean contains(String attribute, String type);
	
	/**
	 * contains
	 * @param index the index
	 * @param type the type
	 * @return boolean : true if contains | false else
	 */
	public boolean contains(int index, String type);
	
	/**
	 * get data value
	 * @param attribute the attribute
 	 * @return the data
	 */
	public Anadatair getData(String attribute);
	
	/**
	 * get data value
	 * @param index the index
	 * @return the data
	 */
	public Anadatair getData(int index);
	
	/**
	 * get data
	 * @return the data if only one data
	 */
	public Anadatair getData();
	
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
	public Long getInteger(String attribute);
	
	/**
	 * get integer value 
	 * @param index the index
	 * @return the integer
	 */
	public Long getInteger(int index);

	/**
	 * get integer
	 * @return the integer if only one integer
	 */
	public Long getInteger();

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
