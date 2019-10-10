package anaofind.lib.anaoweb;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * classe permettant d'interagire avec le web
 * @author anaofind
 *
 */
public class Web {

	/**
	 * la base de l'url
	 */
	private static String base;
	
	/**
	 * le chemin de l'url
	 */
	private static ArrayList<String> paths = new ArrayList<String>();
	
	/**
	 * les parametres avec les valeurs
	 */
	private static HashMap<String, String> parameters = new HashMap<String, String>();
	
	/**
	 * methode permettant de changer l'url base du site
	 * @param baseUrl l'adresse du serveur
	 */
	public static void setBase(String basePath) {
		base = "http://" + basePath;
		clean();
	}
	
	/**
	 * methode permettant d'ajouter un chemins
	 * @param arrayPath le tableau du chemin
	 */
	public static void addPath(String...arrayPath) {
		for (String c: arrayPath) {
			paths.add(c);
		}
	}

	/**
	 * methode permettant d'ajouter un paramètres 
	 * @param id l'id
	 * @param value la valeur
	 */
	public static void addParameters(String id, String value) {
		parameters.put(id, value);
	}
	
	/**
	 * methode permettant de netoyer l'url des paramètres et du chemin 
	 */
	public static void clean() {
		paths.clear();
		parameters.clear();
	}
	
	/**
	 * methode permettant de netoyer seulement les parametres
	 */
	public static void cleanParameters() {
		parameters.clear();	
	}
	
	/**
	 * methode permettant de creer une url avec le chemin et les paramètres
	 * @return l'url complet du site
	 */
	private static String getFullUrl() {	
		String url = base;
		
		if (paths != null) {
			for (String c: paths) {
				url += "/" + c;
			}	
		}
		
		String[] ids = parameters.keySet().toArray(new String[parameters.size()]);
		if (ids.length > 0 ) {
			url += "?";
		}
		for (String id: ids) {
			url += id + "=" + parameters.get(id) + "&";
		}
		if (ids.length > 0) {
			url = url.substring(0, url.length() - 1);	
		}
		return url;
	}
		
	/**
	 * methode permettant d'ouvire un page internet
	 */
	public static void openUrlInDefaultNavigator() {
		UtilWeb.openSite(getFullUrl());
		clean();
	}
	
	/**
	 * methode permettant d'evnoyer requete post
	 * @return le retour du post
	 */
	public static String sendPost() {
		String res = UtilWeb.sendQueryPost(getFullUrl(), parameters);
		return res;
	}
	
	
	/**
	 * methode permettant d'evnoyer requete get
	 * @return le retour du post
	 */
	public static String sendGet() {
		String res = UtilWeb.sendQueryGet(getFullUrl());
		return res;
	}

}
