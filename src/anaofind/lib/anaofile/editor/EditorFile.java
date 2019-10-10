package anaofind.lib.anaofile.editor;

import java.util.ArrayList;
import java.util.Collections;

import anaofind.lib.anaofile.file.*;
import anaofind.lib.anaofile.listenable.FileListenable;

/**
 * classe permettant de creer un fichier
 * @author anaofind
 *
 */
public class EditorFile extends FileListenable{

	/**
	 * le fichier
	 */
	private FileDatas file;
	
	/**
	 * le chemin du fichier
	 */
	private String pathFile;
	
	/**
	 * le nombre de donn�es
	 */
	private int length = 0;
		
	/**
	 * les donn�es
	 */
	private ArrayList<Data> datas = new ArrayList<Data>();
	
	/**
	 * constructeur
	 * @param pathFile le chemin du fichier
	 */
	public EditorFile(String pathFile) {
		this.pathFile = pathFile;
		file = new FileDatas(pathFile);
	}
	
	
	/**
	 * methode permettant d'ajouter une donn�es
	 * @param line la ligne
	 * @param column la colonne
	 * @param data la donn�e
	 */
	public void addData(int line, int column, String data) {
		Data d = new Data(line, column, data);
		this.datas.add(d);
		Collections.sort(this.datas);
		length++;
	}
	
	/**
	 * methode permettant de recuperer les donn�es en string
	 * @return les donn�es en string
	 */
	private String getStringDatas() {
		this.initProgress();
		String ds = "";
		int nl = 1;
		int nc = 1;
		
		for (Data d: datas) {			
			int l = d.getLine();
			int c = d.getColumn();
			String v = d.getValue();
			
			for (int i = nl; i<l; i++) {
				ds += "\n";
				nc = 1;
			}
			
			nl = l;
			
			for (int i = nc; i<c; i++) {
				ds += " ";
			}
			
			ds += v;
			nc = c + v.length();
			
			this.incrementProgress(1);
		}
		
		this.datas.clear();
		return ds;
	}
	
	/**
	 * methode permettant d'�crire les donn�es dans le fichier
	 */
	public void writeDatasInFile() {
		file.openFile();
		String datas = getStringDatas();
		if (datas != null) {
			file.write(datas);	
		}
	}

	/**
	 * getter pathFile
	 * @return le chemin du fichier
	 */
	public String getPathFile() {
		return pathFile;
	}
	
	/**
	 * methode permettant de creer le fichier
	 */
	public void createFile() {
		file.createFile();
	}


	@Override
	public double getLengthDatas() {
		return (double)length;
	}
	
	
}
