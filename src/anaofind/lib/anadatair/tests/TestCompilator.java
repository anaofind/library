package anaofind.lib.anadatair.tests;

import static org.junit.Assert.*;

import org.junit.*;

import anaofind.lib.anadatair.compilator.CompilatorIR;
import anaofind.lib.anadatair.compilator.CompilatorJSON;
import anaofind.lib.anadatair.ir.*;
import anaofind.lib.anadatair.json.*;
import anaofind.lib.anadatair.printer.*;
import anaofind.lib.anadatair.reader.*;

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

		pp1.print();
		pp2.print();
		assertEquals(pp1.getText(), pp2.getText());
	}

	@Test
	public void testRealJSON() {
		String json1 = "{\r\n" + 
				"	\"fileUpdateRate\" : 5,\r\n" + 
				"	\"routeFile\" : \"../simulation/config/automne.gpx\",\r\n" + 
				"	\"simPath\" : \"C:/Users/anaofind/Desktop/projet java/projet/Etudes/UCTL2_Sim/build/libs/UCTL2.jar\",\r\n" + 
				"	\"teams\" : [ \r\n" + 
				"		{\r\n" + 
				"			\"pace\" : 376,\r\n" + 
				"			\"name\" : \"Miss Vincenza Heathcote\",\r\n" + 
				"			\"bibNumber\" : 1\r\n" + 
				"		},\r\n" + 
				"		{\r\n" + 
				"			\"pace\" : 252,\r\n" + 
				"			\"name\" : \"Nicolas Haag\",\r\n" + 
				"			\"bibNumber\" : 2\r\n" + 
				"		},\r\n" + 
				"		{\r\n" + 
				"			\"pace\" : 618,\r\n" + 
				"			\"name\" : \"Anita Auer\",\r\n" + 
				"			\"bibNumber\" : 3\r\n" + 
				"		},\r\n" + 
				"		{\r\n" + 
				"			\"pace\" : 568,\r\n" + 
				"			\"name\" : \"Madonna Ritchie\",\r\n" + 
				"			\"bibNumber\" : 4\r\n" + 
				"		},\r\n" + 
				"		{\r\n" + 
				"			\"pace\" : 458,\r\n" + 
				"			\"name\" : \"Lindsay Lebsack\",\r\n" + 
				"			\"bibNumber\" : 5\r\n" + 
				"		},\r\n" + 
				"		{\r\n" + 
				"			\"pace\" : 284,\r\n" + 
				"			\"name\" : \"Justus Kihn\",\r\n" + 
				"			\"bibNumber\" : 6\r\n" + 
				"		},\r\n" + 
				"		{\r\n" + 
				"			\"pace\" : 278,\r\n" + 
				"			\"name\" : \"Annabelle Rempel MD\",\r\n" + 
				"			\"bibNumber\" : 7\r\n" + 
				"		},\r\n" + 
				"		{\r\n" + 
				"			\"pace\" : 331,\r\n" + 
				"			\"name\" : \"Abraham Heaney\",\r\n" + 
				"			\"bibNumber\" : 8\r\n" + 
				"		},\r\n" + 
				"		{\r\n" + 
				"			\"pace\" : 345,\r\n" + 
				"			\"name\" : \"Mrs. Skye Swaniawski\",\r\n" + 
				"			\"bibNumber\" : 9\r\n" + 
				"		},\r\n" + 
				"		{\r\n" + 
				"			\"pace\" : 588,\r\n" + 
				"			\"name\" : \"Damian Heathcote IV\",\r\n" + 
				"			\"bibNumber\" : 10\r\n" + 
				"		}\r\n" + 
				"	],\r\n" + 
				"	\"tickStep\" : 10,\r\n" + 
				"	\"raceName\" : \"Automne\",\r\n" + 
				"	\"startTime\" : 1.5748092E12,\r\n" + 
				"	\"api\" : {\r\n" + 
				"		\"baseUrl\" : \"http://127.0.0.1/projets/uctl2_api/\",\r\n" + 
				"		\"actions\" : {\r\n" + 
				"			\"setupRace\" : \"setup/race\",\r\n" + 
				"			\"updateTeams\" : \"update/race/teams\",\r\n" + 
				"			\"updateRaceStatus\" : \"update/race/status\"\r\n" + 
				"		}\r\n" + 
				"	},\r\n" + 
				"	\"segments\" : [ \r\n" + 
				"		100,\r\n" + 
				"		1000,\r\n" + 
				"		1000,\r\n" + 
				"		1000,\r\n" + 
				"		1000,\r\n" + 
				"		1000,\r\n" + 
				"		1000,\r\n" + 
				"		1000,\r\n" + 
				"		1000,\r\n" + 
				"		1000,\r\n" + 
				"		1000,\r\n" + 
				"		1000,\r\n" + 
				"		1000,\r\n" + 
				"		1000,\r\n" + 
				"		1000,\r\n" + 
				"		1000,\r\n" + 
				"		1000,\r\n" + 
				"		1000,\r\n" + 
				"		1000,\r\n" + 
				"		1000,\r\n" + 
				"		1000,\r\n" + 
				"		1000,\r\n" + 
				"		1000\r\n" + 
				"	],\r\n" + 
				"	\"raceFile\" : \"../simulation/race.csv\"\r\n" + 
				"}";

		CompilatorJSON cmp = new CompilatorJSON(json1);
		JsonValue value = cmp.getValue();
		PrettyPrinterJSON pp = new PrettyPrinterJSON();
		value.accept(pp);
		assertEquals(UtilReader.removeSpace(json1).length(), UtilReader.removeSpace(pp.getText()).length());
	}


}
