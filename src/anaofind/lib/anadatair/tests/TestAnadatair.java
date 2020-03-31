package anaofind.lib.anadatair.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;
import anaofind.lib.anadatair.*;
import anaofind.lib.anadatair.compilator.Compilator.CompilatorException;
import anaofind.lib.anadatair.util.UtilAnadatair;


/**
 * class tests on anadatair data
 * @author anaofind
 */
public class TestAnadatair {
	
	@Test
	public void testEquals() {
		Anadatair a1 = new AnadatairArray(10, "coucou", false, null);
		Anadatair a2 = new AnadatairArray(10.0, "coucou", false, null);
		Anadatair a3 = new AnadatairArray(10, "coucou", true, null);
		Anadatair a4 = new AnadatairArray(10, "coucou", false);
		Anadatair a5 = new AnadatairArray(10, false, "coucou", null);
		
		assertTrue(a1.equals(a2));
		assertTrue(a2.equals(a1));
		assertFalse(a1.equals(a3));
		assertFalse(a1.equals(a4));
		assertFalse(a1.equals(a5));
		
		
		AnadatairObject o1 = new AnadatairObject();
		o1.add("a1", a1);
		o1.add("a2", true);
		
		AnadatairObject o2 = new AnadatairObject();
		o2.add("a2", true);
		o2.add("a1", a2);
		
		assertTrue(o1.equals(o2));
		
		o2.add("a3", 30);
		assertFalse(o1.equals(o2));
		
		o1.add("a3", 30.0);
		assertTrue(o1.equals(o2));
		
		o1.add("a4", null);
		assertFalse(o1.equals(o2));
		
	}
	
	@Test
	public void TestEncodeDecodeJson() throws CompilatorException {
		AnadatairObject data1 = new AnadatairObject();
		data1.add("a1", new AnadatairArray(10, "coucou", false, null));
		data1.add("a2", true);
		
		String json = UtilAnadatair.jsonEncode(data1);
		Anadatair data2 = UtilAnadatair.jsonDecode(json);
		assertTrue(data1.equals(data2));
		assertTrue(data2.equals(data1));
	}
	
	@Test
	public void TestEncode() throws CompilatorException {
		assertTrue(UtilAnadatair.encode(1).equals(new AnadatairInteger(1)));
		assertTrue(UtilAnadatair.encode(1).equals(new AnadatairDouble(1)));
		assertTrue(UtilAnadatair.encode(35.4).equals(new AnadatairDouble(35.4)));
		assertTrue(UtilAnadatair.encode(true).equals(new AnadatairBoolean(true)));
		assertTrue(UtilAnadatair.encode(false).equals(new AnadatairBoolean(false)));
		assertTrue(UtilAnadatair.encode("coucou").equals(new AnadatairString("coucou")));
		
		List<Object> list = new ArrayList<Object>();
		list.add("coucou");
		list.add("sava");
		list.add(10);
		Anadatair testArray = UtilAnadatair.encode(list);
		System.out.println(testArray.toJson());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a1", "coucou");
		map.put("a2", 10);
		map.put("a3", false);
		Anadatair testObject = UtilAnadatair.encode(map);
		System.out.println(testObject.toJson());
		
		Set<String> set = Set.of("coucou", "sava", "tres", "bien");
		testArray = UtilAnadatair.encode(set);
		System.out.println(testArray.toJson());
	}
	
}
