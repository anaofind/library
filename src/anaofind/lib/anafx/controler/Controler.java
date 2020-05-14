package anaofind.lib.anafx.controler;

import java.net.URL;
import java.util.ResourceBundle;

import anaofind.lib.anafx.screen.Screen;
import anaofind.lib.analistener.Listenable;
import anaofind.lib.analistener.Listener;
import javafx.application.Platform;
import javafx.fxml.Initializable;

/**
 * methode abstraite de controle
 * @author anaofind
 *
 */
public abstract class Controler implements Initializable, Listener{
		
	/**
	 * screen of controler
	 */
	protected Screen screen;
	
	/**
	 * boolean indicate if screen is open
	 */
	private boolean screenOpen = true;
	
	/**
	 * methode executer la boucle
	 */
	public abstract void stepLoop();
	
	/**
	 * methode permettant de preparer le controleur
	 */
	public abstract void starting();
	
	/**
	 * methode permettant de recuperer les fps
	 * @return les fps
	 */
	public abstract int getFps();
	
	/**
	 * methode permettant de savoir si c'est la fin de la boucle
	 * @return boolean
	 */
	public abstract boolean isEndLoop();
	
	/**
	 * methode permettant de finir le controleur
	 */
	public abstract void finishing();
	
	/**
	 * close screen -> screenOpen variable is false
	 */
	public void screenClose() {
		this.screenOpen = false;
	}
	
	/**
	 * setter screen
	 * @param screen the screen
	 */
	public void setScreen(Screen screen) {
		this.screen = screen;
	}
	 
	/**
	 * methode d'initialisation
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		starting();
		new Thread( () -> { 
			try {
				while (screenOpen && !isEndLoop()) {
					Platform.runLater( () -> {
						stepLoop();
					});
					Thread.sleep(1000/getFps());
				}
				Platform.runLater( () -> {
					finishing();
				});
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} ).start(); 
	}
	
	/**
	 * permettant d'actualiser l'observable
	 * @param listenable l'écoutable
	 * @param code le code de l'action
	 */
	public abstract void updateListenable(Listenable listenable, int code);
	
	@Override
	public void listen(Listenable listenable, int code) {
		updateListenable(listenable, code);
	}
}
