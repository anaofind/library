package anaofind.lib.anadatair.tests;

import static org.junit.Assert.*;
import org.junit.*;

import anaofind.lib.anadatair.*;
import anaofind.lib.anadatair.ir.*;


public class TestAnadatair {
	/**
	 * ir object
	 */
	private IRObject objectIR;


	@Before
	public void before() {
		this.createData();
	}

	/**
	 * create datas
	 */
	private void createData() {
		this.objectIR = new IRObject();
		this.objectIR.addAttribute("Nom", new IRString("RAUZIER"));
		this.objectIR.addAttribute("Prenom", new IRString("LEO"));
		this.objectIR.addAttribute("Age", new IRInteger(21));
		this.objectIR.addAttribute("Sex", new IRString("HOMME"));
		this.objectIR.addAttribute("Pays", new IRString("FRANCE"));
		this.objectIR.addAttribute("Contrat", new IRBoolean(false));
		this.objectIR.addAttribute("V/N/D", new IRArray(
				new IRArray(
						new IRString("Victoire"), 
						new IRString("Null"), 
						new IRString("Defaite")
						), 
				new IRInteger(10),
				new IRInteger(5), 
				new IRInteger(2)
				)
				);
	}
	
	@Test
	public void testContain() {
		Anadatair air = this.objectIR.toAnadatair();
		
		assertTrue(air.contains("Nom", "string"));
		assertTrue(air.contains("Prenom", "string"));
		assertTrue(air.contains("Age", "integer"));
		assertTrue(air.contains("Sex", "string"));
		assertTrue(air.contains("Pays", "string"));
		assertTrue(air.contains("Contrat", "boolean"));
		assertTrue(air.contains("V/N/D", "array"));
		
		assertFalse(air.contains(0, "string"));
		assertFalse(air.contains(1, "string"));
		assertFalse(air.contains(2, "string"));
		
		assertFalse(air.contains(0, "array"));
		assertFalse(air.contains(1, "integer"));
		assertFalse(air.contains(2, "integer"));
		assertFalse(air.contains(3, "integer"));
		
		Anadatair vnd = air.getData("V/N/D");
		assertTrue(vnd.contains(0, "array"));
		assertTrue(vnd.contains(1, "integer"));
		assertTrue(vnd.contains(2, "integer"));
		assertTrue(vnd.contains(3, "integer"));
		
		Anadatair stringVND = vnd.getData(0);
		assertTrue(stringVND.contains(0, "string"));
		assertTrue(stringVND.contains(1, "string"));
		assertTrue(stringVND.contains(2, "string"));
		
	}

	@Test
	public void testValue() {
		Anadatair air = this.objectIR.toAnadatair();
		
		assertEquals("RAUZIER", air.getString("Nom"));
		assertEquals("LEO", air.getString("Prenom"));
		assertEquals(new Integer(21), air.getInteger("Age"));
		assertEquals("HOMME", air.getString("Sex"));
		assertEquals("FRANCE", air.getString("Pays"));
		assertEquals(new Boolean(false), air.getBoolean("Contrat"));
		
		Anadatair vnd = air.getData("V/N/D");
		assertEquals(new Integer(10), vnd.getInteger(1));
		assertEquals(new Integer(5), vnd.getInteger(2));
		assertEquals(new Integer(2), vnd.getInteger(3));
		
		Anadatair stringVND = vnd.getData(0);
		assertEquals("Victoire", stringVND.getString(0));
		assertEquals("Null", stringVND.getString(1));
		assertEquals("Defaite", stringVND.getString(2));
	}
	
}
