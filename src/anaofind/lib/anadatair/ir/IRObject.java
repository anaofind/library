package anaofind.lib.anadatair.ir;

import java.util.*;

import anaofind.lib.anadatair.AIRSettable;
import anaofind.lib.anadatair.AIRValue;
import anaofind.lib.anadatair.visitor.VisitorIR;

/**
 * ir value object
 * @author anaofind
 */
public class IRObject implements IRValue{

	/**
	 * values
	 */
	private Map<String, IRValue> values = new HashMap<String, IRValue>();
	
	/**
	 * add value
	 * @param attributeName the name of attribute
	 * @param value the value
	 */
	public void addAttribute(String attributeName, IRValue value) {
		Objects.requireNonNull(attributeName);
		Objects.requireNonNull(value);
		this.values.put(attributeName, value);
	}

	/**
	 * getter values
	 * @return the values
	 */
	public Map<String, IRValue> getValues() {
		return Collections.unmodifiableMap(this.values);
	}
	
	@Override
	public void accept(VisitorIR visitor) {
		visitor.visitObjectIR(this);
	}

	@Override
	public AIRValue toAIR() {
		AIRSettable air = new AIRSettable();
		for (String attribute : this.values.keySet()) {
			air.addData(attribute, this.values.get(attribute).toAIR());
		}
		return air.toGettable();
	}
	
}
