package anaofind.lib.anaoweb;

import java.io.IOException;
import anaofind.lib.anadatair.Anadatair;

/**
 * web class
 * @author anaofind
 */
public class Web {
	
	/**
	 * le chemin de l'url
	 */
	private String url = "";
	
	/**
	 * the body
	 */
	private Anadatair body;
	

	/**
	 * set url
	 * @param url the url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * set body
	 * @param body the body
	 */ 
	public void setBody(Anadatair body) {
		this.body = body;
	}
		
	/**
	 * open on nagitator
	 */
	public void openInNavigator() {
		UtilWeb.openInNavigator(this.url);
	}
	
	/**
	 * send post
	 * @return the response
	 */
	public String sendPost() {
		try {
			return UtilWeb.sendRequestPost(this.url, this.body);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * methode permettant d'evnoyer requete get
	 * @return le retour du post
	 */
	public String sendGet() {
		try {
			return UtilWeb.sendRequestGet(this.url);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

}
