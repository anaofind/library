package anaofind.lib.anadatatext.ir;

/**
 * ir value null
 * @author anaofind
 */
public class IRNull implements IRValue{

	@Override
	public String toIR() {
		return "(null:)";
	}

}
