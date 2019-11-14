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
		IRConverter n = new IRNull();
		
		DataValue db = b.toDataValue();
		DataValue di = i.toDataValue();
		DataValue dd = d.toDataValue();
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
		
		assertEquals(0, dn.size());
		assertEquals("null", dn.getType());
		
	}
}
