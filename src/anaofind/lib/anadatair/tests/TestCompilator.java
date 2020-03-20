package anaofind.lib.anadatair.tests;

import static org.junit.Assert.*;

import org.junit.*;

import anaofind.lib.anadatair.compilator.Compilator.CompilatorException;
import anaofind.lib.anadatair.compilator.CompilatorJSON;
import anaofind.lib.anadatair.json.*;
import anaofind.lib.anadatair.reader.UtilReader;

/**
 * test converter
 * @author anaofind
 *
 */
public class TestCompilator {

	/**
	 * json object
	 */
	private JsonObject objectJSON;

	@Before
	public void before() {
		this.createData();
	}

	/**
	 * create datas
	 */
	private void createData() {
		this.objectJSON = new JsonObject();
		this.objectJSON.addAttribute("Nom", new JsonString("RAUZIER"));
		this.objectJSON.addAttribute("Prenom", new JsonString("LEO"));
		this.objectJSON.addAttribute("Age", new JsonNumber(21));
		this.objectJSON.addAttribute("Sex", new JsonString("HOMME"));
		this.objectJSON.addAttribute("Pays", new JsonString("FRANCE"));
		this.objectJSON.addAttribute("Contrat", new JsonBoolean(false));
		this.objectJSON.addAttribute("V/N/D", new JsonArray(
				new JsonArray(
						new JsonString("Victoire"), 
						new JsonString("Null"), 
						new JsonString("Defaite")
						), 
				new JsonNumber(10),
				new JsonNumber(5), 
				new JsonNumber(2)
				)
				);

	}

	@Test
	public void testJSON() throws CompilatorException {	
		CompilatorJSON cmp = new CompilatorJSON(this.objectJSON.toString());
		assertEquals(this.objectJSON.toString(), cmp.getValue().toString());
	}

	@Test
	public void testRealJSON() throws CompilatorException {
		String json1 = "{  \r\n" + 
				"\r\n" + 
				"\r\n" + 
				"	\"array\" : [\r\n" + 
				"		1,\r\n" + 
				"		false,\r\n" + 
				"		null,\r\n" + 
				"		0.25,\r\n" + 
				"		\"haha\"\r\n" + 
				"	],\r\n" + 
				"	\"object\" : {\r\n" + 
				"\r\n" + 
				"		\"a1\" : 50,\r\n" + 
				"		\"a2\" : true,\r\n" + 
				"		\"a3\"  : \"lol\",\r\n" + 
				"		\"a4\" : null,\r\n" + 
				"		\"a5\" : 15.22,\r\n" + 
				"		\"a6\" : [null,  10, 12 , 15]\r\n" + 
				"		\r\n" + 
				"\r\n" + 
				"	}\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"}";

		CompilatorJSON cmp = new CompilatorJSON(json1);
		System.out.println(UtilReader.removeSpace(json1.toString()));
		System.out.println(cmp.getValue().toString());
		assertEquals(UtilReader.removeSpace(json1.toString()), cmp.getValue().toString());
	}


}
