package anaofind.lib.ananetwork.client;

import java.io.*;
import java.net.*;

import anaofind.lib.ananetwork.*;
import anaofind.lib.ananetwork.query.*;

/**
 * classe Client
 * @author anaofind
 *
 */
public abstract class Client extends NetworkElement{
	
	/**
	 * le serveur oû le client est connecté
	 */
	private RelationNetworkElement server;
	
	/**
	 * socket liant le client au serveur
	 */
	private Socket socketToServer;
		
	@Override
	public String getRole() {
		return "client";
	}
	
	/**
	 * methode principale qui permet de gerer un client
	 * @param addressServer l'adresse du serveur
	 */
	public void execute (String addressServer, int port){
		try {
			this.enable();
			socketToServer = new Socket(InetAddress.getByName(addressServer), port);
			BufferedReader reader = createReader(socketToServer);
			PrintWriter printer = createPrinter(socketToServer);
			server = new RelationNetworkElement(printer, reader);
			starting();
			Thread t = new Thread(new GetQuery());
			t.start();
		} catch (IOException e) {
			this.addMessageError(e.getMessage());
			this.disable();	
		}
	}

		
	/**
	 * méthode permettant de deconnecter le client
	 */
	public void close(){
		finishing();
		try {
			socketToServer.close();
		} catch (IOException e) {
			this.addMessageError(e.getMessage());
		}
		this.disable();
	}

	/**
	 * méthode permettant de récupérer les message recut
	 * @return le message recut
	 * @throws IOException l'exception
	 */
	private String receiveQuery() throws IOException{
		String message = server.getReader().readLine();
		return message;
	}

	/**
	 * méthode permettant d'envoyer une requete
	 * @param plainQuery la requete brut
	 * @throws IOException l'exception
	 */
	public void sendQuery(String plainQuery) throws IOException{
		this.server.getPrinter().println(plainQuery);
		this.server.getPrinter().flush();
	}

	/**
	 * méthode permettant de traiter les requetes du serveur
	 */
	public void processQuery(){
		String plainQuery;
		try {
			plainQuery = receiveQuery();
			if (plainQuery != null) {
				Query requete = Query.parseQuery(plainQuery);
				processQuery(requete);	
			} else {
				this.addMessageError("Communication server interrompt");
				this.disable();
			}
		} 
		catch (IOException e) {
			this.addMessageError(e.getMessage());
			this.disable();
		}
		catch (Exception e) {
			this.addMessageError(e.getMessage());
			this.disable();
		}
	}

	/**
	 * methode permettant de traiter une requete
	 * @param query la requete
	 */
	public void processQuery(Query query) {
		if (query != null) {
			int header = query.getHeader();
			String[] arguments = query.getArguments();
			
			this.executeQuery(header, arguments);
		}
	}

	/**
	 * méthode permettant d'executer une requete
	 * @param header l'entete
	 * @param arguments l'arguments
	 */
	public abstract void executeQuery(int header, String[] arguments);
	
}
