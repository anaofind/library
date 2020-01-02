package anaofind.lib.anadatair.sql;

import anaofind.lib.anadatair.visitor.VisitorSQL;

/**
 * sql valuable number
 * @author anaofind
 */
public class SQLNumber implements SQLValuable{

	/**
	 * the number value
	 */
	private double number;
	
	/**
	 * construct 
	 * @param number the number value
	 */
	public SQLNumber(double number) {
		this.number = number;
	}
	
	@Override
	public void accept(VisitorSQL visitor) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * getter number
	 * @return the number
	 */
	public double getNumber() {
		return number;
	}

}
