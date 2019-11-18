package anaofind.lib.anadatair.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import anaofind.lib.anadatair.data.*;

/**
 * tests on data value
 * @author anaofind
 *
 */
public class TestsContainsDataValue {
	
	@Test
	public void testEquals() {
		DataValue v0 = new DataSettable();
		DataValue v1 = new DataSettable(10);
		DataValue v2 = new DataSettable(10.1);
		DataValue v3 = new DataSettable(true);
		DataValue v4 = new DataSettable("coucou");
		
		assertTrue(v0.equals(v0));
		assertFalse(v0.equals(v1));
		assertFalse(v0.equals(v2));
		assertFalse(v0.equals(v3));
		assertFalse(v0.equals(v4));
		
		assertTrue(v1.equals(v1));
		assertFalse(v1.equals(v0));
		assertFalse(v1.equals(v2));
		assertFalse(v1.equals(v3));
		assertFalse(v1.equals(v4));
		
		assertTrue(v2.equals(v2));
		assertFalse(v2.equals(v0));
		assertFalse(v2.equals(v1));
		assertFalse(v2.equals(v3));
		assertFalse(v2.equals(v4));
		
		assertTrue(v3.equals(v3));
		assertFalse(v3.equals(v0));
		assertFalse(v3.equals(v1));
		assertFalse(v3.equals(v2));
		assertFalse(v3.equals(v4));
		
		assertTrue(v4.equals(v4));
		assertFalse(v4.equals(v0));
		assertFalse(v4.equals(v1));
		assertFalse(v4.equals(v2));
		assertFalse(v4.equals(v3));
		
		DataValue a1 = new DataSettable(v0, v1, v2);
		DataValue a2 = new DataSettable(v0, v1, v2);
		DataValue a3 = new DataSettable(v1, v0, v2);
		DataValue a4 = new DataSettable(v2, v1, v0);
		
		assertTrue(a1.equals(a1));
		assertTrue(a1.equals(a2));
		assertFalse(a1.equals(a3));
		assertFalse(a1.equals(a4));
		
		assertTrue(a2.equals(a2));
		assertTrue(a2.equals(a1));
		assertFalse(a2.equals(a3));
		assertFalse(a2.equals(a4));
		
		assertTrue(a3.equals(a3));
		assertFalse(a3.equals(a1));
		assertFalse(a3.equals(a2));
		assertFalse(a3.equals(a4));
		
		assertTrue(a4.equals(a4));
		assertFalse(a4.equals(a1));
		assertFalse(a4.equals(a2));
		assertFalse(a4.equals(a3));
		
		DataSettable o1 = new DataSettable();
		o1.addData("a1", v1);
		o1.addData("a2", v2);
		DataSettable o2 = new DataSettable();
		o2.addData("a1", v2);
		o2.addData("a2", v1);
		DataSettable o3 = new DataSettable();
		o3.addData("a1", v1);
		o3.addData("a2", v3);
		
		assertTrue(o1.equals(o1));
		assertTrue(o1.equals(o1.toGettable()));
		assertTrue(o1.toGettable().equals(o1));
		assertTrue(o1.toGettable().equals(o1.toGettable()));
		assertFalse(o1.equals(o2));
		assertFalse(o1.equals(o2.toGettable()));
		assertFalse(o1.toGettable().equals(o2));
		assertFalse(o1.toGettable().equals(o2.toGettable()));
		assertFalse(o1.equals(o3));
		assertFalse(o1.equals(o3.toGettable()));
		assertFalse(o1.toGettable().equals(o3));
		assertFalse(o1.toGettable().equals(o3.toGettable()));
		
		assertTrue(o2.equals(o2));
		assertTrue(o2.equals(o2.toGettable()));
		assertTrue(o2.toGettable().equals(o2));
		assertTrue(o2.toGettable().equals(o2.toGettable()));
		assertFalse(o2.equals(o1));
		assertFalse(o2.equals(o1.toGettable()));
		assertFalse(o2.toGettable().equals(o1));
		assertFalse(o2.toGettable().equals(o1.toGettable()));
		assertFalse(o2.equals(o3));
		assertFalse(o2.equals(o3.toGettable()));
		assertFalse(o2.toGettable().equals(o3));
		assertFalse(o2.toGettable().equals(o3.toGettable()));
		
		assertTrue(o3.equals(o3));
		assertTrue(o3.equals(o3.toGettable()));
		assertTrue(o3.toGettable().equals(o3));
		assertTrue(o3.toGettable().equals(o3.toGettable()));
		assertFalse(o3.equals(o1));
		assertFalse(o3.equals(o1.toGettable()));
		assertFalse(o3.toGettable().equals(o1));
		assertFalse(o3.toGettable().equals(o1.toGettable()));
		assertFalse(o3.equals(o2));
		assertFalse(o3.equals(o2.toGettable()));
		assertFalse(o3.toGettable().equals(o2));
		assertFalse(o3.toGettable().equals(o2.toGettable()));
	}
	
