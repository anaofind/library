package anaofind.lib.anadatair.ir;

import anaofind.lib.anadatair.AIRSettable;
import anaofind.lib.anadatair.AIRValue;
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

	@Override
	public AIRValue toAIR() {
		AIRSettable air = new AIRSettable();
		return air.toGettable();
	}

}
