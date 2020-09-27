package anaofind.lib.anaofile.tests;

import anaofind.lib.anaofile.AnaoFile;

/**
 * tests on anaofile
 * @author anaofind
 */
public class TestAnaoFile {

	public static void main(String[] args) {
	
		AnaoFile file = new AnaoFile("D:/musique");
		
		for (AnaoFile f : file.filesRec()) {
			System.out.println(f);
		}	
	}
}
