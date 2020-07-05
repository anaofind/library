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
	 * get value of data
	 * @return the value of data
	 */
	public Object getValue();
		
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
