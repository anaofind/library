package anaofind.lib.anadatair.sql;

import anaofind.lib.anadatair.visitor.VisitorSQL;

/**
 * sql condition where
 * @author anaofind
 *
 */
public class SQLConditionComparator implements SQLCondition{

	/**
	 * the name of column
	 */
	private String column;
	
	/**
	 * the operator
	 */
	private SQLOperator operator;
	
	/**
	 * the valuable
	 */
	private SQLValuable valuable;
	
	@Override
	public void accept(VisitorSQL visitor) {
		// TODO Auto-generated method stub
		
	}
}
