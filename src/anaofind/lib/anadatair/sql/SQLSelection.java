package anaofind.lib.anadatair.sql;

import anaofind.lib.anadatair.visitor.VisitorSQL;

import java.util.*;

/**
 * sql selection
 * @author anaofind
 *
 */
public class SQLSelection implements SQLQuery, SQLTable{
	
	/**
	 * distinct result
	 */
	private boolean distinct;	
	
	/**
	 * the tables
	 */
	private SQLTable[] tables;
	
	/**
	 * the column selected
	 */
	private SQLColumnSelected[] columnsSelected; 
	
	/**
	 * the conditions
	 */
	private SQLCondition[] conditions;
	
	
	@Override
	public void accept(VisitorSQL visitor) {
		// TODO Auto-generated method stub
		
	}

}
