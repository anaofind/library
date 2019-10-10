package anaofind.lib.analistener;

/**
 * classe action
 * @author anaofind
 *
 */
public class Action {

	/**
	 * le code de l'action
	 */
	private int code;
	
	/**
	 * constrcteur
	 * @param code le code de l'action
	 */
	public Action(int code) {
		this.code = code;
	}

	/**
	 * getter code
	 * @return le code de l'action
	 */
	public int getCode() {
		return code;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Action) {
			Action a = (Action)obj;
			return a.code == this.code;
		}
		return false;
	}
	
	
	
}
