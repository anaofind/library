package anaofind.lib.anadatair.ir;

import anaofind.lib.anadatair.AnadatairSettable;
import anaofind.lib.anadatair.Anadatair;
import anaofind.lib.anadatair.visitor.VisitorIR;

import java.util.*;

/**
 * ir value array
 * @author anaofind
 */
public class IRArray implements IRValue{

	/**
	 * values
	 */
	private List<IRValue> values = new ArrayList<IRValue>();
	
	/**
	 * construct
	 * @param irValues the values
	 */
	public IRArray(IRValue...values) {
		for (IRValue value : values) {
			this.values.add(value);
		}
	}
	
	/**
	 * getter values
	 * @return the values
	 */
	public List<IRValue> getValues() {
		return Collections.unmodifiableList(this.values);
	}

	@Override
	public void accept(VisitorIR visitorIR) {
		visitorIR.visitArrayIR(this);
	}

	@Override
	public Anadatair toAnadatair() {
		AnadatairSettable air = new AnadatairSettable();
		for (IRValue value : this.values) {
			air.addData(value.toAnadatair());
		}
		return air.toGettable();
	}
	
	
}
