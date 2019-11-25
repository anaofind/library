package anaofind.lib.anadatair.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import anaofind.lib.anadatair.compilator.CompilatorIR;
import anaofind.lib.anadatair.ir.*;
import anaofind.lib.anadatair.json.*;
import anaofind.lib.anadatair.printer.*;

/**
 * test converter
 * @author anaofind
 *
 */
public class TestCompilator {

	@Test
	public void testIR() {
		
		PrettyPrinterIR pp1 = new PrettyPrinterIR();
		
		IRObject object = new IRObject();
		object.addAttribute("Nom", new IRString("RAUZIER"));
		object.addAttribute("Prenom", new IRString("LEO"));
		object.addAttribute("Age", new IRInteger(21));
		object.addAttribute("Sex", new IRString("HOMME"));
		object.addAttribute("Pays", new IRString("FRANCE"));
		object.addAttribute("Contrat", new IRBoolean(false));
		object.addAttribute("V/N/D", new IRArray(
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
		
		object.accept(pp1);
		String ir1 = pp1.getText();
		pp1.print();
		pp1.clear();
		//new CompilatorIR(ir1).getValue().accept(pp1);
		
		String ir2 = pp1.getText();
		assertEquals(ir1, ir2);
		
		new IRInteger(10).accept(pp1);
		pp1.print();
		
	}
	
	@Test
	public void testJSON() {
		PrettyPrinterJSON pp1 = new PrettyPrinterJSON();
		
		JsonObject object = new JsonObject();
		object.addAttribute("Nom", new JsonString("RAUZIER"));
		object.addAttribute("Prenom", new JsonString("LEO"));
		object.addAttribute("Age", new JsonNumber(21));
		object.addAttribute("Sex", new JsonString("HOMME"));
		object.addAttribute("Pays", new JsonString("FRANCE"));
		object.addAttribute("Contrat", new JsonBoolean(false));
		object.addAttribute("V/N/D", new JsonArray(
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
		
		object.accept(pp1);
		//String json1 = pp1.getText();
		
		//pp1.print();
		pp1.clear();
		//new CompilatorJSON(json1).getValue().accept(pp1);
		
		//String ir2 = pp1.getText();
		//assertEquals(ir1, ir2);
	}
	
	
}
