package anaofind.lib.anadatair.sql;

import anaofind.lib.anadatair.visitor.VisitorSQL;

import java.util.*;

/**
 * sql select
 * @author anaofind
 */
public class SQLSelect implements SQLValue{

	/**
	 * the columns
	 */
	public List<String> columns = new ArrayList<String>();

	/**
	 * the tables used
	 */
	private Set<String> tables = new HashSet<String>();

	/**
	 * the conditions
	 */
	private List<SQLValue> conditions = new ArrayList<SQLValue>();

	/**
	 * construct 
	 * @param tables the tables used
	 */
	public SQLSelect(String...tables) {
		Objects.requireNonNull(tables);
		try {
			if (tables.length > 0) {
				for (String table : tables) {
					this.tables.add(table);
				}
			} else {
				throw new Exception("require minimum 1 table : " + tables.length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * add columns
	 * @param table the table name
	 * @param columns the columns
	 */
	public void addColumns(String table, String...columns) {
		try {
			if (this.tables.contains(table)) {
				for (String column : columns) {
					this.columns.add(table + '.' + column);
				}	
			} else {
				throw new Exception("table not used : " + table);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * add condition
	 * @param condition the condition
	 */
	public void addCondition(SQLValue...conditions) {
		for (SQLValue condition : conditions) {
			this.conditions.add(condition);
		}
	}
	
	/**
	 * getter tables
	 * @return the tables name
	 */
	public Set<String> getTables() {
		return Collections.unmodifiableSet(this.tables);
	}

	/**
	 * getter conditions
	 * @return the conditions
	 */
	public List<SQLValue> getConditions() {
		return Collections.unmodifiableList(this.conditions);
	}

	/**
	 * getter columns
	 * @return the columns
	 */
	public List<String> getColumns() {
		return Collections.unmodifiableList(this.columns);
	}

	@Override
	public void accept(VisitorSQL visitor) {
		visitor.visitSelectSQL(this);
	}

}
