package anaofind.lib.anaofile;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.*;

import anaofind.lib.anadatair.reader.ReaderFile;
import anaofind.lib.anaofile.writer.WriterFile;

/**
 * file datas
 */
public class AnaoFile {

	/**
	 * the path of file
	 */
	private Path path;

	/**
	 * the nb of file
	 */
	private int nbFiles = -1;

	/**
	 * construct
	 * @param pathFile the file path
	 */
	public AnaoFile(String pathFile){
		this.path = Paths.get(pathFile);
	}
	
	/**
	 * construct
	 * @param pathFile the file path
	 */
	public AnaoFile(Path pathFile){
		this.path = pathFile;
	}

	/**
	 * create new file
	 */
	public void create() {
		try {
			Files.createFile(this.path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * delete the file
	 */
	public void delete() {
		try {
			Files.delete(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * know if file exist
	 * @return boolean : true if file exist | false else
	 */
	public boolean exist() {
		return Files.exists(this.path);
	}


	/**
	 * is directory
	 * @return boolean : true if file is directory | false else
	 */
	public boolean isDirectory() {
		return Files.isDirectory(this.path);
	}

	/**
	 * get writer of file
	 * @return the writer
	 */
	public WriterFile writer() {
		return new WriterFile(this.path.toFile());
	}

	/**
	 * get reader of file
	 * @return the reader
	 */
	public ReaderFile reader() {
		return new ReaderFile(this.path.toFile());
	}

	/**
	 * get childrens list
	 */
	public List<AnaoFile> childrens() {
		List<AnaoFile> childrens = new ArrayList<>();
		if (this.isDirectory()) {
			File[] files = this.path.toFile().listFiles();
			if (files != null) {
				for (File file : files) {
					childrens.add(new AnaoFile(file.getAbsolutePath()));
				}		
			}
		}
		return childrens;
	}

	/**
	 * get all files recursive
	 * @param extensions the filter extension
	 * @return list of file
	 */
	public List<AnaoFile> filesRec() {
		List<AnaoFile> files = new ArrayList<AnaoFile>();
		if (this.exist()) {
			
			SimpleFileVisitor<Path> visitor = new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					files.add(new AnaoFile(file));
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFileFailed(Path file, IOException e) throws IOException {
					return FileVisitResult.SKIP_SUBTREE;
				}

				@Override
				public FileVisitResult preVisitDirectory(Path dir,
						BasicFileAttributes attrs) throws IOException {
					return FileVisitResult.CONTINUE;
				}
			};
			
			try {
				Files.walkFileTree(this.path, visitor);
				return files;
			} catch (IOException e) {
				return List.of();
			}
		}
		return files;
	}

	/**
	 * path
	 * @return path
	 */
	public Path path() {
		return this.path.toAbsolutePath();
	}

	/**
	 * get source of file
	 * @return the source of file
	 */
	public Path source() {
		return this.path.getParent();
	}

	/**
	 * get size of file
	 * @return the size of file
	 */
	public long size() {
		try {
			return Files.size(this.path);
		} catch (IOException e) {
			return 0;
		}
	}

	/**
	 * get nb of files content
	 * @return the nb of files
	 */
	public int nbOfFiles() {
		if (this.nbFiles < 0) {
			if (this.isDirectory()) {
				SimpleFileVisitor<Path> visitor = new SimpleFileVisitor<Path>() {
					@Override
					public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
						nbFiles ++;
						return FileVisitResult.CONTINUE;
					}

					@Override
					public FileVisitResult visitFileFailed(Path file, IOException e) throws IOException {
						return FileVisitResult.SKIP_SUBTREE;
					}

					@Override
					public FileVisitResult preVisitDirectory(Path dir,
							BasicFileAttributes attrs) throws IOException {
						return FileVisitResult.CONTINUE;
					}
				};
				try {
					Files.walkFileTree(this.path, visitor);
					return this.nbFiles;
				} catch (IOException e) {
					this.nbFiles = -1;
					return 0;
				}	
			}
		}
		return 1;
	}

	/**
	 * get name of file
	 * @return the name of file
	 */
	public String name() {
		return path.getFileName().toString();
	}

	/**
	 * get extension of file
	 * @return the extension of file
	 */
	public String extension() {
		if (! this.isDirectory()) {
			String[] splitName = this.name().split("\\.");
			if (splitName.length > 1) {
				return splitName[splitName.length-1].toLowerCase();
			}
		}
		return "";
	}

	/**
	 * get date of last modification of file
	 * @return the date of last modification
	 */
	public Date dateOfLastModification() {
		try {
			FileTime time = Files.getLastModifiedTime(path);
			Date date = new Date(time.toMillis());
			return date;
		} catch (IOException e) {}
		return null;
	}

	/**
	 * rename
	 * @param newName the new name
	 * @return boolean : true if rename is good | false else
	 */
	public boolean rename(String newName) {
		File file = new File(this.path.toString());
		Path newPath = Paths.get(this.source() + "\\" + newName + "." + this.extension()); 
		boolean res = file.renameTo(new File(newPath.toString()));
		if (res) {
			this.path = newPath;
		}
		return res;
	}
	
	/**
	 * move the file
	 * @param path the destination
	 * @return boolean : true if move is good | false else
	 */
	public boolean move(String path) {
		try {
			Files.move(this.path, Paths.get(path));
		} catch (IOException e) {
			return false;
		}
		boolean res = Files.exists(Paths.get(path));
		if (res) {
			this.path = Paths.get(path);
		}
		return res;
	}

	/**
	 * copy the file
	 * @param path the destination
	 * @return boolean : true if copy is good | false else
	 */
	public boolean copy(String path) {
		try {
			Files.copy(this.path, Paths.get(path));
		} catch (IOException e) {
			return false;
		}
		return Files.exists(Paths.get(path));
	}

	@Override
	public String toString() {
		return this.path().toString();
	}


}
