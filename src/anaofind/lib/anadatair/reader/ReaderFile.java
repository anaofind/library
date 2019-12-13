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
public class ReaderFile extends ReaderImpl{
	
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
	 * @param pathFile the path of file
	 */
	public ReaderFile(String pathFile) {
		this(new File(pathFile));
	}
	
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
	public int length() {
		return this.lengthFile;
	}

	@Override
	public char nextChar() {
		try {
			return (char) this.reader.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ' ';
	}
}
