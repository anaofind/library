package anaofind.lib.anadatair.sql;

import java.util.Objects;

import anaofind.lib.anadatair.visitor.VisitorSQL;

/**
 * sql value order by
 * @author anaofind
 */
public class SQLOrderBy implements SQLValue{

	/**
	 * the default order
	 */
	private static final String DEFAULT_ORDER = "ASC";
	
	/**
	 * the column
	 */
	private String column;
	
	/**
	 * the order
	 */
	private String order = "ASC";
	
	/**
	 * construct 
	 * @param column the column name
	 */
	public SQLOrderBy(String column) {
		this(column, DEFAULT_ORDER);
	}
	
	/**
	 * construct
	 * @param column the column name
	 * @param order the order
	 */
	public SQLOrderBy(String column, String order) {
		Objects.requireNonNull(column);
		Objects.requireNonNull(order);
		this.column = column;
		this.order = order;
	}

	/**
	 * getter column
	 * @return the column name
	 */
	public String getColumn() {
		return column;
	}

	/**
	 * getter order
	 * @return the order
	 */
	public String getOrder() {
		return order;
	}

	@Override
	public void accept(VisitorSQL visitor) {
		visitor.visitOrderBySQL(this);
	}

}
