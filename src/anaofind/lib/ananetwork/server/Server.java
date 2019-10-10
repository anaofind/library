package anaofind.lib.ananetwork.server;

import java.net.*;
import java.io.*;
import java.util.concurrent.*;

import anaofind.lib.analistener.Listener;
import anaofind.lib.ananetwork.NetworkElement;
import anaofind.lib.ananetwork.Network;
import anaofind.lib.ananetwork.query.Query;

/**
 * classe Serveur
 * @author anaofind
 *
 */
public abstract class Server extends NetworkElement{
		
	/**
	 * serveur singleton
	 */
	private static Server server;
	
	/**
	 * la socket du serveur
	 */
	private ServerSocket socketServer;

	/**
	 * boolean indiquant si le serveur est en marche ou pas
	 */
	private boolean current = false;

	/**
	 * le charset des données
	 */
	public static String charset = "UTF-8";

	/**
	 * le nombre max de clients
	 */
	private static int MAX_CLIENT = 10;

	/**
	 * le port du serveur
	 */
	private static int LISTENED_PORT = 8888;
		
	/**
	 * methode permettant de changer le port
	 * @param listenedPort le port utilise
	 */
	public void setPort(int listenedPort) {
		LISTENED_PORT = listenedPort;
	}
	
	/**
	 * methode permettant de recuperer le port d'attente
	 * @return le port utilisé
	 */
	public static int getlistenedPort() {
		return LISTENED_PORT;
	}
	
	/**
	 * methode permettant de changer le nombre max d'un client
	 * @param maxClient le nombre max de client
	 */
	public void setMaxClient(int maxClient) {
		MAX_CLIENT = maxClient;
	}
	
	/**
	 * methode permettant de creer une liaison serveur client
	 * @param ipClient l'ip du client
	 * @param printer le printer
	 * @param reader le reader
	 * @return la liaison serveur client
	 */
	public abstract RelationServerClient createRelationServerClient(String ipClient, PrintWriter printer, BufferedReader reader);
		
	/**
	 * methode permettant de realiser une action lors de la connexion d'un client
	 * @param client le client
	 */
	public abstract void loginClient(RelationServerClient client);
	
	/**
	 * methode permettant de realiser une action lors de la deconnexion d'un client
	 * @param client le client
	 */
	public abstract void logoutClient(RelationServerClient client);
		
	/**
	 * methode permettant de lancer le serveur
	 */
	public static void start() {
		server = Network.getServer();
		if (server != null) {
			server.execute();
		}
	}
	
	/**
	 * methode permettant de fermer le serveur
	 */
	public static void finish() {
		if (server != null) {
			server.current = false;	
		}
	}
	
	/**
	 * methode permettant de savoir si le serveur est en cours ou pas
	 * @return boolean
	 */
	public static boolean isCurrent() {
		if (server != null) {
			return server.current;	
		}
		return false;
	}
	
