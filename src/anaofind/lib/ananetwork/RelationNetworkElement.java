package anaofind.lib.ananetwork;

import java.io.BufferedReader;
import java.io.PrintWriter;

import java.util.*;

import anaofind.lib.ananetwork.exception.*;

public class RelationNetworkElement {

	/**
	 * the printer of netawork element
	 */
	private PrintWriter printer;
	
	/**
	 * the reader of network element
	 */
	private BufferedReader reader;
	
	/**
	 * true if network element is connected
	 */
	private boolean connected;
	
	/**
	 * map of data on network element
	 */
	private Map<String, String> datas = new HashMap<String, String>();
	
	/**
	 * constructeur
	 * @param printer le printer
	 * @param reader le reader
	 */
	public RelationNetworkElement(PrintWriter printer, BufferedReader reader){
		Objects.requireNonNull(printer);
		Objects.requireNonNull(reader);
		this.printer = printer;
		this.reader = reader;
		this.connected = true;
	}
	
	/**
	 * add data on network element
	 * @param name the name of data
	 * @param value the value of data
	 */
	public void addDatas(String name, String value) {
		Objects.requireNonNull(name);
		Objects.requireNonNull(value);
		datas.put(name, value);
	}
	
	/**
	 * get data on network element
	 * @param dataName the name of data
	 * @return the data
	 * @throws DataNotFoundException if name of data not found
	 */
	public String getData(String dataName) throws DataNotFoundException{
		if (! this.datas.containsKey(dataName)) {
			throw new DataNotFoundException(dataName);
		}
		return this.datas.get(dataName);
	}
	
	/**
	 * getter connecte
	 * @return boolean true if connected | fale else
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
