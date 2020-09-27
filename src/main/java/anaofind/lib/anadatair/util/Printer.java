package anaofind.lib.anadatair.util;

/**
 * function util for printer
 * @author anaofind
 */
public class Printer {

	/**
	 * get tablation string
	 * @param tabulation the nb of tabulation
	 * @return the tabulation String
	 */
	public static String tabulation(int tabulation) {
		String tab = "";
		for(int i = 0; i<tabulation; i++) {
			tab += "\t";
		}
		return tab;
	}

}
