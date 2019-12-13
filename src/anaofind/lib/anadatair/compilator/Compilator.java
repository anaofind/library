package anaofind.lib.anadatair.compilator;

import java.io.File;
import java.util.Objects;

import anaofind.lib.anadatair.reader.*;

/**
 * parser data
 * @author anaofind
 */
public class Compilator implements Reader{

	/**
	 * exception compilator
	 * @author lrauzier
	 */
	public class CompilatorException extends Exception {
		/**
		 * serial version
		 */
		private static final long serialVersionUID = 1L;

		/**
		 * construct
		 * @param message the message
		 */
		public CompilatorException(String message) {
			super(message + " -> char : \'" + reader.currentChar() + "\' [" + reader.indexLine() + "," + reader.indexColumn() + "]");
		}
	}
	
	/**
	 * the reader
	 */
	private Reader reader;
	
	/**
	 * construct 
	 * @param dataText the data text
	 */
	public Compilator(String dataText) {
		this(new ReaderText(dataText));
	}
	
	/**
	 * construct
	 * @param file the file
	 */
	public Compilator(File file) {
		this(new ReaderFile(file));
	}
	
	/**
	 * construct 
	 * @param reader the reader
	 */
	public Compilator(Reader reader) {
		Objects.requireNonNull(reader);
		this.reader = reader;
	}
	
	/**
	 * call exception
	 * @param message the message
	 * @throws CompilatorException 
	 */
	public void callException(String message) throws CompilatorException {
		throw new CompilatorException(message);
	}

	@Override
	public void readChar() {
		this.reader.readChar();
	}

	@Override
	public void readWord() {
		this.reader.readWord();
	}

	@Override
	public void readLine() {
		this.reader.readLine();
	}
	
	@Override
	public char currentChar() {
		return this.reader.currentChar();
	}
	
	@Override
	public String currentWord() {
		return this.reader.currentWord();
	}
	
	@Override
	public String currentLine() {
		return this.reader.currentLine();
	}

	@Override
	public int length() {
		return this.reader.length();
	}
	
	@Override
	public int indexChar() {
		return this.reader.indexChar();
	}

	@Override
	public int indexLine() {
		return this.reader.indexLine();
	}

	@Override
	public int indexColumn() {
		return this.reader.indexColumn();
	}
}
