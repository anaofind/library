package anaofind.lib.anadatair.reader;

/**
 * reader
 * @author lrauzier
 *
 */
public interface Reader {

	/**
	 * read the next char
	 */
	public void readChar();
	
	/**
	 * read char without space
	 */
	default void readCharWithoutSpace() {
		this.readChar();
		while (! this.isEndReading() && this.isSpace()) {
			this.readChar();
		}
	}
	
	/**
	 * read the next word
	 */
	public void readWord();
	
	/**
	 * read the next line
	 */
	public void readLine();
	
	/**
	 * get the current char readed
	 * @return the current char readed
	 */
	public char currentChar();
	
	/**
	 * get the current word readed
	 * @return the current word readed
	 */
	public String currentWord();
	
	/**
	 * get the current line readed
	 * @return the current line readed
	 */
	public String currentLine();
		
	/**
	 * get length data text
	 * @return the length of data text
	 */
	public long length();
	
	/**
	 * get index char reading
	 * @return the index char reading
	 */
	public long indexChar();
	
	/**
	 * get index line
	 * @return the index line
	 */
	public long indexLine();
	
	/**
	 * get index column
	 * @return the index column
	 */
	public long indexColumn();
	
	/**
	 * current char is space
	 * @return boolean : true if current char is space | false else
	 */
	default boolean isSpace() {
		char c = this.currentChar();
		return (c == '\n' | c == ' ' | c == '\r' | c == '\t');
	}
	
	/**
	 * is start reading
	 * @return true if is start reading | false else
	 */
	default boolean isStartReading() {
		return (this.indexChar() > -1);
	}
	
	/**
	 * is end reading
	 * @return true if is end reading | false else
	 */
	default boolean isEndReading() {
		return (this.indexChar() >= this.length());
	}
	
	/**
	 * get progress reading
	 * @return the progress reading
	 */
	default double getProgressReading() {
		return ((double)this.indexChar() / (double)this.length());
	}
}
