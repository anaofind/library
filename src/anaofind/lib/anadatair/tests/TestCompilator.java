package anaofind.lib.anadatair.tests;

import static org.junit.Assert.*;

import org.junit.*;

import anaofind.lib.anadatair.compilator.CompilatorIR;
import anaofind.lib.anadatair.compilator.CompilatorJSON;
import anaofind.lib.anadatair.ir.*;
import anaofind.lib.anadatair.json.*;
import anaofind.lib.anadatair.printer.*;

/**
 * test converter
 * @author anaofind
 *
 */
public class TestCompilator {

	/**
	 * ir object
	 */
	private IRObject objectIR;
	
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
	public void testIR() {		
		PrettyPrinterIR pp1 = new PrettyPrinterIR();
		PrettyPrinterIR pp2 = new PrettyPrinterIR();
		
		this.objectIR.accept(pp1);
		
		CompilatorIR cmp = new CompilatorIR(pp1.getText());	
		cmp.getValue().accept(pp2);
		
		assertEquals(pp1.getText(), pp2.getText());
		
	}
	
	@Test
	public void testJSON() {	
		PrettyPrinterJSON pp1 = new PrettyPrinterJSON();
		PrettyPrinterJSON pp2 = new PrettyPrinterJSON();
		
		this.objectJSON.accept(pp1);
		
		CompilatorJSON cmp = new CompilatorJSON(pp1.getText());
		cmp.getValue().accept(pp2);
		
		assertEquals(pp1.getText(), pp2.getText());
	}
	
	
}