	/**
	 * méthode permettant d'initialiser le serveur
	 */
	private void init(){

		starting();
		
		try {
			socketServer = new ServerSocket(LISTENED_PORT);
			socketServer.setSoTimeout(1000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	/**
	 * méthode principale qui permet de gerer le serveur
	 */
	private void execute() {
		
		// on initialise la variable de début du serveur
		current = true;
		
		// on initialise le serveur
		init();
	
		// on initialise le nombre max de client
		Executor service = Executors.newFixedThreadPool(MAX_CLIENT);
		
		// initialisation de la variable de fin
		boolean end = false;
	
		System.out.println("wait login ...");
	
		while (!end) {	
			try {
				
				// on recupere le client
				Socket socketClient = socketServer.accept();
	
				// on traite le client
				service.execute(new ProcessClient(socketClient));
	
			} 
			catch (java.io.InterruptedIOException e ){
				if (! current) {
					// fermeture socket
					close();
					end = true;
				}
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
	
	
		}
	}

	/**
	 * methode permettant de fermer le serveur
	 */
	private void close() {
		try {
			finishing();
			socketServer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}


	/**
	 * méthode permettant de traiter un client
	 * @param socketToclient la socket liant le serveur au client
	 */
	public void processSocketClient(Socket socketToclient) {
		try {

			String ipClient = socketToclient.getInetAddress().toString();
			System.out.println("login : " +  socketToclient.getInetAddress());

			// creation du printer
			PrintWriter printer = createPrinter(socketToclient, charset);

			// creation du reader
			BufferedReader reader = createReader(socketToclient, charset);


			// creation du client
			RelationServerClient client = createRelationServerClient(ipClient, printer, reader);
			
			// action sur la connexion d'un client
			loginClient(client);

			while (client.isConnected() && current) {					
				// Traitement du message
				processQuery(client);
			}
			
			// action sur la deconnexion d'un client
			logoutClient(client);

			// on ferme la socket
			socketToclient.close();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * méthode permettant de creer un reader
	 * @param socketToClient la socket liant le serveur et le client
	 * @param charset le type de données
	 * @return le reader
	 * @throws IOException l'exception
	 */
	private static BufferedReader createReader(Socket socketToClient, String charset) throws IOException  {
		InputStream in = socketToClient.getInputStream();
		return new BufferedReader(new InputStreamReader(in, charset));
	}

	/**
	 * méthode permettant de creer un printer
	 * @param socketToClient la socket liant le client au serveur
	 * @param charset le type de donnée
	 * @return le printer créé
	 * @throws IOException l'exception
	 */
	private static PrintWriter createPrinter(Socket socketToClient, String charset) throws IOException{
		OutputStream out = socketToClient.getOutputStream();
		return new PrintWriter(new OutputStreamWriter(out, charset));
	}


	/**
	 * méthode permettant de traiter les requetes d'un client
	 * @param client le client
	 */
	public void processQuery(RelationServerClient client){
		String requeteBrut;
		try {
			requeteBrut = receiveQuery(client);
			if (requeteBrut != null) {
				Query requete = Query.parseQuery(requeteBrut);
				processQuery(requete, client);	
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(client.getIp() + "> " + e.getMessage());
			client.disconnect();
		}
	}

	/**
	 * méthode permettant de recevoir une requete du client
	 * @param client le client
	 * @return la requete reçut
	 * @throws IOException l'exception
	 */
	private String receiveQuery(RelationServerClient client) throws IOException{
		String requete = client.getReader().readLine();
		return requete;
	}

	/**
	 * méthode permettant d'envoyer une requete au client
	 * @param client le client
	 * @param plainQuery la requete
	 */
	public void sendQuery(RelationServerClient client, String plainQuery){
		if (client != null) {
			client.getPrinter().println(plainQuery);
			client.getPrinter().flush();
		}
	}

	/**
	 * methode permettant de traiter une requete
	 * @param query la requete
	 * @param client le client
	 */
	private void processQuery(Query query, RelationServerClient client) {
		if (query != null) {
			int entete = query.getHeader();
			String[] arguments = query.getArguments();
			
			executeQuery(entete, arguments, client);
		}
	}
	
	/**
	 * méthode permettant d'executer une requete
	 * @param header l'entete
	 * @param arguments les arguments
	 * @param client le client
	 */
	public abstract void executeQuery(int header, String[]  arguments, RelationServerClient client);
	
	
	/**
	 * methode permettant d'ajouter un observeur
	 * @param listener l'écouteur
	 */
	public static void addOneListener(Listener listener) {
		Server server = Network.getServer();
		if (server != null) {
			server.addListener(listener);
		}
	}
	
	/**
	 * methode permettant de supprimer un observeur
	 * @param listener l'écouteur
	 */
	public static void removeOneListener(Listener listener) {
		Server server = Network.getServer();
		if (server != null) {
			server.removeListener(listener);
		}
	}
	
	/**
	 * methode permettant de prevenir les écouteurs.
	 * @param code le code d'action
	 */
	public static void updateAllListenable(int code) {
		Server server = Network.getServer();
		if (server != null) {
			server.addAction(code);
			server.updateListenable();
		}
	}
}


