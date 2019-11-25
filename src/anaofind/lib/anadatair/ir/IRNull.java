package anaofind.lib.anadatair.ir;

import anaofind.lib.anadatair.visitor.VisitorIR;

/**
 * ir value null
 * @author anaofind
 */
public class IRNull implements IRValue{

	@Override
	public void accept(VisitorIR visitor) {
		visitor.visitNullIR(this);
	}

}
