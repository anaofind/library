package anaofind.lib.anaofile.file;

/**
 * classe permettant de représenter une donnée
 * @author anaofind
 *
 */
public class Data implements Comparable<Data>{
	
	/**
	 * le numero de la ligne
	 */
	private int line;
	
	/**
	 * la colonne
	 */
	private int column;
	
	/**
	 * la valeur
	 */
	private String value;
	
	/**
	 * constructeur
	 * @param line la ligne
	 * @param column la colonne
	 * @param value la valeur
	 */
	public Data(int line, int column, String value) {
		this.line = line;
		this.column = column;
		
		if (this.line < 1) {
			this.line = 1;
		}
		
		if (this.column < 1) {
			this.column = 1;
		}
		
		this.value = value;
	}

	/**
	 * getter line
	 * @return la ligne
	 */
	public int getLine() {
		return line;
	}

	/**
	 * getter coloumn
	 * @return la colonne
	 */
	public int getColumn() {
		return column;
	}
	
	/**
	 * getter value 
	 * @return la valeur
	 */
	public String getValue() {
		return value;
	}

	@Override
	public int compareTo(Data data) {
		
		if (this.line > data.line) {
			return 1;
		} else {
			if (this.line < data.line) {
				return -1;
			}
		}
		
		if (this.column > data.column) {
			return 1;
		} else {
			if (this.column < data.column) {
				return -1;
			}
		}
		
		return 0;
	}
	
	
}
