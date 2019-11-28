package anaofind.lib.anadatair.reader;

import java.util.Objects;

/**
 * reader text
 * @author lrauzier
 *
 */
public class ReaderText implements Reader{

	/**
	 * the text
	 */
	private String text;
	
	/**
	 * the index char
	 */
	private int indexChar = -1;
	
	/**
	 * the index column
	 */
	private int indexColumn = -1;
	
	/**
	 * the index line
	 */
	private int indexLine = 0;
	
	/**
	 * length of data text
	 */
	private int lengthText;
	
	/**
	 * the current char
	 */
	private char currentChar = ' ';
	
	/**
	 * construct
	 * @param text the text
	 */
	public ReaderText(String text) {
		Objects.requireNonNull(text);
		this.text = text;
		this.lengthText = text.length();
	}
	
	@Override
	public void readChar() {
		this.indexChar++;
		if (! this.isEndReading()) {
			this.currentChar = this.text.charAt(this.indexChar);
		}
	}

	@Override
	public void readCharWithoutSpace() {
		this.readChar();
		while (! this.isEndReading() && this.isSpace()) {
			this.readChar();
		}
	}

	@Override
	public char currentChar() {
		return this.currentChar;
	}

	@Override
	public int length() {
		return this.lengthText;
	}

	@Override
	public boolean isStartReading() {
		return (this.currentChar > -1);
	}

	@Override
	public boolean isEndReading() {
		return (this.indexChar >= this.lengthText);
	}

	@Override
	public double getProgressReading() {
		return this.indexChar / this.lengthText;
	}

	@Override
	public int[] getIndexLineColumn() {
		int[] lineColumn = new int[2];
		lineColumn[0] = this.indexLine;
		lineColumn[1] = this.indexColumn;
		return lineColumn;
	}

}
