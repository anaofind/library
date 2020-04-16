package tests.anadatair;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

	@BeforeEach
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
		String json1 = "{\"code\":4,\"receiver\":\"server\",\"sender\":\"anaofind\",\"content\":{\"password\":\"fqd\",\"version\":\"1.0.0\"}}";

		CompilatorJSON cmp = new CompilatorJSON(json1);
		System.out.println(UtilReader.removeSpace(json1.toString()));
		System.out.println(cmp.getValue().toString());
		assertEquals(UtilReader.removeSpace(json1.toString()), cmp.getValue().toString());
	}


}
