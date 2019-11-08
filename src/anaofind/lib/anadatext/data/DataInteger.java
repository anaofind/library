package anaofind.lib.anadatext.data;

/**
 * data integer
 * @author anaofind
 */
public class DataInteger implements DataValue{

	/**
	 * the value
	 */
	private int[] value = new int[1];
	
	/**
	 * construct
	 * @param value the value
	 */
	public DataInteger(int value) {
		this.value[0] = value;
	}
	
	@Override
	public int[] getArrayInteger() {
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
		return "Integer : " + this.value[0];
	}

}
