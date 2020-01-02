package anaofind.lib.anadatair.sql;

import anaofind.lib.anadatair.visitor.VisitorSQL;

/**
 * sql id column selected
 * @author anaofind
 *
 */
public class SQLColumnSelectedID extends SQLColumnSelected{

	/**
	 * construct
	 * @param columnSelected the name of column selected
	 */
	public SQLColumnSelectedID(String columnSelected) {
		super(columnSelected);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(VisitorSQL visitor) {
		// TODO Auto-generated method stub
		
	}
}

