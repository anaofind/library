package anaofind.lib.anadatair.ir;

import anaofind.lib.anadatair.AnadatairSettable;
import anaofind.lib.anadatair.Anadatair;
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
	public Anadatair toAnadatair() {
		AnadatairSettable air = new AnadatairSettable();
		return air.toGettable();
	}

}
