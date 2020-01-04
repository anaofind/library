package anaofind.lib.anadatair.tests;

import org.junit.Test;

import anaofind.lib.anadatair.reader.*;

/**
 * test reader
 * @author anaofind
 */
public class TestReader {

	@Test
	public void readerLine() {
		Reader r = new ReaderFile("C:\\Users\\anaofind\\Desktop\\Etudes\\cours\\Master\\M1\\BDD\\TP\\trip_data_1.csv");
		r.readChar();
		System.out.println(r.length());
		
	}
	
}
