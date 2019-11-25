package anaofind.lib.anadatair.visitor;

import anaofind.lib.anadatair.sql.*;

/**
 * visitor sql
 * @author anaofind
 *
 */
public interface VisitorSQL extends Visitor{
	
	/**
	 * visit select
	 * @param select the select
	 */
	public void visitSelectSQL(SQLSelect select);
	
	/**
	 * visit where
	 * @param where the where
	 */
	public void visitWhereSQL(SQLWhere where);
	
	/**
	 * visit order by
	 * @param orderBy the order by
	 */
	public void visitOrderBySQL(SQLOrderBy orderBy);
	
	/**
	 * visit number
	 * @param number the number
	 */
	public void visitNumberSQL(SQLNumber number);
	
	/**
	 * visit string
	 * @param string the string
	 */
	public void visitStringSQL(SQLString string);
}
