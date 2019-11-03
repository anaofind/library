package anaofind.lib.ananetwork.server;

import java.net.*;
import java.io.*;
import java.util.concurrent.*;

import anaofind.lib.ananetwork.*;
import anaofind.lib.ananetwork.exception.DataNotFoundException;
import anaofind.lib.ananetwork.query.*;

/**
 * classe Serveur
 * @author anaofind
 *
 */
public abstract class Server extends NetworkElement{
			
	/**
	 * la socket du serveur
	 */
	private ServerSocket socketServer;

	/**
	 * le nombre max de clients
	 */
	private int maxNbClient = 10;

	/**
	 * le port du serveur
	 */
	private int port = 8888;
		
	@Override
	public String getRole() {
		return "server";
	}
	
	/**
	 * methode permettant de changer le port
	 * @param listenedPort le port utilise
	 */
	public void setPort(int listenedPort) {
		port = listenedPort;
	}
		
	/**
	 * methode permettant de changer le nombre max d'un client
	 * @param maxClient le nombre max de client
	 */
	public void setMaxClient(int maxClient) {
		maxNbClient = maxClient;
	}
	
	/**
	 * methode permettant de creer une liaison serveur client
	 * @param ipClient l'ip du client
	 * @param printer le printer
	 * @param reader le reader
	 * @return la liaison serveur client
	 */
	public RelationNetworkElement createRelationClient(String ipClient, PrintWriter printer, BufferedReader reader) {
		RelationNetworkElement relationClient = new RelationNetworkElement(printer, reader);
		relationClient.addDatas("ip", ipClient);
		return relationClient;
	}
		
	/**
	 * methode permettant de realiser une action lors de la connexion d'un client
	 * @param client le client
	 */
	public abstract void connectClient(RelationNetworkElement client);
	
	/**
	 * methode permettant de realiser une action lors de la deconnexion d'un client
	 * @param client le client
	 */
	public abstract void disconnectClient(RelationNetworkElement client);
	
	/**
	 * méthode permettant d'initialiser le serveur
	 */
	private void init(){
		starting();
		try {
			socketServer = new ServerSocket(port);
			socketServer.setSoTimeout(1000);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * méthode principale qui permet de gerer le serveur
	 */
	public void execute() {
		this.enable();
		init();
		Executor service = Executors.newFixedThreadPool(maxNbClient);
		boolean end = false;
		System.out.println("wait login ...");
		while (!end) {	
			try {
				Socket socketClient = socketServer.accept();
				service.execute(new ProcessClient(socketClient));
			} 
			catch (java.io.InterruptedIOException e ){
				if (! this.isActive()) {
					close();
					end = true;
				}
			}
			catch (IOException e) {
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
			this.disable();
		} catch (IOException e) {
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
			PrintWriter printer = createPrinter(socketToclient);
			BufferedReader reader = createReader(socketToclient);
			RelationNetworkElement client = createRelationClient(ipClient, printer, reader);
			connectClient(client);

			while (client.isConnected() && this.isActive()) {					
				processQuery(client);
			}
			
			disconnectClient(client);
			socketToclient.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}


	/**
	 * méthode permettant de traiter les requetes d'un client
	 * @param client le client
	 */
	public void processQuery(RelationNetworkElement client){
		String requeteBrut;
		try {
			requeteBrut = receiveQuery(client);
			if (requeteBrut != null) {
				Query requete = Query.parseQuery(requeteBrut);
				processQuery(requete, client);	
			}
		} catch (IOException e) {
			try {
				System.out.println(client.getData("ip") + "> " + e.getMessage());
			} catch (DataNotFoundException e1) {
				client.disconnect();
				e1.printStackTrace();
			}
			client.disconnect();
		}
	}

	/**
	 * méthode permettant de recevoir une requete du client
	 * @param client le client
	 * @return la requete reçut
	 * @throws IOException l'exception
	 */
	private String receiveQuery(RelationNetworkElement client) throws IOException{
		String requete = client.getReader().readLine();
		return requete;
	}

	/**
	 * méthode permettant d'envoyer une requete au client
	 * @param client le client
	 * @param plainQuery la requete
	 */
	public void sendQuery(RelationNetworkElement client, String plainQuery){
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
	private void processQuery(Query query, RelationNetworkElement client) {
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
	public abstract void executeQuery(int header, String[]  arguments, RelationNetworkElement client);
	
}


