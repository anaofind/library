package anaofind.lib.anadatair.compilator;

import java.util.Objects;

/**
 * compilator data
 * @author anaofind
 */
public class Compilator {

	protected class CompilatorException extends Exception {
		/**
		 * serial version
		 */
		private static final long serialVersionUID = 1L;

		public CompilatorException(String message) {
			super(message + " -> char : \'" + currentChar + "\' [" + (indexChar-1) + "]");
		}
	}
	
	/**
	 * the text data
	 */
	private String dataText;
	
	/**
	 * the index char
	 */
	private int indexChar = -1;
	
	/**
	 * the current char
	 */
	protected char currentChar = ' ';

	/**
	 * length of data text
	 */
	private int lengthDataText;
		
			
	public Compilator(String dataText) {
		Objects.requireNonNull(dataText);
		this.dataText = dataText;
		this.lengthDataText = dataText.length();
	}
	
	/**
	 * read the char
	 * @return the char readed
	 */
	public void readChar() {
		this.indexChar++;
		if (! this.isEndRead()) {
			this.currentChar = dataText.charAt(this.indexChar);
		}
	}
	
	/**
	 * read the car not space
	 */
	public void readCharWithoutSpace() {
		this.readChar();
		while (! this.isEndRead() && this.isSpace()) {
			this.readChar();
		}
	}
	
	/**
	 * is space
	 * @return boolean : true if char is space | false else
	 */
	public boolean isSpace() {
		return (currentChar == ' ' || currentChar == '\t' || currentChar == '\n' || currentChar == '\r');
	}
	
	/**
	 * is end read the data
	 * @return boolean : true if is end | false else
	 */
	public boolean isEndRead() {
		return (this.indexChar >= this.lengthDataText);
	}
	
	/**
	 * getter current char
	 * @return the current char
	 */
	public char getCurrentChar() {
		return this.currentChar;
	}
		
	/**
	 * remove space in text
	 * @param textSpace the text with space
	 * @return the text without space
	 */
	public static String removeSpace(String textSpace) {
		String textWithoutSpace = "";
		Compilator cmp = new Compilator(textSpace);
		cmp.readCharWithoutSpace();
		while (! cmp.isEndRead()) {
			textWithoutSpace += cmp.currentChar;
			cmp.readCharWithoutSpace();
		}
		return textWithoutSpace;
	}
	
	/**
	 * call exception
	 * @param message the message
	 * @throws CompilatorException 
	 */
	public void callException(String message) throws CompilatorException {
		throw new CompilatorException(message);
	}
}
