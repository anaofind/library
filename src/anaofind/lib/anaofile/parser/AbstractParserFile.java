package anaofind.lib.anaofile.parser;

import anaofind.lib.anaofile.file.FileDatas;
import anaofind.lib.anaofile.listenable.FileListenable;

/**
 * classe abstraite de parser de fichier
 * @author anaofind
 *
 */
public abstract class AbstractParserFile extends FileListenable{
	
	/**
	 * code de recuperation de type de donnees
	 * 0 : mots par mots
	 * 1 : ligne par ligne (un espace entre chaque mot)
	 * 2 : ligne par ligne (espaces originals)
	 * 3 : bloc par bloc (un espace entre chaque mot)
	 * 4 : bloc par bloc (espaces originals)
	 */
	public static final int DATAS_WORD = 0, DATAS_LINE = 1, DATAS_ORIGINAL_LINE = 2,  DATAS_BLOC = 3, DATAS_ORIGINAL_BLOC = 4;
	
	/**
	 * le fichier
	 */
	private FileDatas file;
	
	/**
	 * le code de recuperation de donnees
	 */
	private int codeDatas = DATAS_LINE;
	
	/**
	 * le chemin du fichier
	 */
	private String pathFile;
		
	/**
	 * constructeur
	 * @param pathFile le chemin du fichier
	 */
	public AbstractParserFile(String pathFile){
		this.pathFile = pathFile;
		this.file = new FileDatas(pathFile);
	}
	
	/**
	 * constructeur
	 * @param pathFile le chemin du fichier
	 * @param codeDatas le code de recuperation de donnees
	 */
	public AbstractParserFile(String pathFile, int codeDatas){
		this.pathFile = pathFile;
		this.codeDatas = codeDatas;
		String separator = getSeparator(codeDatas);
		this.file = new FileDatas(pathFile, separator);
	}
	
	/**
	 * constructeur
	 * @param pathFile le chemin du fichier
	 * @param separatorBloc le separateur de bloc
	 */
	public AbstractParserFile(String pathFile, String separatorBloc){
		this.pathFile = pathFile;
		this.codeDatas = DATAS_BLOC;
		this.file = new FileDatas(pathFile, separatorBloc);
	}
	
	/**
	 * methode appelé au début du parsage
	 */
	public abstract void preparation();
	
	/**
	 * methode appelé à la fin du parsage
	 */
	public abstract void finishing();
	
	/**
	 * methode permettant de parser le fichier
	 */
	public void toParse(){
		
		file.openFile();
		
		if (file.isOpen()){
			preparation();
			
			String data  = getNextDatasFile();
			while (data != null) {
				executeAction(data);
				this.incrementProgress(file.getByteReadLength());
				data = getNextDatasFile();
			}
			
			finishing();
		}
		
		file.closeFile();
	}
	
	/**
	 * methode permettant de parser le fichier en donnant le nombre de données suivantes à traiter
	 * @param nbDatas le nombre de données suivante à traiter
	 */
	public void toParse(int nbDatas){
		int nb = 0;
		
		if (! file.isOpen()){
			file.openFile();
		}
	
		if (file.isOpen() && nb < nbDatas){
			String data  = getNextDatasFile(); 
			while (data != null && nb < nbDatas){
				executeAction(data);
				this.incrementProgress(file.getByteReadLength());
				nb++;
				if (nb < nbDatas){
					data = getNextDatasFile();
				}
			}
			if (data == null){
				file.closeFile();
			}
		}
	}
	
	/**
	 * methode permettant de recuperer les prochaines donnees
	 * @return les prochaines donnees
	 */
	private String getNextDatasFile(){
		switch (codeDatas){
		case DATAS_WORD :
			return file.nextWord();
		case DATAS_LINE :
			return file.nextLine();
		case DATAS_ORIGINAL_LINE :
			return file.nextOriginalLine();
		case DATAS_BLOC :
			return file.nextBloc();
		case DATAS_ORIGINAL_BLOC :
			return file.nextOriginalBloc();
		default : 
			System.out.println("ERROR CODE DATAS");
		}
		return null;
	}
	
	/**
	 * methode abstraite permettant d'excecuter une action pour une ligne lue
	 * @param line la ligne lue
	 */
	public abstract void executeAction(String line);
	
	/**
	 * methode permettant de recuper la taille du fichier
	 * @return la taille du fichier
	 */
	public long getFileLength() {
		return file.getFileLength();
	}

	/**
	 * getter nom fichier
	 * @return le nom du fichier
	 */
	public String getPathFile() {
		return pathFile;
	}
	
	/**
	 * methode permettant de recuperer le separateur du fihchier
	 * @param codeData le code data
	 * @return le separateur
	 */
	private String getSeparator(int codeData) {
		if (codeData == DATAS_WORD) {
			return "\\s";
		}
		return "\n";
	}
	
	@Override
	public double getLengthDatas() {
		return file.getFileLength();
	}
}
