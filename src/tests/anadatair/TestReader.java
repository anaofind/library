package tests.anadatair;

import org.junit.jupiter.api.Test;

import anaofind.lib.anadatair.reader.*;

/**
 * test reader
 * @author anaofind
 */
public class TestReader {

	@Test
	public void readerLine() {
		// on charge le fichier
		Reader r = new ReaderFile("C:\\Users\\leo_r\\Desktop\\tps\\tips.txt");
		// on lit la premiere ligne
		r.readLine();
		// tant que le fichier n'a pas été lu entirement
		while (! r.isEndReading()) {
			// on affiche la ligne courrante
			System.out.println(r.currentLine());
			// on lit la ligne suivante
			r.readLine();
		}
		// on affiche la derniere ligne
		System.out.println(r.currentLine());
	}
	
}
