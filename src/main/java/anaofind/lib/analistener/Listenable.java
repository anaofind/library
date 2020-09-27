package anaofind.lib.analistener;

import java.util.ArrayList;

/**
 * classe ecoutable
 * @author anaofind
 *
 */
public abstract class Listenable {

	/**
	 * liste d'�couteur
	 */
	private ArrayList<Listener> listeners = new ArrayList<Listener>();
	
	/**
	 * la liste des codes d'actions r�aliser
	 */
	private ArrayList<Action> actions = new ArrayList<Action>();
	
	/**
	 * methode permettant d'ajouter un �couteur
	 * @param listener l'�couteur � ajouter
	 */
	public void addListener(Listener listener) {
		if (! listeners.contains(listener)) {
			listeners.add(listener);
		}
	}
	
	/**
	 * methode permettant d'enlever un �couteur
	 * @param listener l'�couteur � enlever
	 */
	public void removeListener(Listener listener) {
		listeners.remove(listener);
	}
	
	/**
	 * methode permettant d'ajouter une action qui a �t� r�alis�
	 * @param code le code de l'action
	 */
	public void addAction(int code) {
		Action a = new Action(code);
		if (actions.contains(a)) {
			actions.remove(a);
		}
		actions.add(a);
	}
	
	/**
	 * methode permettant d'enlever une action
	 * @param code le code de l'action
	 */
	public void removeAction(int code) {
		Action a = new Action(code);
		actions.remove(a);
	}
	
	/**
	 * methode permettant d'actualiser les �coutables
	 */
	public void updateListenable() {
		
		Listener[] te = listeners.toArray(new Listener[listeners.size()]);
		Action[] ta = actions.toArray(new Action[actions.size()]);
		for (Listener ecouteur: te) {
			for (Action action : ta) {
				ecouteur.listen(this, action.getCode());
			}
		}
		actions.clear();
	}
}
