package anaofind.lib.ananetwork.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

/**
 * the network
 * anaofind
 */
public class UtilNetwork {		
	/**
	 * méthode permettant de creer un reader
	 * @param socket la socket
	 * @param charset le type de données
	 * @return le reader
	 * @throws IOException l'exception
	 */
	private static BufferedReader createReader(Socket socket) throws IOException  {
		InputStream in = socket.getInputStream();
		return new BufferedReader(new InputStreamReader(in));
	}

	/**
	 * méthode permettant de creer un printer
	 * @param socket la socket
	 * @param charset le type de donnée
	 * @return le printer créé
	 * @throws IOException l'exception
	 */
	private static PrintWriter createPrinter(Socket socket) throws IOException{
		OutputStream out = socket.getOutputStream();
		return new PrintWriter(new OutputStreamWriter(out));	
	}


	/**
	 * send message 
	 * @param socket the destinatory of message
	 * @param message the message to send
	 */
	public static void sendMessage(Socket socket, String message) throws IOException{
		PrintWriter printer = createPrinter(socket);
		printer.println(message);
		printer.flush();
	}

	/**
	 * read message 
	 * @param socket the author of message
	 * @throws IOException 
	 */
	public static String[] readMessage(Socket socket, int timeout) throws IOException {
		List<String> messages = new ArrayList<String>();
		int timeoutSocket = socket.getSoTimeout();
		try {
			BufferedReader reader = createReader(socket);
			socket.setSoTimeout(timeout);
			String line = reader.readLine();
			while (line != null) {
				messages.add(line);
				line = reader.readLine();
			}
		} 
		catch (InterruptedIOException e) { }
		socket.setSoTimeout(timeoutSocket);
		return messages.toArray(new String[messages.size()]);
	}
}
