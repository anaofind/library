package anaofind.lib.anajson;

/**
 * json numeric
 * @author anaofind
 *
 */
public class JsonInteger implements JsonValue{

	/**
	 * the value
	 */
	private int value;
	
	/**
	 * construct
	 * @param value the value
	 */
	public JsonInteger(int value) {
		this.value = value;
	}
	
	@Override
	public String getString() {
		return "" + this.value;
	}

}
