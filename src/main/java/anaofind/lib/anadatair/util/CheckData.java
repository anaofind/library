package anaofind.lib.anadatair.util;

/**
 * classe permettant de faire toute sorte de vérification sur une donnée
 * @author anaofind
 *
 */
public class CheckData {

	/**
	 * regex integer
	 */
	private static final String REGEX_INTEGER = "^(-?\\d+|\\d+\\.\\d+E\\d+)$" ;
	
	/**
	 * regex double
	 */
	private static final String REGEX_DOUBLE = "^(-?\\d*\\.?\\d*E?-?\\d*)$";
	
	/**
	 * regex boolean
	 */
	private static final String REGEX_BOOLEAN = "^(?:0|1|false|true|False|True)$";
	
	/**
	 * regex date
	 */
	private static final String REGEX_DATE = "^[0-9]{4}-[0-9]{2}-[0-9]{2}Z$";
	
	
	/**
	 * methode permettant de savoir si une donnée est un double
	 * @param data la donnée
	 * @return boolean indiquant si la donnée est un double ou non
	 */
	public static boolean isDouble(String...datas){
		for (String data : datas) {
			if (! data.matches(REGEX_DOUBLE)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * methode permettant de savoir si une donnée est un entier
	 * @param data la donnée
	 * @return boolean indiquant si la donnée est un entier ou non
	 */
	public static boolean isInteger(String...datas){
		for (String data : datas) {
			if (! data.matches(REGEX_INTEGER)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * methode permettant de savoir si une donnée est une date
	 * @param data la donnée
	 * @return boolean
	 */
	public static boolean isDate(String...datas) {
		for (String data : datas) {
			if (! data.matches(REGEX_DATE)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * is boolean
	 * @param data the data
	 * @return boolean : true if data is boolean | false else
	 */
	public static boolean isBoolean(String...datas) {
		for (String data : datas) {
			if (! data.matches(REGEX_BOOLEAN)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * methode permettant de savoir si une donnée est de la bonne taille
	 * @param data la donnée
	 * @param sizeMin la taille min
	 * @param sizeMax la taille max
	 * @return boolean indiquant si la donnée est de bonne taille
	 */
	public static boolean isGoodSize(String data, int sizeMin, int sizeMax){
		int size = data.length();
		return (size >= sizeMin && size <= sizeMax);
	}
}
