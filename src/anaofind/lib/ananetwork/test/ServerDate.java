package anaofind.lib.ananetwork.test;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

import anaofind.lib.ananetwork.AnaServer;
import anaofind.lib.ananetwork.UtilNetwork;

public class ServerDate extends AnaServer{
	
	public ServerDate() {
		super(8888, 5000);
	}
	
	@Override
	public void processMessage(Socket socket, String message) {
		
	}

	@Override
	public void actionLoop() {
//		for (Socket client : this.listClient) {
//			try {
//				UtilNetwork.sendMessage(client, new Date().toString());
//			} catch (IOException e) {
//				System.out.println("ERREUR");
//			}
//		}
	}

	
	public static void main(String[] args) {
		AnaServer server = new ServerDate();
		server.start();
	}
	
	
}
