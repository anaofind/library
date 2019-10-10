package anaofind.lib.anaofile.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * classe qui permet de lire le contenu d'un fichier
 * @author anaofind
 *
 */
public class FileDatas {
	
	
	/** 
	 * le reader du fichier
	 */
	private Scanner reader;
	
	/**
	 * le writer du fichier
	 */
	private FileWriter writer;
	
	/**
	 * le chemin du fichier
	 */
	private String pathFile;
	
	/**
	 * separateur de block
	 */
	private String separatorBloc = "\n";

	/**
	 * la taille du fichier en byte
	 */
	private long fileLength = 0;
	
	/**
	 * la longueur de bytes lus
	 */
	private int byteReadLength = 0;
	
	/**
	 * constructeur
	 * @param pathFile le chemin du fichier
	 */
	public FileDatas(String pathFile){
		this.pathFile = pathFile;
	}
	
	/**
	 * constructeur
	 * @param pathFile le chemin du fichier
	 * @param separatorBloc le separateur de bloc
	 */
	public FileDatas(String pathFile, String separatorBloc){
		this.pathFile = pathFile;
		this.separatorBloc = separatorBloc;
	}
	
	/**
	 * méthode qui permet d'ouvrire le fichier
	 */
	public void openFile(){
		try {
			if (! this.isFileExist()){
				throw new Exception("file not found");
			}
			
			this.initReader();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	

	
	/**
	 * methode permettant de creer le fichier
	 */
	public void createFile() {
		try {
			if (this.isFileExist()) {
				throw new Exception("file already exist");
			}
			
			File file = new File(this.pathFile);
			file.createNewFile();
			
		}  catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * methode permettant de creer un repertoir
	 */
	public void createDirectory() {
		if (!this.isFileExist()) {
			File file = new File(this.pathFile);
			file.mkdirs();	
		}
	}
	
	/**
	 * methode permettant de retourner le bloc suivant du fichier
	 * @return le bloc suivante du fichier
	 */
	public String nextBloc(){
		if (reader.hasNext()){
			
			String bloc = "";
			
			String nextBloc = reader.next();
			Scanner sc = new Scanner(nextBloc);
			if (sc.hasNext()){
				bloc += sc.next();
				while (sc.hasNext()){
					bloc += " " + sc.next();
				}
				sc.close();
				byteReadLength = nextBloc.length() + (separatorBloc.length()*2);
				return bloc;
			}
			sc.close();
		}
		return null;
	}
	
	/**
	 * methode permettant de retourner le bloc suivant du fichier (en conservant les espaces)
	 * @return le bloc suivante du fichier
	 */
	public String nextOriginalBloc(){
		if (reader.hasNext()){
			
			String bloc = reader.next();
			byteReadLength = bloc.length() + (separatorBloc.length()*2);
			return bloc; 
		}
		return null;
	}
	
	/**
	 * methode permettant de retourner la ligne suivante du fichier
	 * @return la ligne suivante du fichier
	 */
	public String nextLine(){
		byteReadLength = 0;
		if (reader.hasNext()){
			String line = "";
			
			String nextLine = reader.nextLine();
			Scanner sc = new Scanner(nextLine);
			if (sc.hasNext()){
				line += sc.next();
				while (sc.hasNext()){
					line += " " + sc.next();
				}
				sc.close();
				byteReadLength = nextLine.length() + (separatorBloc.length()*2);
				return line;
			}
			sc.close();
		}
		return null;
	}
	
	
	/**
	 * methode permettant de retourner la ligne suivante du fichier (en conservant les espaces)
	 * @return la ligne suivante du fichier
	 */
	public String nextOriginalLine(){
		if (reader.hasNext()){
			String line = reader.nextLine();
			byteReadLength = line.length() + (separatorBloc.length()*2);
			return line;
		}
		return null;
	}
	
	/**
	 * methode permettant de retourner le mot suivant du fichier
	 * @return le mot suivant du fichier
	 */
	public String nextWord(){
		if (reader.hasNext()){
			String word = reader.next();
			byteReadLength = word.length() + (separatorBloc.length()*2);
			return word;
		}
		return null;
	}
	
	/**
	 * methode permettant d'écrire les données dans le fichier
	 * @param datas les données
	 */
	public void write(String datas) {
		initWriter();
		BufferedWriter w = new BufferedWriter(writer);
		try {
			w.write(datas);
			w.flush();
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		closeWriter();
	}
	
	/**
	 * méthode qui permet de fermer le fichier
	 */
	public void closeFile(){
		if (reader != null){
			reader.close();
			reader = null;	
		}
	}
	
	/**
	 * methode permettant de fermer le writer
	 */
	private void closeWriter() {
		if (writer != null) {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			writer = null;
		}
	}

	/**
	 * méthode qui permet de savoir si le fichier est ouvert
	 * @return, un booléen
	 */
	public boolean isOpen(){
		return  (reader != null);
	}


	/**
	 * méthode qui permet de vérfier que le fichier existe
	 * @return booléen
	 */
	private boolean isFileExist(){
		File file = new File(this.pathFile);
		return (file.isFile() && !file.isDirectory());
	}
	
	/**
	 * méthode qui permet de preparer le reader
	 */
	private void initReader(){
		File file = new File(this.pathFile);
		fileLength = file.length();
		try {
			this.reader = new Scanner(file);
			this.reader.useDelimiter("\\s*" + this.separatorBloc + "\\s*");
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	/**
	 * methode permettant de preparer le writer
	 */
	private void initWriter() {
		File file = new File(this.pathFile);
		try {
			writer = new FileWriter(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * methode permettant de recupuperer la  taille du fichier (en byte)
	 * @return a taille du fichier en byte
	 */
	public long getFileLength() {
		return fileLength;
	}

	/**
	 * methode permettant de recuperer la taille des derniers bytes lus
	 * @return la taille des derniers bytes lus
	 */
	public int getByteReadLength() {
		return byteReadLength;
	}
	
	
}
