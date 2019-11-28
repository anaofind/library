package anaofind.lib.anadatair.reader;

import java.io.File;
import java.util.Objects;

/**
 * compilator data
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
			super(message + " -> char : \'" + reader.currentChar() + "\' [" + reader.getIndexLineColumn()[0] + "," + reader.getIndexLineColumn()[1] + "]");
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
		Objects.requireNonNull(dataText);
		this.reader = new ReaderText(dataText);
	}
	
	/**
	 * construct
	 * @param file the file
	 */
	public Compilator(File file) {
		Objects.requireNonNull(file);
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
	public void readCharWithoutSpace() {
		this.reader.readCharWithoutSpace();
	}

	@Override
	public char currentChar() {
		return this.reader.currentChar();
	}

	@Override
	public int length() {
		return this.reader.length();
	}

	@Override
	public boolean isStartReading() {
		return this.reader.isStartReading();
	}

	@Override
	public boolean isEndReading() {
		return this.reader.isEndReading();
	}

	@Override
	public double getProgressReading() {
		return this.reader.getProgressReading();
	}

	@Override
	public int[] getIndexLineColumn() {
		return this.reader.getIndexLineColumn();	
	}
}
