package anaofind.lib.anaofile.tests;

import java.io.File;

import anaofind.lib.anadatair.parser.Parser;
import anaofind.lib.anadatair.reader.ReaderFile;

public class TestParserFile extends Parser{

	public TestParserFile() {
		super(new ReaderFile(new File("C:\\Users\\anaofind\\Desktop\\file\\data.prn")), Parser.DATAS_LINE);
	}

	@Override
	public void preparation() {
	}

	@Override
	public void finishing() {
	}

	@Override
	public void executeAction(String datas) {
		System.out.println("data read = " + datas + " fin");
		//System.exit(1);
	}

	public static void main(String[] args) {
		Parser parser = new TestParserFile();
		parser.toParse(3);
		System.out.println("sa marche pas");
	}
	
}
