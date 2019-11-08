package anaofind.lib.anadatatext.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import anaofind.lib.anadatatext.ir.*;

/**
 * test ir
 * @author anaofind
 */
public class TestsConverterIR {

	
	/**
	 * test ir base
	 */
	@Test
	public void testBase() {
		IRConverter value = new IRInteger(10);
		assertEquals("(integer:10)", value.toTextIR());
		value = new IRInteger(-10);
		assertEquals("(integer:-10)", value.toTextIR());
		value = new IRInteger(+10);
		assertEquals("(integer:10)", value.toTextIR());
		
		value = new IRDouble(5.5);
		assertEquals("(double:5.5)", value.toTextIR());
		value = new IRDouble(-5.5);
		assertEquals("(double:-5.5)", value.toTextIR());
		value = new IRDouble(+5.5);
		assertEquals("(double:5.5)", value.toTextIR());
		value = new IRDouble(5);
		assertEquals("(double:5.0)", value.toTextIR());
		
		value = new IRString("coucou");
		assertEquals("(string:coucou)", value.toTextIR());
		value = new IRString("cou cou");
		assertEquals("(string:cou cou)", value.toTextIR());
		value = new IRString("5");
		assertEquals("(string:5)", value.toTextIR());
		
		value = new IRBoolean(true);
		assertEquals("(boolean:true)", value.toTextIR());
		value = new IRBoolean(false);
		assertEquals("(boolean:false)", value.toTextIR());
		
		value = new IRNull();
		assertEquals("(null:)", value.toTextIR());
	}
	
	@Test
	public void testArray() {
		IRValue v1 = new IRInteger(10);
		IRValue v2 = new IRDouble(5.2);
		IRValue v3 = new IRString("text");
		IRValue v4 = new IRBoolean(true);
		IRValue v5 = new IRNull();
		
		IRArray array = new IRArray();
		assertEquals("(array:)", array.toTextIR());
		
		array = new IRArray(new IRArray());
		assertEquals("(array:(array:))", array.toTextIR());
		
		array = new IRArray(v1, v2, v3, v4, v5);
		assertEquals("(array:(integer:10)(double:5.2)(string:text)(boolean:true)(null:))", array.toTextIR());
		
		array = new IRArray(v1, v2, new IRArray(v3, v4));
		assertEquals("(array:(integer:10)(double:5.2)(array:(string:text)(boolean:true)))", array.toTextIR());
	}
	
	@Test
	public void testObject() {
		IRValue v1 = new IRInteger(10);
		IRValue v2 = new IRDouble(5.2);
		IRValue v3 = new IRString("text");
		IRValue v4 = new IRBoolean(true);
		IRValue v5 = new IRNull();
		
		IRObject ob = new IRObject();
		ob.addAttribute("a3", v3);
		
		IRObject object = new IRObject();
		assertEquals("(object:)", object.toTextIR());
		
		object.addAttribute("ob", new IRObject());
		assertEquals("(object:<ob>(object:))", object.toTextIR());
		
		object = new IRObject();
		object.addAttribute("a1", v1);
		object.addAttribute("a2", v2);
		object.addAttribute("a3", v3);
		object.addAttribute("a4", v4);
		object.addAttribute("a5", v5);
		assertEquals("(object:<a1>(integer:10)<a2>(double:5.2)<a3>(string:text)<a4>(boolean:true)<a5>(null:))", object.toTextIR());
		
		object = new IRObject();
		object.addAttribute("a1", v1);
		object.addAttribute("ob", ob);
		assertEquals("(object:<a1>(integer:10)<ob>(object:<a3>(string:text)))", object.toTextIR());
	}
	
	@Test
	public void testMixed() {
		IRValue v1 = new IRInteger(10);
		IRValue v2 = new IRDouble(5.2);
		IRValue v3 = new IRString("text");
		IRValue v4 = new IRBoolean(true);
		IRValue v5 = new IRNull();
		
		IRObject object = new IRObject();
		IRArray array = new IRArray();
		
		object.addAttribute("ar", array);
		assertEquals("(object:<ar>(array:))", object.toTextIR());
		
		object = new IRObject();
		array = new IRArray(object);
		assertEquals("(array:(object:))", array.toTextIR());
		
		array = new IRArray(v1, v2, v3);
		object.addAttribute("ar", array);
		assertEquals("(object:<ar>(array:(integer:10)(double:5.2)(string:text)))", object.toTextIR());
		
		IRObject o1 = new IRObject();
		IRObject o2 = new IRObject();
		IRObject o3 = new IRObject();
		array = new IRArray(o1, o2, o3);
		o1.addAttribute("a1", v1);
		o2.addAttribute("a4", v4);
		o2.addAttribute("a5", v5);
		o3.addAttribute("a3", v3);
		assertEquals("(array:(object:<a1>(integer:10))(object:<a4>(boolean:true)<a5>(null:))(object:<a3>(string:text)))", array.toTextIR());
	}
	
}
