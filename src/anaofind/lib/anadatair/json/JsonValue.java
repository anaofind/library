package anaofind.lib.anadatair.json;

import anaofind.lib.anadatair.visitor.VisitorJSON;

/**
 * json value
 * @author anaofind
 *
 */
public interface JsonValue{

	/**
	 * accept visitor json
	 * @param visitor the visitor json
	 */
	public void accept(VisitorJSON visitor);
	
}
