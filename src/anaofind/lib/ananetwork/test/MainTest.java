package anaofind.lib.ananetwork.test;

import anaofind.lib.ananetwork.AnaClient;
import anaofind.lib.ananetwork.AnaServer;

/**
 * main test
 * @author anaofind
 */
public class MainTest {
	

	public static void main(String[] args) {
		try {
			AnaServer server = new ServerDate();
			AnaClient client1 = new ClientDate();
			AnaClient client2 = new ClientDate();
			client1.bind("127.0.0.1", 8888);
			
			
			
			System.out.println("\n... STATE 1 ... \n");
			server.startThread();
			client1.startThread();
			Thread.sleep(5000);
			server.close();
			Thread.sleep(2000);
			
			server.startThread();
			
			System.out.println("\n... STATE 2 ... \n");
			client2.startThread();
			Thread.sleep(5000);
			server.close();
			Thread.sleep(2000);
			
			System.out.println("\n... STATE 3 ... \n");
			server.startThread();
			Thread.sleep(5000);
			server.close();
			Thread.sleep(2000);
			
			System.out.println("\n... STATE 4 ... \n");
			server.startThread();
			Thread.sleep(5000);
			server.close();
			client1.close();
			Thread.sleep(2000);
			
			System.out.println("\n... STATE 5 ... \n");
			server.startThread();
			Thread.sleep(5000);
			server.close();
			
			client2.close();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
