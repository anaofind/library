package anaofind.lib.ananetwork.server;

import java.io.BufferedReader;
import java.io.PrintWriter;

import anaofind.lib.analistener.Listenable;

/**
 * classe définissant la liason entre le serveur et le client
 * @author anaofind
 *
 */
public abstract class RelationServerClient extends Listenable{
	
	/**
	 * l'ip du client
	 */
	private String ip;
	
	/**
	 * le printer du client
	 */
	private PrintWriter printer;
	
	/**
	 * le reader du client
	 */
	private BufferedReader reader;
	
	/**
	 * vrai si le client est connecté
	 */
	private boolean connected;
	
	/**
	 * constrcuteur
	 * @param ip l'ip
	 * @param printer le printer
	 * @param reader le reader
	 */
	public RelationServerClient(String ip, PrintWriter printer, BufferedReader reader){
		this.ip = ip;
		this.printer = printer;
		this.reader = reader;
		this.connected = true;
	}
	
	/**
	 * getter ip
	 * @return l'ip
	 */
	public String getIp() {
		return ip;
	}
	
	/**
	 * getter printer
	 * @return le printer
	 */
	public PrintWriter getPrinter() {
		return printer;
	}

	/**
	 * getter reader
	 * @return le reader
	 */
	public BufferedReader getReader() {
		return reader;
	}

	/**
	 * getter connecte
	 * @return vrai si le client est connecté
	 */
	public boolean isConnected() {
		return connected;
	}

	/**
	 * methode permettant de déconnecter un client
	 */
	public void disconnect() {
		this.connected = false;
	}
	
}
