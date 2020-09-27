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
	private long indexChar = -1;
	
	/**
	 * the index line
	 */
	private long indexLine = 0;
	
	/**
	 * the index column
	 */
	private long indexColumn = -1;
	
	/**
	 * length of data text
	 */
	private long lengthText;
	
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
	public long indexChar() {
		return this.indexChar;
	}

	@Override
	public long indexLine() {
		return this.indexLine;
	}

	@Override
	public long indexColumn() {
		return this.indexColumn;
	}
	
	@Override
	public long length() {
		return this.lengthText;
	}
}
