package anaofind.lib.ananetwork.server;

import java.net.*;

import anaofind.lib.ananetwork.Network;

/**
 * classe permettant de traiter un client en thread
 * @author anaofind
 *
 */
public class ProcessClient implements Runnable{
	
	/** 
	 * la socket liant le serveur et le client
	 */
	Socket socketToClient;
	
	
	/**
	 * constructeur
	 * @param socket la socket
	 */
	public ProcessClient(Socket socket){
		socketToClient = socket;
	}
	
	/**
	 * méthode qui permet de lancer le traitement du client au serveur
	 */
	@Override
	public void run() {
		Network.getServer().processSocketClient(socketToClient);
	}
}
