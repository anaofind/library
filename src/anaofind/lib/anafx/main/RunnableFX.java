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
	 * le runnable fx
	 */
	private static ReaderFX runnable;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		if (runnable != null) {
			runnable.action();
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
	 * setter runnable
	 * @param r le runnable
	 */
	public static void setRunnable(ReaderFX r) {
		runnable = r;
	}
}
