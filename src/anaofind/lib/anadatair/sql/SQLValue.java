package anaofind.lib.anadatair.sql;

import anaofind.lib.anadatair.visitor.VisitorSQL;

/**
 * sql value
 * @author anaofind
 */
public interface SQLValue {
	
	/**
	 * accept visitor sql
	 * @param visitor the visitor sql
	 */
	public void accept(VisitorSQL visitor);
	
}
