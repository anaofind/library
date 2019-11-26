package anaofind.lib.anadatair.translater;

import anaofind.lib.anadatair.json.*;
import anaofind.lib.anadatair.ir.*;
import anaofind.lib.anadatair.visitor.VisitorJSON;

import java.util.*;

/**
 * translater json to ir
 * @author anaofind
 */
public class TranslaterJSONToIR implements VisitorJSON{

	/**
	 * the ir value
	 */
	private IRValue value;
	
	/**
	 * construct
	 * @param valueJSON the json value
	 */
	public TranslaterJSONToIR(JsonValue valueJSON) {
		Objects.requireNonNull(valueJSON);
		valueJSON.accept(this);
		Objects.requireNonNull(this.value);
	}
	
	/**
	 * translate json value
	 * @param valueJSON the json value
	 * @return the ir value equivalent
	 */
	private IRValue translateValue(JsonValue valueJSON) {
		TranslaterJSONToIR translater = new TranslaterJSONToIR(valueJSON);
		return translater.getValue();
	}
	
	/**
	 * getter value
	 * @return the ir value
	 */
	public IRValue getValue() {
		return this.value;
	}
	
	@Override
	public void visitArrayJSON(JsonArray jsonArray) {
		List<JsonValue> valuesJSON = jsonArray.getValues();
		int size = valuesJSON.size();
		IRValue[] valuesIR = new IRValue[size];
		for (int i = 0; i<size; i++) {
			valuesIR[i] = this.translateValue(valuesJSON.get(i));
		}
		this.value = new IRArray(valuesIR);
	}

	@Override
	public void visitObjectJSON(JsonObject jsonObject) {
		IRObject objectIR = new IRObject();
		Map<String, JsonValue> valuesJSON = jsonObject.getValues();
		for (String attribute : valuesJSON.keySet()) {
			objectIR.addAttribute(attribute, this.translateValue(valuesJSON.get(attribute)));
		}
		this.value = objectIR;
	}

	@Override
	public void visitNumberJSON(JsonNumber jsonNumber) {
		if (jsonNumber.getType().equals("integer")) {
			this.value = new IRInteger((int)jsonNumber.getValue());
		} else {
			this.value = new IRDouble(jsonNumber.getValue());
		}
	}

	@Override
	public void visitBooleanJSON(JsonBoolean jsonBoolean) {
		this.value = new IRBoolean(jsonBoolean.getValue());
	}

	@Override
	public void visitStringJSON(JsonString jsonString) {
		this.value = new IRString(jsonString.getValue());
	}

	@Override
	public void visitNullJSON(JsonNull jsonNull) {
		this.value = new IRNull();
	}
	
}
