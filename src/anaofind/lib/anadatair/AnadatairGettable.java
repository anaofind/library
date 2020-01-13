package anaofind.lib.anadatair;

/**
 * anadatair value gettable (not settable)
 * @author anaofind
 */
public class AnadatairGettable implements Anadatair{

	/**
	 * the settable data
	 */
	private AnadatairSettable data;
	
	/**
	 * construct 
	 * @param data the settable data
	 */
	public AnadatairGettable (AnadatairSettable data) {
		this.data = data;
	}
	
	@Override
	public Anadatair getData(String attribute) {
		return this.data.getData(attribute);
	}

	@Override
	public Anadatair getData(int index) {
		return this.data.getData(index);
	}

	@Override
	public Anadatair getData() {
		return this.data.getData();
	}

	@Override
	public String getString(String attribute) {
		return this.data.getString(attribute);
	}

	@Override
	public String getString(int index) {
		return this.data.getString(index);
	}

	@Override
	public String getString() {
		return this.data.getString();
	}

	@Override
	public Long getInteger(String attribute) {
		return this.data.getInteger(attribute);
	}

	@Override
	public Long getInteger(int index) {
		return this.data.getInteger(index);
	}

	@Override
	public Long getInteger() {
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
		return this.data.getDouble();
	}

	@Override
	public Boolean getBoolean(String attribute) {
		return this.data.getBoolean(attribute);
	}

	@Override
	public Boolean getBoolean(int index) {
		return this.data.getBoolean(index);
	}

	@Override
	public Boolean getBoolean() {
		return this.data.getBoolean();
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
	
	@Override
	public String toString() {
		return this.data.toString();
	}
	
	@Override
	public boolean equals(Object ob) {
		if (ob instanceof Anadatair) {
			Anadatair dv = (Anadatair) ob;
			return (dv.equals(this.data));
		}
		return false;
	}

	@Override
	public boolean contains(String attribute) {
		return this.data.contains(attribute);
	}

	@Override
	public boolean contains(int index) {
		return this.data.contains(index);
	}

	@Override
	public boolean contains(String attribute, String type) {
		return this.data.contains(attribute, type);
	}

	@Override
	public boolean contains(int index, String type) {
		return this.data.contains(index, type);
	}
}
