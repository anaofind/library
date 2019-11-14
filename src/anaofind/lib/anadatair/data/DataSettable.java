package anaofind.lib.anadatair.data;

import java.util.*;

/**
 * data
 * @author anaofind
 */
public class DataSettable implements DataValue{

	/**
	 * the index of array
	 */
	private int index = 0;
	
	/**
	 * all attributes used
	 */
	private Set<String> attributes = new HashSet<String>();

	/**
	 * the object of object
	 */
	private Map<String, DataValue> objectData = new HashMap<String, DataValue>();

	/**
	 * the array of object
	 */
	private Map<Integer, DataValue> arrayData = new HashMap<Integer, DataValue>();

	/**
	 * the array of string
	 */
	private Map<String, String> objectString = new HashMap<String, String>(); 

	/**
	 * the object of string
	 */
	private Map<Integer, String> arrayString = new HashMap<Integer, String>();

	/**
	 * the object of integer
	 */
	private Map<String, Integer> objectInteger = new HashMap<String, Integer>(); 

	/**
	 * the array of integer
	 */
	private Map<Integer, Integer> arrayInteger = new HashMap<Integer, Integer>();

	/**
	 * the object of double
	 */
	private Map<String, Double> objectDouble = new HashMap<String, Double>();

	/**
	 * the array of double
	 */
	private Map<Integer, Double> arrayDouble = new HashMap<Integer, Double>();

	/**
	 * the object of boolean
	 */
	private Map<String, Boolean> objectBoolean = new HashMap<String, Boolean>();

	/**
	 * the array of boolean
	 */
	private Map<Integer, Boolean> arrayBoolean = new HashMap<Integer, Boolean>();

	/**
	 * construct
	 */
	public DataSettable() {
	}
	
	/**
	 * construct
	 * @param booleans array of booleans
	 */
	public DataSettable(boolean...booleans) {
		for (boolean bool : booleans) {
			this.addBoolean(bool);
		}
	}
	
	/**
	 * construct
	 * @param integers array of integers
	 */
	public DataSettable(int...integers) {
		for (int i : integers) {
			this.addInteger(i);
		}
	}
	
	/**
	 * construct
	 * @param doubles array of doubles
	 */
	public DataSettable(double...doubles) {
		for (double d : doubles) {
			this.addDouble(d);
		}
	}

	/**
	 * construct
	 * @param strings array of strings
	 */
	public DataSettable(String...strings) {
		for (String s : strings) {
			this.addString(s);
		}
	}
	
	/**
	 * construct
	 * @param datas array of data value
	 */
	public DataSettable(DataValue...datas) {
		for (DataValue data : datas) {
			this.addData(data);
		}
	}
	
	/**
	 * to gettable
	 * @return the gettable equivalent
	 */
	public DataValue toGettable() {
		return new DataGettable(this);
	}

	@Override
	public void optimize() {
		for (int i : this.arrayData.keySet()) {
			DataValue data = this.arrayData.get(i);
			data.optimize();
			int size = data.size();
			if (size == 1) {
				String type = data.getType();
				switch (type) {
				case "string" :
					this.arrayString.put(i, data.getString(0));
					this.arrayData.remove(i);
					break;
				case "integer" :
					this.arrayInteger.put(i, data.getInteger(0));
					this.arrayDouble.put(i, data.getDouble(0));
					this.arrayData.remove(i);
					break;
				case "double" :
					this.arrayDouble.put(i, data.getDouble(0));
					this.arrayData.remove(i);
					break;
				case "boolean" :
					this.arrayBoolean.put(i, data.getBoolean(0));
					this.arrayData.remove(i);
					break;
				}
			}
		}
		for (String attribute : this.objectData.keySet()) {
			DataValue data = this.objectData.get(attribute);
			data.optimize();
			int size = data.size();
			if (size == 1) {
				String type = data.getType();
				switch (type) {
				case "string" :
					this.objectString.put(attribute, data.getString(0));
					this.objectData.remove(attribute);
					break;
				case "integer" :
					this.objectInteger.put(attribute, data.getInteger(0));
					this.objectDouble.put(attribute, data.getDouble(0));
					this.objectData.remove(attribute);
					break;
				case "double" :
					this.objectDouble.put(attribute, data.getDouble(0));
					this.objectData.remove(attribute);
					break;
				case "boolean" :
					this.objectBoolean.put(attribute, data.getBoolean(0));
					this.objectData.remove(attribute);
					break;
				}
			}
		}
	}

	/**
	 * add data
	 * @param attribute the attribute
	 * @param data the data
	 */
	public void addData(String attribute, DataValue data) {
		Objects.requireNonNull(attribute);
		Objects.requireNonNull(data);
		if (! this.attributes.contains(attribute)) {
			this.objectData.put(attribute, data);
			this.optimize();
			this.attributes.add(attribute);	
		}
	}

	/**
	 * add data
	 * @param data the data
	 */
	public void addData(DataValue data) {
		Objects.requireNonNull(data);
		this.arrayData.put(this.index, data);
		this.optimize();
		this.index ++;
	}

	/**
	 * add string 
	 * @param attribute the attribute
	 * @param value the value
	 */
	public void addString(String attribute, String value) {
		Objects.requireNonNull(attribute);
		Objects.requireNonNull(value);
		if (! this.attributes.contains(attribute)) {
			this.objectString.put(attribute, value);
			this.attributes.add(attribute);	
		}
	}

	/**
	 * add string
	 * @param data the string
	 */
	public void addString(String value) {
		Objects.requireNonNull(value);
		this.arrayString.put(this.index, value);
		this.index++;
	}

