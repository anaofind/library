package anaofind.lib.ananetwork.client;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * classe définissant la liason entre le client et le serveur
 * @author anaofind
 *
 */
public class RelationClientServer {
	/**
	 * le printer du serveur
	 */
	private PrintWriter printer;
	
	/**
	 * le reader du serveur
	 */
	private BufferedReader reader;
	
	/**
	 * constructeur
	 * @param printer le printer
	 * @param reader le reader
	 */
	public RelationClientServer(PrintWriter printer, BufferedReader reader){
		this.printer = printer;
		this.reader = reader;
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
}
