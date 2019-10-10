package anaofind.lib.anafx.main;

/**
 * classe permettant d'executer une application fx
 * @author anaofind
 *
 */
public abstract class ReaderFX {

	/**
	 * methode permettant de definir les actions de lancement
	 */
	public abstract void action();
	
	/**
	 * methode permettant de lancer le support
	 * @param args les arguments
	 */
	public void start(String[] args) {
		RunnableFX.setRunnable(this);
		RunnableFX.run(args);
	}
	
}
