package anaofind.lib.anadatair.sql;

import anaofind.lib.anadatair.visitor.VisitorSQL;

/**
 * sql value number
 * @author lrauzier
 */
public class SQLNumber implements SQLValue{

	/**
	 * the value
	 */
	private double value;
	
	/**
	 * the type
	 */
	private String type;
	
	/**
	 * construct
	 * @param value the value double
	 */
	public SQLNumber(double value) {
		this.type = "double";
		this.value = value;
	}
	
	/**
	 * construct
	 * @param value the value integer
	 */
	public SQLNumber(int value) {
		this.type = "integer";
		this.value = value;
	}
	
	/**
	 * getter value
	 * @return the value
	 */
	public double getValue() {
		return this.value;
	}
	
	/**
	 * getter type
	 * @return the type
	 */
	public String getType() {
		return this.type;
	}
	
	@Override
	public void accept(VisitorSQL visitor) {
		visitor.visitNumberSQL(this);
	}

}
