package anaofind.lib.anaofile;

import java.io.*;
import java.util.*;

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
	 * private construct
	 * @param file the file
	 */
	private AnaoFile(File file) {
		this.file = file;
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
	 * delete the file
	 */
	public void delete() {
		try {
			if (! this.file.exists()) {
				throw new Exception("file not found");
			}
			if (! this.file.delete()) {
				throw new Exception("file cannot be deleted");
			}
		}  catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * know if file exist
	 * @return boolean : true if file exist | false else
	 */
	public boolean exist() {
		return this.file.exists();
	}
	
	
	/**
	 * is directory
	 * @return boolean : true if file is directory | false else
	 */
	public boolean isDirectory() {
		return this.file.isDirectory();
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
	
	/**
	 * get childrens list
	 */
	public List<AnaoFile> childrens() {
		List<AnaoFile> childrens = new ArrayList<>();
		if (this.isDirectory()) {
			File[] files = this.file.listFiles();
			if (files != null) {
				for (File file : files) {
					childrens.add(new AnaoFile(file));
				}		
			}
		}
		return childrens;
	}

	/**
	 * get all files recursive
	 * @return list of file
	 */
	public List<AnaoFile> filesRec() {
		List<AnaoFile> files = new ArrayList<AnaoFile>();
		if (this.exist()) {
			if (! this.isDirectory()) {
				files.add(this);
			} else {
				List<AnaoFile> childrens = this.childrens();
				for (AnaoFile child : childrens) {
					files.addAll(child.filesRec());
				}
			}
		}
		return files;
	}

	/**
	 * path
	 * @return path
	 */
	public String path() {
		return this.file.getAbsolutePath();
	}
	
	/**
	 * get name of file
	 * @return the name of file
	 */
	public String name() {
		return this.file.getName();
	}
	
	/**
	 * get extension of file
	 * @return the extension of file
	 */
	public String extension() {
		if (! this.isDirectory()) {
			String[] splitName = this.name().split("\\.");
			if (splitName.length > 1) {
				return splitName[splitName.length-1];
			}
		}
		return "";
	}

	@Override
	public String toString() {
		return this.path();
	}
	
	
}
