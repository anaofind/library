package anaofind.lib.anadatext.data;

import java.util.Objects;

/**
 * compilator data
 * @author anaofind
 */
public abstract class DataCompilator {

	/**
	 * the text data
	 */
	private String dataText;
	
	/**
	 * the index char
	 */
	private int indexChar;
	
	/**
	 * the current char
	 */
	protected char currentChar = ' ';

	/**
	 * length of data text
	 */
	private int lengthDataText;
	
	
	public DataCompilator(String dataText) {
		Objects.requireNonNull(dataText);
		this.dataText = dataText;
		this.lengthDataText = dataText.length();
	}
	
	/**
	 * read the char
	 * @return the char readed
	 */
	protected void readChar() {
		if (! this.isEndRead()) {
			this.currentChar = dataText.charAt(this.indexChar);
			this.indexChar++;
		}
	}
	
	/**
	 * read the car not space
	 */
	protected void readCharWithoutSpace() {
		if (! this.isEndRead()) {
			this.readChar();
			while (currentChar == ' ') {
				this.readChar();
			}
		}
	}
	
	/**
	 * is end read the data
	 * @return boolean : true if is end | false else
	 */
	protected boolean isEndRead() {
		return (this.indexChar >= this.lengthDataText);
	}
}
