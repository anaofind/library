package anaofind.lib.anadatair.printer;

import anaofind.lib.anadatair.json.*;
import anaofind.lib.anadatair.visitor.VisitorJSON;

import java.util.*;

/**
 * pretty printer json
 * @author anaofind
 */
public class PrettyPrinterJSON extends PrettyPrinter implements VisitorJSON{
	
	/**
	 * construct
	 */
	public PrettyPrinterJSON() {
		super('\t');
	}
	
	@Override
	public void visitArrayJSON(JsonArray jsonArray) {
		List<JsonValue> values = jsonArray.getValues();
		if (values.size() > 0) {
			this.addText(0, "[", true);
			this.marginSizeBase ++;
			for (int i = 0; i<values.size(); i++) {
				values.get(i).accept(this);
				if (i < values.size() - 1) {
					this.addText(0, ",", true);
				} else {
					this.jumpLine(1);
				}
			}
			this.marginSizeBase --;
			this.addText(0, "]");
		} else {
			this.addText(0, "[]");
		}
	}

	@Override
	public void visitObjectJSON(JsonObject jsonObject) {
		Map<String, JsonValue> values = jsonObject.getValues();
		if (values.size() > 0) {
			this.addText(0, "{", true);
			int nbAttributeReaded = 0;
			this.marginSizeBase ++;
			for (String attribute : values.keySet()) {
				this.addText(0, "\"" + attribute + "\" : ");
				values.get(attribute).accept(this);
				if (nbAttributeReaded < values.size() -1 ) {
					this.addText(0, ",", true);
				} else {
					jumpLine(1);
				}
				nbAttributeReaded ++;
			}
			this.marginSizeBase --;
			this.addText(0, "}");
		} else {
			this.addText(0, "{}");
		}
	}

	@Override
	public void visitNumberJSON(JsonNumber jsonNumber) {
		if (jsonNumber.getType().equals("integer")) {
			this.addText(0, "" + (int)jsonNumber.getValue());
		} else {
			this.addText(0, "" + jsonNumber.getValue());	
		}
	}

	@Override
	public void visitBooleanJSON(JsonBoolean jsonBoolean) {
		this.addText(0, "" + jsonBoolean.getValue());
	}

	@Override
	public void visitStringJSON(JsonString jsonString) {
		this.addText(0, "\"" + jsonString.getValue() + "\"");
	}

	@Override
	public void visitNullJSON(JsonNull jsonNull) {
		this.addText(0, "null");
	}

	public static void main(String[] args) {
		JsonObject object = new JsonObject();
		JsonArray array = new JsonArray(new JsonString("coucou"), new JsonNumber(1000), new JsonBoolean(true));
		object.addAttribute("Array", array);
		object.addAttribute("Average", new JsonNumber(5.25));
		PrettyPrinterJSON pp = new PrettyPrinterJSON();
		object.accept(pp);
		pp.print();
	}
	
	
}
