package anaofind.lib.anaoweb;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;


/**
 * classe permettant d'executer des actions util sur le web
 * @author anaofind
 *
 */
public class UtilWeb {


	/**
	 * methode permettant d'ouvire un page internet
	 * @param url l'url du site
	 */
	public static void openSite(String url) {
		if(Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(new URI(url));
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
		}	
	}

	/**
	 * methode permettant d'envoyer une requete GET
	 * @param url l'url du site
	 * @param params les paramètres de la requete
	 * @return le resultat de la requete
	 */
	public static String sendQueryGet(String url){		
		String source ="";
		URL oracle;
		try {
			oracle = new URL(url);
			URLConnection yc = oracle.openConnection();
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							yc.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null)
				source +=inputLine;
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return source;
	}

	/**
	 * methode permettant d'envoyer une requete http post
	 * @param address l'adresse
	 * @param params les parametres avec valeurs
	 * @return la reponse
	 */
	public static String sendQueryPost(String address, HashMap<String, String> params){
		String result = "";
		OutputStreamWriter writer = null;
		BufferedReader reader = null;
		try {
			//encodage des paramètres de la requête
			String data="";
			String[] keys = params.keySet().toArray(new String[params.size()]);
			for(int i=0;i<keys.length;i++){
				if (i!=0) data += "&";
				data +=URLEncoder.encode(keys[i], "UTF-8")+"="+URLEncoder.encode(params.get(keys[i]), "UTF-8");
			}
			//création de la connection
			URL url = new URL(address);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);

			//envoi de la requête
			writer = new OutputStreamWriter(conn.getOutputStream());
			writer.write(data);
			writer.flush();

			//lecture de la réponse
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String ligne;
			while ((ligne = reader.readLine()) != null) {
				result+=ligne;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{writer.close();}catch(Exception e){}
			try{reader.close();}catch(Exception e){}
		}
		System.out.println(result);
		return result;
	}

}
