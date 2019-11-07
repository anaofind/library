package anaofind.lib.anadatatext.ir;

/**
 * the ir value boolean
 * @author anaofind
 */
public class IRBoolean implements IRValue{

	/**
	 * the value
	 */
	private boolean value;
	
	/**
	 * construct 
	 * @param value the value
	 */
	public IRBoolean(boolean value) {
		this.value = value;
	}
	
	@Override
	public String toIR() {
		return "(boolean:" + this.value + ")";
	}

}