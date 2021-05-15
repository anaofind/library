package anaofind.lib.analistener;

/**
 * the action
 * @author anaofind
 *
 */
public class Action {

	/**
	 * the code 
	 */
	private int code;
	
	/**
	 * constructor
	 * @param code the code
	 */
	public Action(int code) {
		this.code = code;
	}

	/**
	 * getter code
	 * @return the code
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
