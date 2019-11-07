package anaofind.lib.anadatatext.ir;

/**
 * ir value double
 * @author anaofind
 */
public class IRDouble implements IRValue{

	private double value;
	
	/**
	 * construct
	 * @param value the value
	 */
	public IRDouble(double value) {
		this.value = value;
	}
	
	@Override
	public String toIR() {
		return "(double:" + this.value+")";
	}

}
