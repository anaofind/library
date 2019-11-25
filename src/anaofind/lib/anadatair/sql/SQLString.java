package anaofind.lib.anadatair.sql;

import java.util.Objects;

import anaofind.lib.anadatair.visitor.VisitorSQL;

/**
 * sql value string
 * @author anaofind
 */
public class SQLString implements SQLValue{

	/**
	 * the value
	 */
	private String value;
	
	/**
	 * construct
	 * @param value the value
	 */
	public SQLString(String value) {
		Objects.requireNonNull(value);
		this.value = value;
	}
	
	/**
	 * getter value
	 * @return the value
	 */
	public String getValue() {
		return this.value;
	}
	
	@Override
	public void accept(VisitorSQL visitor) {
		visitor.visitStringSQL(this);
	}

}
