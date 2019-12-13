package anaofind.lib.anadatair.reader;
/**
 * reader abstract implements
 * @author anaofind
 *
 */
public abstract class ReaderImpl implements Reader{
	
	/**
	 * the index char
	 */
	private int indexChar = -1;
	
	/**
	 * the index line
	 */
	private int indexLine = 0;
	
	/**
	 * the index column
	 */
	private int indexColumn = -1;
	
	/**
	 * length of data text
	 */
	private int lengthText;
	
	/**
	 * the current char
	 */
	private char currentChar = ' ';
	
	/**
	 * the current char
	 */
	private String currentWord = "";
	
	/**
	 * the current line
	 */
	private String currentLine = "";
			
	/**
	 * the next char
	 * @return the next char
	 */
	public abstract char nextChar();
	
	@Override
	public void readChar() {
		this.indexChar ++;
		if (! this.isEndReading()) {
			this.indexColumn ++;
			if (this.currentChar == '\n') {
				this.indexColumn = 0;
				this.indexLine ++;
			}
			this.currentChar = this.nextChar();
		}
	}
	
	@Override
	public void readWord() {
		this.currentWord = "";
		this.readChar();
		if (this.isSpace()) {
			this.readCharWithoutSpace();
		}
		while (! this.isEndReading() && ! this.isSpace()) {
			this.currentWord += this.currentChar();
			this.readChar();
		}
	}

	@Override
	public void readLine() {
		this.currentLine = "";
		this.readChar();
		while (! this.isEndReading() && this.currentChar() != '\n') {
			this.currentLine += this.currentChar();
			this.readChar();
		}
	}
	
	@Override
	public char currentChar() {
		return this.currentChar;
	}
	
	@Override
	public String currentWord() {
		return this.currentWord;
	}

	@Override
	public String currentLine() {
		return this.currentLine;
	}

	@Override
	public int indexChar() {
		return this.indexChar;
	}

	@Override
	public int indexLine() {
		return this.indexLine;
	}

	@Override
	public int indexColumn() {
		return this.indexColumn;
	}
	
	@Override
	public int length() {
		return this.lengthText;
	}
}
