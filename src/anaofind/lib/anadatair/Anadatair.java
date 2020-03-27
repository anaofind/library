package anaofind.lib.anadatair;

import anaofind.lib.anadatair.json.*;

/**
 * air value
 * @author anaofind
 */
public interface Anadatair {

	/**
	 * constains attribute
	 * @param attribute the attribute
	 * @return boolean : true if contains attribute | false else
	 */
	default boolean contains(String attribute) {
		return this.getData(attribute) != null;
	}

	/**
	 * contains index
	 * @param index the index
	 * @return boolean : true if contains index | false else
	 */
	default boolean contains(int index) {
		return this.getData(index) != null;
	}

	/**
	 * contains
	 * @param attribute the attribute
	 * @param type the type
	 * @return boolean : true if contains | false else
	 */
	default boolean contains(String attribute, String type) {
		Anadatair data = this.getData(attribute);
		return (data != null && data.getType().equals(type));
	}

	/**
	 * contains
	 * @param index the index
	 * @param type the type
	 * @return boolean : true if contains | false else
	 */
	default boolean contains(int index, String type) {
		Anadatair data = this.getData(index);
		return (data != null && data.getType().equals(type));
	}

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
	default Anadatair getData() {
		return this.getData(0);
	}

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
	default String getString() {
		return this.getString(0);
	}

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
	default Long getInteger() {
		return this.getInteger(0);
	}

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
	default Double getDouble() {
		return this.getDouble(0);
	}

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
	default Boolean getBoolean() {
		return this.getBoolean(0);
	}

	/**
	 * get size
	 */
	public int size();

	/**
	 * get type of data
	 * @return the type of data
	 */
	public String getType();

	/**
	 * equals method
	 * @param other the other anadatair
	 */
	public boolean equals(Anadatair other);
	
	/**
	 * to json
	 * @return the json value equivalent
	 */
	public JsonValue toJson();

}
