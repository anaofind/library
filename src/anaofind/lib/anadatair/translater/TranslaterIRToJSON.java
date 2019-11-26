package anaofind.lib.anadatair.translater;

import java.util.Objects;

import anaofind.lib.anadatair.ir.*;
import anaofind.lib.anadatair.json.*;
import anaofind.lib.anadatair.visitor.VisitorIR;

import java.util.*;

/**
 * translater ir to json
 * @author anaofind
 */
public class TranslaterIRToJSON implements VisitorIR{

	/**
	 * the json value
	 */
	private JsonValue value;
	
	/**
	 * construct
	 * @param valueIR the value ir
	 */
	public TranslaterIRToJSON(IRValue valueIR) {
		Objects.requireNonNull(valueIR);
		valueIR.accept(this);
		Objects.requireNonNull(this.value);
	}
	
	/**
	 * getter json value
	 * @return the json value
	 */
	public JsonValue getValue() {
		return this.value;
	}

	/**
	 * translateValue value
	 * @param valueIR
	 * @return the equivalent json value
	 */
	private JsonValue translateValue(IRValue valueIR) {
		TranslaterIRToJSON translater = new TranslaterIRToJSON(valueIR);
		return translater.getValue();
	}
	
	@Override
	public void visitArrayIR(IRArray irArray) {
		List<IRValue> valuesIR = irArray.getValues();
		int size = valuesIR.size();
		JsonValue[] valuesJSON = new JsonValue[size];
		for (int i = 0; i<size; i++) {
			valuesJSON[i] = this.translateValue(valuesIR.get(i));
		}
		this.value = new JsonArray(valuesJSON);
	}

	@Override
	public void visitObjectIR(IRObject irObject) {
		JsonObject object = new JsonObject();
		Map<String, IRValue> values = irObject.getValues();
		for (String attribute : values.keySet()) {
			object.addAttribute(attribute, this.translateValue(values.get(attribute)));
		}
		this.value = object;
	}

	@Override
	public void visitDoubleIR(IRDouble irDouble) {
		this.value = new JsonNumber(irDouble.getValue());
	}

	@Override
	public void visitIntegerIR(IRInteger irInteger) {
		this.value = new JsonNumber(irInteger.getValue());
	}

	@Override
	public void visitBooleanIR(IRBoolean irBoolean) {
		this.value = new JsonBoolean(irBoolean.getValue());
	}

	@Override
	public void visitStringIR(IRString irString) {
		this.value = new JsonString(irString.getValue());
	}

	@Override
	public void visitNullIR(IRNull irNull) {
		this.value = new JsonNull();
	}

}