	@Test
	public void testBase() {
		DataValue v0 = new DataSettable();
		DataValue v1 = new DataSettable(10);
		DataValue v2 = new DataSettable(10.1);
		DataValue v3 = new DataSettable(true);
		DataValue v4 = new DataSettable("coucou");
		
		assertEquals(0, v0.size());
		assertEquals("null", v0.getType());
		
		assertEquals(1, v1.size());
		assertEquals("integer", v1.getType());
		
		assertEquals(1, v2.size());
		assertEquals("double", v2.getType());
		
		assertEquals(1, v3.size());
		assertEquals("boolean", v3.getType());
		
		assertEquals(1, v4.size());
		assertEquals("string", v4.getType());
		
		DataSettable v5 = new DataSettable();
		v5.addData(v0);
		assertEquals(1, v5.size());
		assertEquals("array", v5.getType());
		
		v5 = new DataSettable();
		v5.addData(v1);
		assertEquals(1, v5.size());
		assertEquals("integer", v5.getType());
		
		v5 = new DataSettable();
		v5.addData(v2);
		assertEquals(1, v5.size());
		assertEquals("double", v5.getType());
		
		v5 = new DataSettable();
		v5.addData(v3);
		assertEquals(1, v5.size());
		assertEquals("boolean", v5.getType());
		
		v5 = new DataSettable();
		v5.addData(v4);
		assertEquals(1, v5.size());
		assertEquals("string", v5.getType());
	}
	
	@Test
	public void testArray() {
		DataValue v0 = new DataSettable();
		DataValue v1 = new DataSettable(10);
		DataValue v2 = new DataSettable(10.1);
		DataValue v3 = new DataSettable(true);
		DataValue v4 = new DataSettable("coucou");
		
		DataSettable array = new DataSettable(5, 10, 15);
		assertEquals(3, array.size());
		assertEquals("array", array.getType());
		assertTrue(array.getInteger(0).equals(5));
		assertTrue(array.getInteger(1).equals(10));
		assertTrue(array.getInteger(2).equals(15));
		assertTrue(array.getDouble(0).equals(5.0));
		assertTrue(array.getDouble(1).equals(10.0));
		assertTrue(array.getDouble(2).equals(15.0));
		
		array = new DataSettable(5.0, 10.5);
		assertEquals(2, array.size());
		assertEquals("array", array.getType());
		assertTrue(array.getDouble(0).equals(5.0));
		assertTrue(array.getDouble(1).equals(10.5));
		
		array = new DataSettable("coucou", "sava");
		assertEquals(2, array.size());
		assertEquals("array", array.getType());
		assertTrue(array.getString(0).equals("coucou"));
		assertTrue(array.getString(1).equals("sava"));
		
		array = new DataSettable(true, false);
		assertEquals(2, array.size());
		assertEquals("array", array.getType());
		assertTrue(array.getBoolean(0));
		assertFalse(array.getBoolean(1));
		
		array = new DataSettable();
		array.addData(v0);
		array.addData(v1);
		array.addData(v2);
		array.addData(v3);
		array.addData(v4);
		assertEquals(5, array.size());
		assertEquals("array", array.getType());
		assertTrue(array.getInteger(1).equals(10));
		assertTrue(array.getDouble(1).equals(10.0));
		assertTrue(array.getDouble(2).equals(10.1));
		assertTrue(array.getBoolean(3));
		assertTrue(array.getString(4).equals("coucou"));
	}
	
