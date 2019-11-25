package anaofind.lib.anadatair.visitor;

import anaofind.lib.anadatair.json.*;

/**
 * visitot json
 * @author anaofind
 */
public interface VisitorJSON {

	/**
	 * visit array json
	 * @param array the array json
	 */
	public void visitArrayJSON(JsonArray jsonArray);
	
	/**
	 * visit object json
	 * @param object the object json
	 */
	public void visitObjectJSON(JsonObject jsonObject);
	
	/**
	 * visit double json
	 * @param irDouble the double json
	 */
	public void visitNumberJSON(JsonNumber jsonNumber);
	
	/**
	 * visit boolean json
	 * @param irBoolean the boolean json
	 */
	public void visitBooleanJSON(JsonBoolean jsonBoolean);
	
	/**
	 * visit string json
	 * @param irString the string json
	 */
	public void visitStringJSON(JsonString jsonString);
	
	/**
	 * visit null json
	 * @param irNull the null json
	 */
	public void visitNullJSON(JsonNull jsonNull);
}
