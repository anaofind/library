package anaofind.lib.anadatair.printer;

import anaofind.lib.anadatair.visitor.VisitorSQL;

/**
 * pretty printer sql
 * @author anaofind
 */
public class PrettyPrinterSQL extends PrettyPrinter implements VisitorSQL{

	/**
	 * construct
	 */
	public PrettyPrinterSQL() {
		super(' ');
	}
	
	public static void main(String[] args) {
		
	}

}
