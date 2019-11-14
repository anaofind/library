package anaofind.lib.anadatair.json;

/**
 * json value
 * @author anaofind
 *
 */
public interface JsonConverter{

	/**
	 * to text json
	 * @return the json equivalents
	 */
	public String toTextJson();
	
	/**
	 * to text IR
	 * @return the ir text equivalent
	 */
	public String toTextIR();
	
}
