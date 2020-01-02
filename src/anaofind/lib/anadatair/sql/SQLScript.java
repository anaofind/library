package anaofind.lib.anadatair.sql;

import anaofind.lib.anadatair.visitor.VisitorSQL;

import java.util.*;

/**
 * script sql
 * @author anaofind
 */
public class SQLScript implements SQLValue{

	/**
	 * the queries of script
	 */
	SQLQuery[] queries;
	
	/**
	 * construct
	 * @param queries the queries of script
	 */
	public SQLScript(SQLQuery...queries) {
		Objects.requireNonNull(queries);
		this.queries = queries;
	}
	
	@Override
	public void accept(VisitorSQL visitor) {
		// TODO Auto-generated method stub
		
	}
	
}
