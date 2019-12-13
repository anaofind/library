package anaofind.lib.anadatair.ir;

import anaofind.lib.anadatair.Anadatair;
import anaofind.lib.anadatair.visitor.VisitorIR;

/**
 * the ir value
 * @author anaofind
 */
public interface IRValue {
	
	/**
	 * accept visitor ir
	 * @param visitor the visitor ir
	 */
	public void accept(VisitorIR visitor);
	
	/**
	 * to air value
	 * @return the air value equivalent
	 */
	public Anadatair toAnadatair();
	
}
