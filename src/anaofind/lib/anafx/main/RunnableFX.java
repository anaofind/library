package anaofind.lib.anafx.main;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * classe permettant de servir de support pour les application fx
 * @author anaofind
 *
 */
public class RunnableFX extends Application{

	/**
	 * le reader
	 */
	private static ReaderFX reader;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		if (reader != null) {
			reader.action();
		}
	}
	
	/**
	 * methode d'excecution
	 * @param args les arguments
	 */
	public static void run(String[] args) {
		launch(args);
	}
	
	/**
	 * setter reader
	 * @param r le reader
	 */
	public static void setReader(ReaderFX r) {
		reader = r;
	}
}
