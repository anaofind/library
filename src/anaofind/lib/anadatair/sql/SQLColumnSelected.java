package anaofind.lib.anadatair.sql;

import java.util.Objects;

/**
 * sql column selected
 * @author anaofind
 */
public abstract class SQLColumnSelected implements SQLValue{
	
	/**
	 * the name of column selected
	 */
	private String columnSelected;

	/**
	 * construct
	 * @param columnSelected the name of column selected
	 */
	public SQLColumnSelected(String columnSelected) {
		Objects.requireNonNull(columnSelected);
		this.columnSelected = columnSelected;
	}

	/**
	 * getter column selected name
	 * @return the name of column selected
	 */
	public String getColumnSelected() {
		return columnSelected;
	}
}
