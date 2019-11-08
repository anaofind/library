package anaofind.lib.anadatext.data;

import java.util.Objects;

import java.util.*;

/**
 * data array
 * @author anaofind
 */
public class DataArray implements DataValue{

	/**
	 * the values
	 */
	private DataValue[] values;
	
	/**
	 * the values object
	 */
	private List<DataObject> valuesObject = new ArrayList<DataObject>();
	
	/**
	 * the values array
	 */
	private List<DataArray> valuesArray = new ArrayList<DataArray>();
	
	/**
	 * the values integer
	 */
	private List<Integer> valuesInteger = new ArrayList<Integer>();
	
	/**
	 * the valuesDouble
	 */
	private List<Double> valuesDouble = new ArrayList<Double>();
	
	/**
	 * the values string
	 */
	private List<String> valuesString = new ArrayList<String>();
	
	/**
	 * the values boolean
	 */
	private List<Boolean> valuesBoolean = new ArrayList<Boolean>();
		
	/**
	 * construct
	 * @param dataValues the data values
	 */
	public DataArray(DataValue...dataValues) {
		Objects.requireNonNull(dataValues);
		this.values = dataValues;
		for (DataValue value : dataValues) {
			for (DataArray v : value.getArrayArray()) {
				this.valuesArray.add(v);	
			}
			for (DataObject v : value.getArrayObject()) {
				this.valuesObject.add(v);	
			}
			for (int v : value.getArrayInteger()) {
				this.valuesInteger.add(v);	
			}
			for (double v : value.getArrayDouble()) {
				this.valuesDouble.add(v);	
			}
			for (boolean v : value.getArrayBoolean()) {
				this.valuesBoolean.add(v);	
			}
		}
	}
	

	@Override
	public DataObject[] getArrayObject() {
		return this.valuesObject.toArray(new DataObject[this.valuesObject.size()]);
	}

	@Override
	public DataArray[] getArrayArray() {
		return this.valuesArray.toArray(new DataArray[this.valuesArray.size()]);
	}
	
	@Override
	public int[] getArrayInteger() {
		int[] values = new int[this.valuesInteger.size()];
		for (int i = 0; i<values.length; i++) {
			values[0] = this.valuesInteger.get(i);
		}
		return values;
	}

	@Override
	public String[] getArrayString() {
		return this.valuesString.toArray(new String[this.valuesString.size()]);
	}

	@Override
	public boolean[] getArrayBoolean() {
		boolean[] values = new boolean[this.valuesBoolean.size()];
		for (int i = 0; i<values.length; i++) {
			values[0] = this.valuesBoolean.get(i);
		}
		return values;
	}

	@Override
	public double[] getArrayDouble() {
		double[] values = new double[this.valuesDouble.size()];
		for (int i = 0; i<values.length; i++) {
			values[0] = this.valuesDouble.get(i);
		}
		return values;
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return this.values.length == 0;
	}


	@Override
	public String getContainsString() {
		String description = "Array : {";
		for (DataValue value : this.values) {
			description += "\n    " + value.getContainsString();
		}
		description += "}\n";
		return description;
	}	
}
