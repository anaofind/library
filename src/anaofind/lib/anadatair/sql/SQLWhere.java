package anaofind.lib.anadatair.sql;

import java.util.Objects;

import anaofind.lib.anadatair.visitor.VisitorSQL;

/**
 * query where
 * @author anaofind
 *
 */
public class SQLWhere implements SQLValue{

	/**
	 * the name column
	 */
	private String column;
	
	/**
	 * the operator
	 */
	private String operator;
	
	/**
	 * the comparable
	 */
	private SQLValue comparable;
	
	/**
	 * constrruct
	 * @param column the name of column
	 * @param op the operation 
	 * @param value the value
	 */
	public SQLWhere(String column, String op, SQLValue comparable) {
		Objects.requireNonNull(column);
		Objects.requireNonNull(op);
		Objects.requireNonNull(comparable);
		
		this.column = column;
		this.operator = op;
		this.comparable = comparable;
	}

	/**
	 * getter column 
	 * @return the column
	 */
	public String getColumn() {
		return column;
	}

	/**
	 * getter operator
	 * @return the operator
	 */
	public String getOp() {
		return operator;
	}

	/**
	 * getter comparable
	 * @return the comparable
	 */
	public SQLValue getComparable() {
		return comparable;
	}

	@Override
	public void accept(VisitorSQL visitor) {
		visitor.visitWhereSQL(this);
	}

}
