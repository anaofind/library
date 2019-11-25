package anaofind.lib.anadatair.printer;

import java.util.*;

import anaofind.lib.anadatair.ir.*;
import anaofind.lib.anadatair.visitor.VisitorIR;

/**
 * pretty printer ir
 * @author anaofind
 */
public class PrettyPrinterIR extends PrettyPrinter implements VisitorIR{
	
	/**
	 * constructor
	 * @param marginSize the size of margin
	 */
	public PrettyPrinterIR() {
		super('\t');
	}
	
	@Override
	public void visitArrayIR(IRArray irArray) {
		String start = "(array:";
		String end = ")";
		
		List<IRValue> values = irArray.getValues();
		if (values.size() > 0) {
			this.addText(0, start);
			this.marginSizeBase ++;
			for (IRValue value: values) {
				value.accept(this);
			}
			this.marginSizeBase--;
			this.addText(0, end);
		} else {
			this.addText(0, start + end);
		}
	}

	@Override
	public void visitObjectIR(IRObject irObject) {
		String start = "(object: ";
		String end = ")";
		
		Map<String, IRValue> values = irObject.getValues();
		if (values.size() > 0) {
			this.addText(0, start);
			this.marginSizeBase ++;
			for (String attribute: values.keySet()) {
				IRValue value = values.get(attribute);
				this.addText(0, "<" + attribute + "> "); 
				value.accept(this);
			}
			this.marginSizeBase --;
			this.addText(0, end);
		} else {
			this.addText(0, start + end);
		}
	}

	@Override
	public void visitDoubleIR(IRDouble irDouble) {
		this.addText(0, "(double:" + irDouble.getValue() + ")");
	}

	@Override
	public void visitIntegerIR(IRInteger irInteger) {
		this.addText(0, "(integer:" + irInteger.getValue() + ")");
	}

	@Override
	public void visitBooleanIR(IRBoolean irBoolean) {
		this.addText(0, "(boolean:" + irBoolean.getValue() + ")");
	}

	@Override
	public void visitStringIR(IRString irString) {
		this.addText(0, "(string:" +  irString.getValue() + ")");
	}

	@Override
	public void visitNullIR(IRNull irNull) {
		this.addText(0, "(null:)");
	}
	
}
