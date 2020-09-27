package anaofind.lib.anafx.main;

import java.util.List;

/**
 * classe permettant d'executer une application fx
 * @author anaofind
 *
 */
public abstract class ReaderFX {

	/**
	 * methode permettant de definir les actions de lancement
	 */
	public abstract void action(List<String> args);
	
	/**
	 * methode permettant de lancer le support
	 * @param args les arguments
	 */
	public void start(String[] args) {
		RunnableFX.setReader(this);
		RunnableFX.run(args);
	}
	
}
