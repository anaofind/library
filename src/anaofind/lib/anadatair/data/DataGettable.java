package anaofind.lib.anadatair.data;

/**
 * data gettable only (not settable)
 * @author anaofind
 */
public class DataGettable implements DataValue{

	/**
	 * the settable data
	 */
	private DataSettable data;
	
	/**
	 * construct 
	 * @param data the settable data
	 */
	public DataGettable (DataSettable data) {
		this.data = data;
	}
	
	@Override
	public DataValue getData(String attribute) {
		return this.data.getData(attribute);
	}

	@Override
	public DataValue getData(int index) {
		return this.getData().getData(index);
	}

	@Override
	public DataValue getData() {
		return this.getData();
	}

	@Override
	public String getString(String attribute) {
		return this.data.getString(attribute);
	}

	@Override
	public String getString(int index) {
		return this.getString(index);
	}

	@Override
	public String getString() {
		return this.getString();
	}

	@Override
	public Integer getInteger(String attribute) {
		return this.data.getInteger(attribute);
	}

	@Override
	public Integer getInteger(int index) {
		return this.data.getInteger(index);
	}

	@Override
	public Integer getInteger() {
		return this.data.getInteger();
	}

	@Override
	public Double getDouble(String attribute) {
		return this.data.getDouble(attribute);
	}

	@Override
	public Double getDouble(int index) {
		return this.data.getDouble(index);
	}

	@Override
	public Double getDouble() {
		return this.getDouble();
	}

	@Override
	public Boolean getBoolean(String attribute) {
		return this.getBoolean(attribute);
	}

	@Override
	public Boolean getBoolean(int index) {
		return this.getBoolean(index);
	}

	@Override
	public Boolean getBoolean() {
		return this.getBoolean();
	}

	@Override
	public int size() {
		return this.data.size();
	}

	@Override
	public String getType() {
		return this.data.getType();
	}

	@Override
	public void optimize() {
		this.data.optimize();
	}
}
