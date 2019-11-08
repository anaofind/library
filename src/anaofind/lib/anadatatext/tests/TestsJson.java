package anaofind.lib.anadatatext.tests;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;

import anaofind.lib.anadatatext.json.*;

/**
 * tests json value on get string
 * @author anaofind
 *
 */
public class TestsJson {

	
	@Test
	public void testJsonObject() {
		JsonObject object = new JsonObject();
		assertEquals("{}", object.toJson());
		
		JsonObject o1 = new JsonObject();
		object.add("o1", o1);
		assertEquals("{\"o1\":{}}", object.toJson());
		
		JsonObject o2 = new JsonObject();
		object.add("o2", o2);
		assertEquals("{\"o1\":{},\"o2\":{}}", object.toJson());
		
		o1.add("string", new JsonString("s"));
		assertEquals("{\"o1\":{\"string\":\"s\"},\"o2\":{}}", object.toJson());
		
		o1.add("integer", new JsonNumber(2));
		assertEquals("{\"o1\":{\"string\":\"s\",\"integer\":2},\"o2\":{}}", object.toJson());
		
		o2.add("double", new JsonNumber(1.2));
		assertEquals("{\"o1\":{\"string\":\"s\",\"integer\":2},\"o2\":{\"double\":1.2}}", object.toJson());
		
		o2.add("boolean", new JsonBoolean(true));
		assertEquals("{\"o1\":{\"string\":\"s\",\"integer\":2},\"o2\":{\"boolean\":true,\"double\":1.2}}", object.toJson());
		
	}
	
	@Test
	public void testJsonArray() {
		JsonArray array = new JsonArray();
		assertEquals("[]", array.toJson());
		
		JsonArray a1 = new JsonArray();
		array = new JsonArray(a1);
		assertEquals("[[]]", array.toJson());
		
		JsonArray a2 = new JsonArray();
		array = new JsonArray(a1, a2);
		assertEquals("[[],[]]", array.toJson());
		
		array = new JsonArray(new JsonString("s1"), new JsonString("s2"));
		assertEquals("[\"s1\",\"s2\"]", array.toJson());
	}
	
	@Test
	public void testJsonMixed() {
		JsonArray a1 = new JsonArray();
		JsonArray a2 = new JsonArray();
		JsonObject object = new JsonObject();
		object.add("a1", a1);
		assertEquals("{\"a1\":[]}", object.toJson());
		object.add("a2", a2);
		assertEquals("{\"a1\":[],\"a2\":[]}", object.toJson());
		
		JsonObject o1 = new JsonObject();
		JsonObject o2 = new JsonObject();
		JsonArray array = new JsonArray(o1);
		assertEquals("[{}]", array.toJson());
		array = new JsonArray(o1,o2);
		assertEquals("[{},{}]", array.toJson());
		
	}
	
}
