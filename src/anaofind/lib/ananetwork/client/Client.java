package anaofind.lib.ananetwork.client;

import java.io.*;
import java.net.*;

import anaofind.lib.analistener.Listener;
import anaofind.lib.ananetwork.NetworkElement;
import anaofind.lib.ananetwork.Network;
import anaofind.lib.ananetwork.query.Query;
import anaofind.lib.ananetwork.server.Server;

/**
 * classe Client
 * @author anaofind
 *
 */
public abstract class Client extends NetworkElement{
	
	/**
	 * le serveur oû le client est connecté
	 */
	private RelationClientServer server;
	
	/**
	 * socket liant le client au serveur
	 */
	private Socket socketToServer;
		
	/**
	 * boolean indiquant si le client est actif
	 */
	private boolean isActive = false;
		
	/**
	 * methode permettant de lancer le client
	 * @param adresseServeur l'adresse du serveur
	 */
	public static void start(String adresseServeur) {
		Client client = Network.getClient();
		if (client != null) {
			client.execute(adresseServeur);
		}
	}
	
	/**
	 * methode permettant de fermer un client
	 */
	public static void finish() {
		Client client = Network.getClient();
		if (client != null) {
			client.isActive = false;
			client.close();
		}
	}
	
	/**
	 * methode principale qui permet de gerer un client
	 * @param addressServer l'adresse du serveur
	 */
	public void execute (String addressServer){
		try {
			isActive = true;
			
			// creation de la socket
			socketToServer = new Socket(InetAddress.getByName(addressServer), Server.getlistenedPort());
			
			// creattion du reader
			BufferedReader reader = createReader(socketToServer);

			// creattion du printer
			PrintWriter printer = createPrinter(socketToServer);

			server = new RelationClientServer(printer, reader);
			
			starting();
			
			// creation de la thread de récéption de message
			Thread t = new Thread(new GetQuery());
			t.start();


		} catch (IOException e) {
			addMessageError(e.getMessage());
			isActive = false;	
		}

	}

	/**
	 * methode permettant de savoir si le client est actif ou pas
	 * @return boolean
	 */
	public static boolean isActive() {
		Client client = Network.getClient();
		if (client != null) {
			return client.isActive;	
		} 
		return false;
	}
	
	/**
	 * methode permettant de desactiver le client
	 */
	public static void disable() {
		Client client = Network.getClient();
		if (client != null) {
			client.isActive = false;	
		}
	}
	
	/**
	 * méthode permettant de deconnecter le client
	 */
	public void close(){

		try {
			finishing();
			socketToServer.close();
		} catch (IOException e) {
			addMessageError(e.getMessage());
		}
	}


	/**
	 * méthode permettant de creer un reader
	 * @param socketToClient la seocket liant le client au serveur
	 * @return le reader creé
	 * @throws IOException l'excption
	 */
	private static BufferedReader createReader(Socket socketToClient) throws IOException  {
		InputStream in = socketToClient.getInputStream();
		return new BufferedReader(new InputStreamReader(in, Server.charset));
	}

	/**
	 * méthode permettant de creer un printer
	 * @param socketToClient la socket liant le client au serveur
	 * @return le printer créé
	 * @throws IOException l'exception
	 */
	private static PrintWriter createPrinter(Socket socketToClient) throws IOException{
		OutputStream out = socketToClient.getOutputStream();
		return new PrintWriter(new OutputStreamWriter(out, Server.charset));
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
	public static void sendQuery(String plainQuery) throws IOException{
		Client client = Network.getClient();
		if (client != null) {
			client.server.getPrinter().println(plainQuery);
			client.server.getPrinter().flush();	
		}
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
				addMessageError("Communication server interrompt");
				isActive = false;
			}
		} 
		catch (IOException e) {
			addMessageError(e.getMessage());
			isActive = false;
		}
		catch (Exception e) {
			addMessageError(e.getMessage());
			isActive = false;
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

	/**
	 * methode permettant d'ajouter un message info au tampon
	 * @param message le message
	 */
	public static void addMessageInformation(String message){
		Client client = Network.getClient();
		if (client != null) {
			client.addMessage(CODE_INFORMATION, message);
		}
	}
	
	/**
	 * methode permettant d'ajouter un message d'alerte au tampon
	 * @param message le message
	 */
	public static void addMessageWarning(String message){
		Client client = Network.getClient();
		if (client != null) {
			client.addMessage(CODE_WARNING, message);
		}
	}

	/**
	 * methode permettant d'ajouter un message d'erreur au tampon
	 * @param message le message
	 */
	public static void addMessageError(String message){
		Client client = Network.getClient();
		if (client != null) {
			client.addMessage(CODE_ERROR, message);
		}
	}
	
	/**
	 * methode permettant d'ajouter un message de question au tampon
	 * @param message le message de question
	 */
	public static void addMessageQuestion(String message){
		Client client = Network.getClient();
		if (client != null) {
			client.addMessage(CODE_QUESTION, message);
		}
	}

	/**
	 * méthode permettant de récuperer le premier message alerte du tampon
	 * @return le premier message alerte du tampon
	 */
	public static String getFirstMessageInformation(){
		Client client = Network.getClient();
		if (client != null) {
			return client.getFirstMessage(CODE_INFORMATION);
		}
		return null;
	}
	
	/**
	 * méthode permettant de récuperer le premier message alerte du tampon
	 * @return le premier message alerte du tampon
	 */
	public static String getFirstMessageWarning(){
		Client client = Network.getClient();
		if (client != null) {
			return client.getFirstMessage(CODE_WARNING);
		}
		return null;
	}
	
	/**
	 * méthode permettant de récuperer le premier message d'erreur du tampon
	 * @return le premier message erreur du tampon
	 */
	public static String getFirstMessageError(){
		Client client = Network.getClient();
		if (client != null) {
			return client.getFirstMessage(CODE_ERROR);
		}
		return null;
	}
	
	/**
	 * methode permettant de recuperer la premiere question
	 * @return la premiere question
	 */
	public static String getFirstMessageQuestion() {
		Client client = Network.getClient();
		if (client != null) {
			return client.getFirstMessage(CODE_QUESTION);
		}
		return null;
	}
	
	/**
	 * methode permettant de recuperer la reponse de la question
	 * @return la reponse a la question
	 */
	public static String getFirstAnswerMessageQuestion(){
		Client client = Network.getClient();
		if (client != null) {
			return client.getFirstMessage(CODE_ANSWER_QUESTION);
		}
		return null;
	}
	
	/**
	 * methode permettant d'envoyer la reponse à la question
	 * @param answer la reponse à la question
	 */
	public static void sendAnswerQuestion(String answer) {
		Client client = Network.getClient();
		if (client != null) {
			client.addMessage(CODE_ANSWER_QUESTION, answer);
		}
	}
	
	/**
	 * methode permettant d'ajouter un écouteur
	 * @param listener l'écouteur
	 */
	public static void addOneListener(Listener listener) {
		Client client = Network.getClient();
		if (client != null) {
			client.addListener(listener);
		}
	}
	
	/**
	 * methode permettant de supprimer un ecouteur
	 * @param listener l'ecouteur
	 */
	public static void removeOneListener(Listener listener) {
		Client client = Network.getClient();
		if (client != null) {
			client.removeListener(listener);
		}
	}
	
	/**
	 * methode permettant de prevenir les écouteurs;
	 * @param code le code d'action
	 */
	public static void updateAllListenable(int code) {
		Client client = Network.getClient();
		if (client != null) {
			client.addAction(code);
			client.updateListenable();
		}
	}

}
