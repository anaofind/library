package anaofind.lib.anaoweb;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import anaofind.lib.anadatair.Anadatair;


/**
 * util method for web action
 */
public class UtilWeb {


	/**
	 * open web site on navigator
	 * @param url the url of web site
	 */
	public static void openInNavigator(String url) {
		if(Desktop.isDesktopSupported()) {
			try {
				Desktop.getDesktop().browse(new URI(url));
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
		}	
	}

	/**
	 * send request get http
	 * @param url the url
	 * @return the response
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static String sendRequestGet(String url) throws IOException, InterruptedException{		
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
        		.uri(URI.create(url))
        		.build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
	}

	/**
	 * send request post http
	 * @param url the url
	 * @param body the body
	 * @return the response
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static String sendRequestPost(String url, Anadatair body) throws IOException, InterruptedException{
		HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(body.toJson().toString()))
                .build();

        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
		return response.body();
	}

}
