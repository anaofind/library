package anaofind.lib.anadatair.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import anaofind.lib.anadatair.data.*;
import anaofind.lib.anadatair.ir.*;

/**
 * test to data value
 * @author anaofind
 */
public class TestToDataValue {

	@Test
	public void testBase() {
		IRConverter b = new IRBoolean(true);
		IRConverter i = new IRInteger(10);
		IRConverter d = new IRDouble(5.5);
		IRConverter s = new IRString("coucou");
		IRConverter n = new IRNull();

		DataValue db = b.toDataValue();
		DataValue di = i.toDataValue();
		DataValue dd = d.toDataValue();
		DataValue ds = s.toDataValue();
		DataValue dn = n.toDataValue();

		assertEquals(1, db.size());
		assertEquals("boolean", db.getType());
		assertTrue(db.getBoolean());

		assertEquals(1, di.size());
		assertEquals("integer", di.getType());
		assertTrue(di.getInteger().equals(10));
		assertTrue(di.getDouble().equals(10.0));

		assertEquals(1, dd.size());
		assertEquals("double", dd.getType());
		assertTrue(dd.getDouble().equals(5.5));
		
		assertEquals(1, ds.size());
		assertEquals("string", ds.getType());
		assertTrue(ds.getString().equals("coucou"));

		assertEquals(0, dn.size());
		assertEquals("null", dn.getType());
	}
	
	@Test
	public void testArray() {
		IRConverter b = new IRBoolean(true);
		IRConverter i = new IRInteger(10);
		
		DataValue db = new DataSettable(true);
		DataValue di = new DataSettable(10);
		
		IRConverter a1 = new IRArray(b, i);
		IRConverter a2 = new IRArray(i, b);
		IRConverter a3 = new IRArray(a1, a2);
		DataValue da1 = new DataSettable(db, di);
		DataValue da2 = new DataSettable(di, db);
		DataValue da3 = new DataSettable(da1, da2);
		DataValue da4 = new DataSettable(da2, da1);
		assertEquals(a1.toDataValue(), da1);
		assertNotEquals(a2.toDataValue(), da1);
		assertEquals(a3.toDataValue(), da3);
		assertNotEquals(a3.toDataValue(), da4);
		
	}
	
	@Test
	public void testObject() {
		IRConverter b = new IRBoolean(true);
		IRConverter i = new IRInteger(10);

		DataValue db = b.toDataValue();
		DataValue di = i.toDataValue();
		
		IRObject o1 = new IRObject();
		o1.addAttribute("b", b);
		o1.addAttribute("i", i);
		IRObject o2 = new IRObject();
		o2.addAttribute("b", i);
		o2.addAttribute("i", b);
		
		DataSettable d1 = new DataSettable();
		d1.addData("b", db);
		d1.addData("i", di);
		DataSettable d2 = new DataSettable();
		d2.addData("b", di);
		d2.addData("i", db);
		
		IRObject object = new IRObject();
		DataSettable dataObject = new DataSettable();
		
		object.addAttribute("o1", o1);
		dataObject.addData("o1", d1);
		
		assertEquals(o1.toDataValue(), d1);
		assertNotEquals(o1.toDataValue(), d2);
		assertNotEquals(o1.toDataValue(), dataObject);
		assertEquals(o2.toDataValue(), d2);
		assertNotEquals(o2.toDataValue(), d1);
		assertNotEquals(o2.toDataValue(), dataObject);
		
		assertNotEquals(object.toDataValue(), d1);
		assertNotEquals(object.toDataValue(), d2);
		assertEquals(object.toDataValue(), dataObject);
	}

	public void testMixed() {
		IRConverter b = new IRBoolean(true);
		IRConverter i = new IRInteger(10);

		DataValue db = b.toDataValue();
		DataValue di = i.toDataValue();
		
		IRObject o1 = new IRObject();
		o1.addAttribute("b", b);
		o1.addAttribute("i", i);
		IRObject o2 = new IRObject();
		o2.addAttribute("b", i);
		o2.addAttribute("i", b);
		
		DataSettable do1 = new DataSettable();
		do1.addData("b", db);
		do1.addData("i", di);
		DataSettable do2 = new DataSettable();
		do2.addData("b", di);
		do2.addData("i", db);
		
		IRArray a1 = new IRArray(o1, o2);
		IRArray a2 = new IRArray(o2, o1);
		
		DataSettable da1 = new DataSettable(do1, do2);
		DataSettable da2 = new DataSettable(do2, do1);
		
		assertEquals(a1.toDataValue(), da1);
		assertNotEquals(a1.toDataValue(), da2);
		assertEquals(a2.toDataValue(), da2);
		assertNotEquals(a2.toDataValue(), da1);
		
		a1 = new IRArray(i, b);
		a2 = new IRArray(b, i);
		
		da1 = new DataSettable(da1, da2);
		da2 = new DataSettable(da2, da1);
		
		o1 = new IRObject();
		o1.addAttribute("a1", a1);
		o1.addAttribute("a2", a2);
		o2 = new IRObject();
		o2.addAttribute("a1", a2);
		o2.addAttribute("a2", a1);
		
		do1 = new DataSettable();
		do1.addData("a1", da1);
		do1.addData("a2", da2);
		do2 = new DataSettable();
		do2.addData("a1", da2);
		do2.addData("a2", da1);
		
		assertEquals(o1.toDataValue(), do1);
		assertNotEquals(o1.toDataValue(), do2);
		assertEquals(o2.toDataValue(), do2);
		assertNotEquals(o2.toDataValue(), do1);
	}
	
}