	/**
	 * add integer
	 * @param attribute the attribute
	 * @param value the value
	 */
	public void addInteger(String attribute, int value) {
		Objects.requireNonNull(attribute);
		if (! this.attributes.contains(attribute)) {
			this.objectInteger.put(attribute, value);
			this.objectDouble.put(attribute, (double)value);
			this.attributes.add(attribute);	
		}
	}

	/**
	 * add integer
	 * @param data the string
	 */
	public void addInteger(int value) {
		this.arrayInteger.put(this.index, value);
		this.arrayDouble.put(this.index, (double)value);
		this.index++;
	}

	/**
	 * add double 
	 * @param attribute the attribute
	 * @param value the value
	 */
	public void addDouble(String attribute, double value) {
		Objects.requireNonNull(attribute);
		if (! this.attributes.contains(attribute)) {
			this.objectDouble.put(attribute, value);	
			this.attributes.add(attribute);	
		}
	}

	/**
	 * add double
	 * @param data the string
	 */
	public void addDouble(double value) {
		this.arrayDouble.put(this.index, value);
		this.index++;
	}

	/**
	 * add boolean 
	 * @param attribute the attribute
	 * @param value the value
	 */
	public void addBoolean(String attribute, boolean value) {
		Objects.requireNonNull(attribute);
		if (! this.attributes.contains(attribute)) {
			this.objectBoolean.put(attribute, value);	
			this.attributes.add(attribute);	
		}
	}

	/**
	 * add boolean
	 * @param data the string
	 */
	public void addBoolean(boolean value) {
		this.arrayBoolean.put(index, value);
		this.index++;
	}

	@Override
	public DataValue getData(String attribute) {
		return this.objectData.get(attribute);
	}

	@Override
	public DataValue getData(int index) {
		return this.arrayData.get(index);
	}

	@Override
	public DataValue getData() {
		if (this.size() == 1 && this.arrayData.size() == 1) {
			return this.arrayData.get(0);
		}
		return null;
	}

	@Override
	public String getString(String attribute) {
		return this.objectString.get(attribute);
	}

	@Override
	public String getString(int index) {
		return this.arrayString.get(index);
	}

	@Override
	public String getString() {
		if (this.size() == 1 && this.arrayString.size() == 1) {
			return this.arrayString.get(0);
		}
		return null;
	}

	@Override
	public Integer getInteger(String attribute) {
		return this.objectInteger.get(attribute);
	}

	@Override
	public Integer getInteger(int index) {
		return this.arrayInteger.get(index);
	}

	@Override
	public Integer getInteger() {
		if (this.size() == 1 && this.arrayInteger.size() == 1) {
			return this.arrayInteger.get(0);
		}
		return null;
	}

	@Override
	public Double getDouble(String attribute) {
		return this.objectDouble.get(attribute);
	}

	@Override
	public Double getDouble(int index) {
		return this.arrayDouble.get(index);
	}

	@Override
	public Double getDouble() {
		if (this.size() == 1 && this.arrayDouble.size() == 1) {
			return this.arrayDouble.get(0);
		}
		return null;
	}

	@Override
	public Boolean getBoolean(String attribute) {
		return this.objectBoolean.get(attribute);
	}

	@Override
	public Boolean getBoolean(int index) {
		return this.arrayBoolean.get(index);
	}

	@Override
	public Boolean getBoolean() {
		if (this.size() == 1 && this.arrayBoolean.size() == 1) {
			return this.arrayBoolean.get(0);
		}
		return null;
	}

	@Override
	public int size() {
		return this.index + this.attributes.size();
	}

	@Override
	public String getType() {
		int size = this.size();
		if (size == 0) {
			return "null";
		}
		
		if (this.attributes.size() > 0) {
			return "object";
		}
		
		if (size == 1) {
			if (this.arrayBoolean.size() > 0) {
				return "boolean";
			}
			if (this.arrayString.size() > 0) {
				return "string";
			}
			if (this.arrayInteger.size() > 0) {
				return "integer";
			}
			if (this.arrayDouble.size() > 0) {
				return "double";
			}
		}
		return "array";
	}
	
	@Override
	public String toString() {
		String s = this.getType() + " { \n";
		for (String attribute : this.objectData.keySet()) {
			s += "\"" + attribute + "\"" + " -> " + this.objectData.get(attribute).toString();
			s += "\n";
		}
		for (String attribute : this.objectString.keySet()) {
			s += "\"" + attribute + "\"" + " -> " + this.objectString.get(attribute);
			s += "\n";
		}
		for (String attribute : this.objectDouble.keySet()) {
			s += "\"" + attribute + "\"" + " -> " + this.objectDouble.get(attribute);
			s += "\n";
		}
		for (String attribute : this.objectInteger.keySet()) {
			s += "\"" + attribute + "\"" + " -> " + this.objectInteger.get(attribute);
			s += "\n";
		}
		for (String attribute : this.objectBoolean.keySet()) {
			s += "\"" + attribute + "\"" + " -> " + this.objectBoolean.get(attribute);
			s += "\n";
		}
		for (int index : this.arrayData.keySet()) {
			s += "[" + index + "]" + " -> " + this.arrayData.get(index).toString();
			s += "\n";
		}
		for (int index : this.arrayString.keySet()) {
			s += "[" + index + "]" + " -> " + this.arrayString.get(index);
			s += "\n";
		}
		for (int index : this.arrayDouble.keySet()) {
			s += "[" + index + "]" + " -> " + this.arrayDouble.get(index);
			s += "\n";
		}
		for (int index : this.arrayInteger.keySet()) {
			s += "[" + index + "]" + " -> " + this.arrayInteger.get(index);
			s += "\n";
		}
		for (int index : this.arrayBoolean.keySet()) {
			s += "[" + index + "]" + " -> " + this.arrayBoolean.get(index);
			s += "\n";
		}
		s += "}";
		return s;
	}
}