	@Test
	public void testObject() {
		DataValue v0 = new DataSettable();
		DataValue v1 = new DataSettable(10);
		DataValue v2 = new DataSettable(10.1);
		DataValue v3 = new DataSettable(true);
		DataValue v4 = new DataSettable("coucou");
		
		DataSettable object = new DataSettable();
		object.addInteger("a1", 10);
		assertEquals(1, object.size());
		assertEquals("object", object.getType());
		assertTrue(object.getInteger("a1").equals(10));
		
		object.addDouble("a2", 5.5);
		assertEquals(2, object.size());
		assertEquals("object", object.getType());
		assertTrue(object.getDouble("a2").equals(5.5));
		
		object.addString("a3", "coucou");
		assertEquals(3, object.size());
		assertEquals("object", object.getType());
		assertTrue(object.getString("a3").equals("coucou"));
		
		object.addBoolean("a4", true);
		assertEquals(4, object.size());
		assertEquals("object", object.getType());
		assertTrue(object.getBoolean("a4"));
		
		object = new DataSettable();
		object.addData("v0", v0);
		object.addData("v1", v1);
		object.addData("v2", v2);
		object.addData("v3", v3);
		object.addData("v4", v4);
		assertEquals(5, object.size());
		assertEquals("object", object.getType());
		assertTrue(object.getInteger("v1").equals(10));
		assertTrue(object.getDouble("v1").equals(10.0));
		assertTrue(object.getDouble("v2").equals(10.1));
		assertTrue(object.getBoolean("v3"));
		assertTrue(object.getString("v4").equals("coucou"));
	}
	
	@Test
	public void testMixed() {
		DataValue v0 = new DataSettable();
		DataValue v1 = new DataSettable(10);
		DataValue v2 = new DataSettable(10.1);
		DataValue v3 = new DataSettable(true);
		DataValue v4 = new DataSettable("coucou");
		
		DataSettable object = new DataSettable();
		DataSettable array = new DataSettable(10,15);
		object.addData("array", array);
		assertEquals(1, object.size());
		assertEquals("object", object.getType());
		
		object = new DataSettable();
		array = new DataSettable(object);
		assertEquals(1, array.size());
		assertEquals("array", array.getType());
		
		DataSettable o1 = new DataSettable();
		DataSettable o2 = new DataSettable();
		o1.addData("v1", v1);
		o2.addData("v2", v2);
		o2.addData("v3", v3);
		array = new DataSettable(o1, o2);
		assertEquals(2, array.size());
		assertEquals("array", array.getType());
		assertTrue(array.getData(0).getInteger("v1").equals(10));
		assertTrue(array.getData(0).getDouble("v1").equals(10.0));
		assertTrue(array.getData(1).getDouble("v2").equals(10.1));
		assertTrue(array.getData(1).getBoolean("v3"));
		
		object = new DataSettable();
		DataSettable a1 = new DataSettable(v0, v4);
		DataSettable a2 = new DataSettable(v2, v3);
		object.addData("a1", a1);
		object.addData("a2", a2);
		assertEquals(2, object.size());
		assertEquals("object", object.getType());
		assertTrue(object.getData("a1").getString(1).equals("coucou"));
		assertTrue(object.getData("a2").getDouble(0).equals(10.1));
		assertTrue(object.getData("a2").getBoolean(1));
		
	}
	
}
