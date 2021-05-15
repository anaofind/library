package anaofind.lib.analistener;

import java.util.ArrayList;

/**
 * listenable
 * @author anaofind
 *
 */
public abstract class Listenable {

	/**
	 * the listeners
	 */
	private ArrayList<Listener> listeners = new ArrayList<Listener>();
	
	/**
	 * the code of actions to execute
	 */
	private ArrayList<Action> actions = new ArrayList<Action>();
	
	/**
	 * add listener
	 * @param listener the listener to added
	 */
	public void addListener(Listener listener) {
		if (! listeners.contains(listener)) {
			listeners.add(listener);
		}
	}
	
	/**
	 * remove listener
	 * @param listener the listener to removed
	 */
	public void removeListener(Listener listener) {
		listeners.remove(listener);
	}
	
	/**
	 * add action code
	 * @param code the code of action to added
	 */
	public void addAction(int code) {
		Action a = new Action(code);
		if (actions.contains(a)) {
			actions.remove(a);
		}
		actions.add(a);
	}
	
	/**
	 * remove action code
	 * @param code the code of action to removed
	 */
	public void removeAction(int code) {
		Action a = new Action(code);
		actions.remove(a);
	}
	
	/**
	 * update listenable
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
