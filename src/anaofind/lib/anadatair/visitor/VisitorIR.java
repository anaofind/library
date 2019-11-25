package anaofind.lib.anadatair.visitor;

import anaofind.lib.anadatair.ir.*;

/**
 * visitor ir
 * @author anaofind
 *
 */
public interface VisitorIR extends Visitor{

	/**
	 * visit array ir
	 * @param array the array ir
	 */
	public void visitArrayIR(IRArray irArray);
	
	/**
	 * visit object ir
	 * @param object the object ir
	 */
	public void visitObjectIR(IRObject irObject);
	
	/**
	 * visit double ir
	 * @param irDouble the double ir
	 */
	public void visitDoubleIR(IRDouble irDouble);
	
	/**
	 * visit integer ir
	 * @param irInteger the integer ir
	 */
	public void visitIntegerIR(IRInteger irInteger);
	
	/**
	 * visit boolean ir
	 * @param irBoolean the boolean ir
	 */
	public void visitBooleanIR(IRBoolean irBoolean);
	
	/**
	 * visit string ir
	 * @param irString the string ir
	 */
	public void visitStringIR(IRString irString);
	
	/**
	 * visit null ir
	 * @param irNull the null ir
	 */
	public void visitNullIR(IRNull irNull);
}
