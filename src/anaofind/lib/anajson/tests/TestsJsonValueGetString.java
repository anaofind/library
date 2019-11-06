package anaofind.lib.anajson.tests;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import anaofind.lib.anajson.*;

/**
 * tests json value on get string
 * @author anaofind
 *
 */
public class TestsJsonValueGetString {

	
	@Test
	public void testJsonObject() {
		JsonObject object = new JsonObject();
		assertEquals("{}", object.getString());
		
		JsonObject o1 = new JsonObject();
		object.add("o1", o1);
		assertEquals("{\"o1\":{}}", object.getString());
		
		JsonObject o2 = new JsonObject();
		object.add("o2", o2);
		assertEquals("{\"o1\":{},\"o2\":{}}", object.getString());
		
		o1.add("string", new JsonString("s"));
		assertEquals("{\"o1\":{\"string\":\"s\"},\"o2\":{}}", object.getString());
		
		o1.add("integer", new JsonInteger(2));
		assertEquals("{\"o1\":{\"string\":\"s\",\"integer\":2},\"o2\":{}}", object.getString());
		
		o2.add("double", new JsonDouble(1.2));
		assertEquals("{\"o1\":{\"string\":\"s\",\"integer\":2},\"o2\":{\"double\":1.2}}", object.getString());
		
		o2.add("boolean", new JsonBoolean(true));
		assertEquals("{\"o1\":{\"string\":\"s\",\"integer\":2},\"o2\":{\"boolean\":true,\"double\":1.2}}", object.getString());
		
	}
	
	@Test
	public void testJsonArray() {
		JsonArray array = new JsonArray();
		assertEquals("[]", array.getString());
		
		JsonArray a1 = new JsonArray();
		array = new JsonArray(a1);
		assertEquals("[[]]", array.getString());
		
		JsonArray a2 = new JsonArray();
		array = new JsonArray(a1, a2);
		assertEquals("[[],[]]", array.getString());
		
		array = new JsonArray(new JsonString("s1"), new JsonString("s2"));
		assertEquals("[\"s1\",\"s2\"]", array.getString());
	}
	
	@Test
	public void testJsonMixed() {
		JsonArray a1 = new JsonArray();
		JsonArray a2 = new JsonArray();
		JsonObject object = new JsonObject();
		object.add("a1", a1);
		assertEquals("{\"a1\":[]}", object.getString());
		object.add("a2", a2);
		assertEquals("{\"a1\":[],\"a2\":[]}", object.getString());
		
		JsonObject o1 = new JsonObject();
		JsonObject o2 = new JsonObject();
		JsonArray array = new JsonArray(o1);
		assertEquals("[{}]", array.getString());
		array = new JsonArray(o1,o2);
		assertEquals("[{},{}]", array.getString());
		
	}
	
}
