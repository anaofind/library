package anaofind.lib.anadatair.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

/**
 * reader file
 * @author lrauzier
 */
public class ReaderFile implements Reader{

	/**
	 * the current char
	 */
	private char currentChar = ' ';

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
	 * the reader
	 */
	private BufferedReader reader;

	/**
	 * the length file
	 */
	private int lengthFile;

	/**
	 * construct
	 * @param file the file to read
	 */
	public ReaderFile(File file) {
		Objects.requireNonNull(file);
		try {
			if (! file.exists()) {
				throw new Exception("file not found : " + file.getPath());
			}
			if (! file.canRead()) {
				throw new Exception("file can not be readed : " + file.getPath());
			}
			this.reader = new BufferedReader(new FileReader(file));
			this.lengthFile = (int)file.length();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void readChar() {
		this.indexChar ++;
		if (! this.isEndReading()) {
			try {
				if (this.currentChar == '\n') {
					this.indexColumn = 0;
					this.indexLine ++;
				}
				this.currentChar = (char) this.reader.read();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		return this.lengthFile;
	}

	@Override
	public boolean isStartReading() {
		return (this.indexChar > -1);
	}

	@Override
	public boolean isEndReading() {
		return (this.indexChar >= this.lengthFile);
	}

	@Override
	public double getProgressReading() {
		return ((double)this.indexChar / (double)this.lengthFile);
	}

	@Override
	public int[] getIndexLineColumn() {
		int[] lineColumn = new int[2];
		lineColumn[0] = this.indexLine;
		lineColumn[1] = this.indexColumn;
		return lineColumn;
	}

	public static void main(String[] args) {
		File f = new File("testReaderFile.txt");
		Reader r = new ReaderFile(f);
		while (! r.isEndReading()) {
			r.readChar();
			System.out.println(r.getProgressReading()*100);
		}
	}
	
}
