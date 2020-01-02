package anaofind.lib.anadatair.sql;

import java.util.Objects;

import anaofind.lib.anadatair.visitor.VisitorSQL;

/**
 * sql table id
 * @author anaofind
 */
public class SQLTableID implements SQLTable{

	/**
	 * the id of table
	 */
	private String id;
	
	/**
	 * construct
	 * @param id the id of table
	 */
	public SQLTableID(String id) {
		Objects.requireNonNull(id);
		this.id = id;
	}
	
	@Override
	public void accept(VisitorSQL visitor) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * getter id 
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
}
