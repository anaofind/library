package anaofind.lib.anadatatext.ir;

/**
 * ir value integer
 * @author anaofind
 */
public class IRInteger implements IRValue{

	/**
	 * the value
	 */
	private int value;
	
	/**
	 * construct
	 * @param value the value
	 */
	public IRInteger(int value) {
		this.value = value;
	}
	
	@Override
	public String toIR() {
		return "(integer:" + this.value + ")";
	}

	/**
	 * to array of IRInteger
	 * @param values the values
	 * @return the array of IRInteger
	 */
	public static IRInteger[] toArray(int...values) {
		IRInteger[] array = new IRInteger[values.length];
		for (int i = 0; i<array.length; i++) {
			array[i] = new IRInteger(values[i]);
		}
		return array;
	}
	
}
