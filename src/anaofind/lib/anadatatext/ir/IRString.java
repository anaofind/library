package anaofind.lib.anadatatext.ir;

/**
 * ir value string
 * @author anaofind
 */
public class IRString implements IRValue{

	/**
	 * the value
	 */
	private String value;
	
	/**
	 * construct
	 * @param value the value
	 */
	public IRString(String value) {
		this.value = value;
	}
	
	@Override
	public String toIR() {
		return "(string:" + this.value + ")";
	}

}
