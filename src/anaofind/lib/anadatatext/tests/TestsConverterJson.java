package anaofind.lib.anadatatext.tests;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

import anaofind.lib.anadatatext.json.*;

/**
 * tests json value on get string
 * @author anaofind
 *
 */
public class TestsConverterJson {

	@Test
	public void testBase() {
		JsonConverter value = new JsonNumber(1);
		assertEquals("1", value.toTextJson());
		value = new JsonNumber(-1);
		assertEquals("-1", value.toTextJson());
		value = new JsonNumber(+1);
		assertEquals("1", value.toTextJson());
		value = new JsonNumber(5.1);
		assertEquals("5.1", value.toTextJson());
		value = new JsonNumber(-5.1);
		assertEquals("-5.1", value.toTextJson());
		value = new JsonNumber(+5.1);
		assertEquals("5.1", value.toTextJson());
		value = new JsonNumber(5.0);
		assertEquals("5.0", value.toTextJson());
		
		value = new JsonString("coucou");
		assertEquals("\"coucou\"", value.toTextJson());
		value = new JsonString("cou cou");
		assertEquals("\"cou cou\"", value.toTextJson());
		value = new JsonString("");
		assertEquals("\"\"", value.toTextJson());
		value = new JsonString("10");
		assertEquals("\"10\"", value.toTextJson());
		
		value = new JsonBoolean(true);
		assertEquals("true", value.toTextJson());
		value = new JsonBoolean(false);
		assertEquals("false", value.toTextJson());
		
		value = new JsonNull();
		assertEquals("null", value.toTextJson());
	}
	
	@Test
	public void testObject() {
		JsonConverter v1 = new JsonNumber(10);
		JsonConverter v2 = new JsonString("text");
		JsonConverter v3 = new JsonBoolean(false);
		JsonConverter v4 = new JsonNull();
		
		JsonObject ob = new JsonObject();
		ob.add("a2", v2);
		
		JsonObject object = new JsonObject();
		assertEquals("{}", object.toTextJson());
	
		object = new JsonObject();
		object.add("a1", v1);
		object.add("a2", v2);
		object.add("a3", v3);
		object.add("a4", v4);
		assertEquals("{\"a1\":10,\"a2\":\"text\",\"a3\":false,\"a4\":null}", object.toTextJson());
		
		object = new JsonObject();
		object.add("ob", new JsonObject());
		assertEquals("{\"ob\":{}}", object.toTextJson());
		
		object = new JsonObject();
		object.add("ob", ob);
		assertEquals("{\"ob\":{\"a2\":\"text\"}}", object.toTextJson());	
	}
	
	@Test
	public void testArray() {
		JsonConverter v1 = new JsonNumber(10);
		JsonConverter v2 = new JsonString("text");
		JsonConverter v3 = new JsonBoolean(false);
		JsonConverter v4 = new JsonNull();
		
		JsonConverter ar = new JsonArray(v1, v3);
		
		JsonArray array = new JsonArray();
		assertEquals("[]", array.toTextJson());
		
		array = new JsonArray(new JsonArray());
		assertEquals("[[]]", array.toTextJson());
		
		array = new JsonArray(new JsonArray(), new JsonArray());
		assertEquals("[[],[]]", array.toTextJson());
		
		array = new JsonArray(v1, v2, v3, v4);
		assertEquals("[10,\"text\",false,null]", array.toTextJson());
		
		array = new JsonArray(v2, ar);
		assertEquals("[\"text\",[10,false]]", array.toTextJson());
	}
	
	@Test
	public void testMixed() {
		JsonConverter v1 = new JsonNumber(10);
		JsonConverter v2 = new JsonString("text");
		JsonConverter v3 = new JsonBoolean(false);
		JsonConverter v4 = new JsonNull();
		
		JsonObject object = new JsonObject();
		JsonConverter array = new JsonArray(object);
		assertEquals("[{}]", array.toTextJson());
		
		array = new JsonArray();
		object.add("ar", array);
		assertEquals("{\"ar\":[]}", object.toTextJson());
		
		object = new JsonObject();
		array = new JsonArray(v3, object);
		object.add("a1", v1);
		object.add("a2", v2);
		assertEquals("[false,{\"a1\":10,\"a2\":\"text\"}]", array.toTextJson());
		
		object = new JsonObject();
		array = new JsonArray(v2, v4);
		object.add("a1", v1);
		object.add("ar", array);
		assertEquals("{\"a1\":10,\"ar\":[\"text\",null]}", object.toTextJson());
		
	}
	
}
