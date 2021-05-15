package anaofind.anadatair;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import anaofind.lib.anadatair.*;
import anaofind.lib.anadatair.compilator.Compilator.CompilatorException;
import anaofind.lib.anadatair.util.TypeResolver;
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
	public void TestDecodeJsonFile() throws CompilatorException {		
		Anadatair data1 = UtilAnadatair.jsonDecodeFile("test/json_decode_file.json");
		System.out.println(data1.toJson().toString());
		Anadatair data2 = UtilAnadatair.jsonDecode(data1.toJson().toString());
		assertTrue(data1.equals(data2));
		assertTrue(data2.equals(data1));
	}
	
	@Test
	public void TestEncodeJsonFile() {
		AnadatairObject data1 = new AnadatairObject();
		data1.add("a1", new AnadatairArray(10, "coucou", false, null));
		data1.add("a2", Map.of("int", new int[][] {{1,2,3}, {4,5,6,7}, {8,9,10}}));
		data1.add("a3", Map.of("object", new Object[] {Map.of("v1", true, "v2", 0.1), 4}));
		UtilAnadatair.jsonEncodeFile(data1, "test/json_encode_file.json");
		Anadatair data2 = UtilAnadatair.jsonDecodeFile("test/json_encode_file.json");
		assertTrue(data1.equals(data2));
		assertTrue(data2.equals(data1));
	}
	
	
	@Test
	public void TestArrayTypeDimension() {
		assertEquals(1, new AnadatairArray().getDimension());
		assertEquals(1, new AnadatairArray(10,50,60,80).getDimension());
		assertEquals(2, new AnadatairArray(new int[] {10,5,7}, new int[] {60,50,40}).getDimension());
		assertEquals(3, new AnadatairArray(new int[][] {{10},{5,7}}, new int[][] {{60,50},{40}}).getDimension());
		assertEquals(2, new AnadatairArray(new int[] {10,5,7}, new int[][] {{60,50},{40}}).getDimension());
		assertEquals(1, new AnadatairArray(5, new int[] {10,5,7}, new int[][] {{60,50},{40}}).getDimension());
		
		assertEquals(TypeResolver.OBJECT, new AnadatairArray().getTypeOfArray());
		assertEquals(TypeResolver.INT, new AnadatairArray(10,50,60,80).getTypeOfArray());
		assertEquals(TypeResolver.INT, new AnadatairArray(new int[] {10,5,7}, new int[] {60,50,40}).getTypeOfArray());
		assertEquals(TypeResolver.INT, new AnadatairArray(10,50,60,new int[] {10,5,7}).getTypeOfArray());
		assertEquals(TypeResolver.DOUBLE, new AnadatairArray(10.5,50.2,60.0,80.4).getTypeOfArray());
		assertEquals(TypeResolver.DOUBLE, new AnadatairArray(new double[] {10.2,5.3,7.4}, new double[] {60.1,50.8,40.0}).getTypeOfArray());
		assertEquals(TypeResolver.DOUBLE, new AnadatairArray(10.5,50.5,60.0, new double[] {10.0,5.0,7.0}).getTypeOfArray());
		assertEquals(TypeResolver.STRING, new AnadatairArray("salut", "comment").getTypeOfArray());
		assertEquals(TypeResolver.STRING, new AnadatairArray(new String[] {"salut", "comment"}, new String[] {"tres", "bien"}).getTypeOfArray());
		assertEquals(TypeResolver.STRING, new AnadatairArray("lol" ,new String[] {"haha", "hihi"}).getTypeOfArray());		
		assertEquals(TypeResolver.BOOLEAN, new AnadatairArray(true, false).getTypeOfArray());
		assertEquals(TypeResolver.BOOLEAN, new AnadatairArray(new boolean[] {true, true}, new boolean[] {false, false}).getTypeOfArray());
		assertEquals(TypeResolver.BOOLEAN, new AnadatairArray(true ,new boolean[] {false, false}).getTypeOfArray());	
		assertEquals(TypeResolver.OBJECT, new AnadatairArray(10,80.4).getTypeOfArray());
		assertEquals(TypeResolver.OBJECT, new AnadatairArray(new double[] {10.2,5.3,7.4}, new int[] {60,4}).getTypeOfArray());
		assertEquals(TypeResolver.OBJECT, new AnadatairArray(true, new double[] {10.0,5.0,7.0}).getTypeOfArray());
	}
	
	@Test
	public void TestArraySize() {
		assertEquals(0, new AnadatairArray().size());
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
		Anadatair testObject = UtilAnadatair.encode((Object) map);
		System.out.println(testObject.toJson());
		
		Object set = List.of(true, 1234567891032132132L, 4, 4.0);
		testArray = UtilAnadatair.encode(set);
		System.out.println(testArray.toJson());
		Object[] valueArray = UtilAnadatair.decode(testArray, Object[].class);
		for (Object object : valueArray) {
			System.out.println(object + " -> " + TypeResolver.getType(object));
		}
		
		testArray = UtilAnadatair.encode(new ArrayList<String>());
		System.out.println(testArray.toJson());
		
		int[][] arrays2D = new int[][] {{1,2}, {3,4}};
		testArray = UtilAnadatair.encode(arrays2D);
		System.out.println(testArray.toJson());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void TestDecode() throws CompilatorException {
		Integer[] aie = UtilAnadatair.decode(UtilAnadatair.encode(new int[] {}), Integer[].class);
		Long[] ale = UtilAnadatair.decode(UtilAnadatair.encode(new long[] {}), Long[].class);
		Double[] ade = UtilAnadatair.decode(UtilAnadatair.encode(new double[] {}), Double[].class);
		Boolean[] abe = UtilAnadatair.decode(UtilAnadatair.encode(new boolean[] {}), Boolean[].class);
		String[] ase = UtilAnadatair.decode(UtilAnadatair.encode(new String[] {}), String[].class);
		Object[] aoe = UtilAnadatair.decode(UtilAnadatair.encode(new Object[] {}), Object[].class);
		
		Integer[] ai = UtilAnadatair.decode(UtilAnadatair.encode(new int[] {10,5,5,3}), Integer[].class);
		Long[] al = UtilAnadatair.decode(UtilAnadatair.encode(new long[] {2L, 1L}), Long[].class);
		Double[] ad = UtilAnadatair.decode(UtilAnadatair.encode(new double[] {10.5,5.2,5.3,3.4}), Double[].class);
		Boolean[] ab = UtilAnadatair.decode(UtilAnadatair.encode(new boolean[] {true, false}), Boolean[].class);
		String[] as = UtilAnadatair.decode(UtilAnadatair.encode(new String[] {"coucou", "comment", "�ava", "?"}), String[].class);
		Object[] ao = UtilAnadatair.decode(UtilAnadatair.encode(new Object[] {"coucou", 4, 4.0, 4L, false}), Object[].class);
		
		Integer[][] ai2 = UtilAnadatair.decode(UtilAnadatair.encode(new int[][] {{10,5},{5,3}}), Integer[][].class);
		Long[][] al2 = UtilAnadatair.decode(UtilAnadatair.encode(new long[][] {{10L,5L},{5L,3L}}), Long[][].class);
		Double[][] ad2 = UtilAnadatair.decode(UtilAnadatair.encode(new double[][] {{10.2,5.2},{5.2,3.2}}), Double[][].class);
		Boolean[][] ab2 = UtilAnadatair.decode(UtilAnadatair.encode(new boolean[][] {{true,false},{false,true}}), Boolean[][].class);
		String[][] as2 = UtilAnadatair.decode(UtilAnadatair.encode(new String[][] {{"coucou","�a va"},{"oui","tres bien"}}), String[][].class);
		Object[][] ao2 = UtilAnadatair.decode(UtilAnadatair.encode(new Object[][] {{"coucou", 4}, {4.0}, {4L}, {false}}), Object[][].class);
		
		Map<String, Integer> am = UtilAnadatair.decode(UtilAnadatair.encode(new HashMap<String, Integer>()), Map.class);
		
		assertTrue(Arrays.deepEquals(aie, new Integer[] {}));
		assertTrue(Arrays.deepEquals(ale, new Long[] {}));
		assertTrue(Arrays.deepEquals(ade, new Double[] {}));
		assertTrue(Arrays.deepEquals(abe, new Boolean[] {}));
		assertTrue(Arrays.deepEquals(ase, new String[] {}));
		assertTrue(Arrays.deepEquals(aoe, new Object[] {}));
		
		assertEquals(am, new HashMap<String, Integer>());
		
		assertTrue(Arrays.deepEquals(ai, new Integer[] {10,5,5,3}));
		assertTrue(Arrays.deepEquals(al, new Long[] {2L,1L}));
		assertTrue(Arrays.deepEquals(ad, new Double[] {10.5,5.2,5.3,3.4}));
		assertTrue(Arrays.deepEquals(ab, new Boolean[] {true, false}));
		assertTrue(Arrays.deepEquals(as, new String[] {"coucou", "comment", "�ava", "?"}));
		System.out.println(Arrays.toString(ao));
		assertTrue(Arrays.deepEquals(ao, new Object[] {"coucou", 4, 4.0, 4L, false}));
		
		assertTrue(Arrays.deepEquals(ai2, new Integer[][] {{10,5},{5,3}}));
		assertTrue(Arrays.deepEquals(al2, new Long[][] {{10L,5L},{5L,3L}}));
		assertTrue(Arrays.deepEquals(ad2, new Double[][] {{10.2,5.2},{5.2,3.2}}));
		assertTrue(Arrays.deepEquals(ab2, new Boolean[][] {{true,false},{false,true}}));
		assertTrue(Arrays.deepEquals(as2, new String[][] {{"coucou","�a va"},{"oui","tres bien"}}));
		assertTrue(Arrays.deepEquals(ao2, new Object[][] {{"coucou", 4}, {4.0}, {4L}, {false}}));
		
		
		AnadatairObject mark1 = new AnadatairObject();
		mark1.add("mastery", "Maths");
		mark1.add("value", 15);
		AnadatairObject mark2 = new AnadatairObject();
		mark2.add("mastery", "English");
		mark2.add("value", 7);
		
		AnadatairObject personne = new AnadatairObject();
		personne.add("name", "RAUZIER");
		personne.add("firstName", "Leo");
		personne.add("age", 22);
		personne.add("marks", new AnadatairArray(mark1, mark2));
		
		personne.add("week", new boolean[] {true, true, true, true, true, false, false});
		
		System.out.println(personne.toJson());
		
		System.out.println("DECODE");
		Personne1 p1 = UtilAnadatair.decode(personne, Personne1.class);
		System.out.println(p1);
		Personne2 p2 = UtilAnadatair.decode(personne, Personne2.class);
		System.out.println(p2);
		System.out.println("END DECODE");
		
		assertTrue(personne.equals(UtilAnadatair.encode(p1)));
		assertTrue(personne.equals(UtilAnadatair.encode(p2)));
		assertTrue(UtilAnadatair.encode(p1).equals(UtilAnadatair.encode(p2)));
	}

	/**
	 * class Personne 2
	 * @author anaofind
	 *
	 */
	public static class Personne1 {
		public String name;
		public String firstName;
		public int age;
		
		public Mark[] marks;
		
		public Boolean[] week;
					
		public String toString() {
			return "PERSONNE 1 : " + name + " " + firstName + " " + age + " " + Arrays.toString(week) + " " + Arrays.toString(marks);
		}
	}
	
	/**
	 * class Personne 2
	 * @author anaofind
	 *
	 */
	public static class Personne2 {
		public String name;
		public String firstName;
		public int age;
		
		public Set<Mark> marks;
		
		public List<Boolean> week;
					
		public String toString() {
			return "PERSONNE 2 : " + name + " " + firstName + " " + age + " " + week + " " + marks;
		}
	}
	
	/**
	 * class mark
	 * @author anaofind
	 */
	public static class Mark {
		public String mastery;
		public int value;
		
		public String toString() {
			return mastery + "(" + value + ")";
		}
	}
}
