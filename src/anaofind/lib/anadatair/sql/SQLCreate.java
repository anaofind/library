package anaofind.lib.anadatair.sql;

import anaofind.lib.anadatair.visitor.VisitorSQL;

/**
 * sql value create
 * @author anaofind
 */
public class SQLCreate implements SQLValue{

	/**
	 * the table name
	 */
	private String table;
	
	/**
	 * 
	 * @param table
	 */
	public SQLCreate(String table) {
		
	}
	
	@Override
	public void accept(VisitorSQL visitor) {
		
	}

}
