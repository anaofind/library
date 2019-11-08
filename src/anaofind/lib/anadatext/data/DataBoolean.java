package anaofind.lib.anadatext.data;

/**
 * data boolean
 * @author anaofind
 *
 */
public class DataBoolean implements DataValue{

	/**
	 * the value
	 */
	private boolean[] value = new boolean[1];
	
	/**
	 * construct
	 * @param value the value
	 */
	public DataBoolean(boolean value) {
		this.value[0] = value;
	}

	@Override
	public boolean[] getArrayBoolean() {
		return this.value;
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public String getContainsString() {
		return "Boolean : " + this.value[0];
	}

}
