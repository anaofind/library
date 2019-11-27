package anaofind.lib.anadatair;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	private static final String REGEX_DOUBLE = "^-?\\d+\\.?\\d*E?-?\\d*$";
	
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
	public static boolean isDouble(String data){
		return data.matches(REGEX_DOUBLE);
	}
	
	/**
	 * methode permettant de savoir si une donnée est un entier
	 * @param data la donnée
	 * @return boolean indiquant si la donnée est un entier ou non
	 */
	public static boolean isInteger(String data){
		return data.matches(REGEX_INTEGER);
	}
	
	/**
	 * methode permettant de savoir si une donnée est une date
	 * @param data la donnée
	 * @return boolean
	 */
	public static boolean isDate(String data) {
		return data.matches(REGEX_DATE);
	}
	
	/**
	 * is boolean
	 * @param data the data
	 * @return boolean : true if data is boolean | false else
	 */
	public static boolean isBoolean(String data) {
		return data.matches(REGEX_BOOLEAN);
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
