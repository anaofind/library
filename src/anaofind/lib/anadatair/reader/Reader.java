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
	public void readCharWithoutSpace();
	
	/**
	 * get the current char readed
	 * @return the current char readed
	 */
	public char currentChar();

	/**
	 * get length data text
	 * @return the length of data text
	 */
	public int length();
	
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
	public boolean isStartReading();
	
	/**
	 * is end reading
	 * @return true if is end reading | false else
	 */
	public boolean isEndReading();
	
	/**
	 * get progress reading
	 * @return the progress reading
	 */
	public double getProgressReading();
	
	/**
	 * get index line and column
	 * @return array int [line,column]
	 */
	public int[] getIndexLineColumn();
	
}
