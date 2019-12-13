package anaofind.lib.anaofile;

import java.io.*;

import anaofind.lib.anadatair.reader.ReaderFile;
import anaofind.lib.anaofile.writer.WriterFile;

/**
 * file datas
 */
public class AnaoFile {
		
	/**
	 * the file
	 */
	private File file;
			
	/**
	 * construct
	 * @param pathFile the file path
	 */
	public AnaoFile(String pathFile){
		this.file = new File(pathFile);
	}
			
	/**
	 * create new file
	 */
	public void create() {
		try {
			if (this.file.exists()) {
				throw new Exception("file already exist");
			}
			if (this.file.isDirectory()) {
				this.file.mkdirs();
			} else {
				this.file.createNewFile();	
			}
		}  catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * get writer of file
	 * @return the writer
	 */
	public WriterFile writer() {
		return new WriterFile(this.file);
	}
		
	/**
	 * get reader of file
	 * @return the reader
	 */
	public ReaderFile reader() {
		return new ReaderFile(this.file);
	}
	 
}
