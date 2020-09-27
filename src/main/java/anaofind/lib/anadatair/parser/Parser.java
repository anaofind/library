package anaofind.lib.anadatair.parser;

import java.util.Objects;

import anaofind.lib.anadatair.reader.*;

/**
 * classe abstraite de parser de fichier
 * @author anaofind
 *
 */
public abstract class Parser{
	
	/**
	 * code of data
	 */
	public static final int DATAS_CHAR = 0, DATAS_WORD = 1, 
			DATAS_LINE = 2;
	
	/**
	 * the reader of file
	 */
	private Reader reader;
	
	/**
	 * the code of datas
	 */
	private int codeDatas;
		
	/**
	 * construct
	 * @param pathFile the path of file
	 */
	public Parser(Reader reader){
		this(reader, DATAS_LINE);
	}
	
	/**
	 * construct
	 * @param pathFile the path of file
	 * @param codeDatas the code of data
	 */
	public Parser(Reader reader, int codeDatas){
		Objects.requireNonNull(reader);
		this.reader = reader;
		this.codeDatas = codeDatas;
	}
		
	/**
	 * call before parsing
	 */
	public abstract void preparation();
	
	/**
	 * call after parsing
	 */
	public abstract void finishing();
	
	/**
	 * to parse
	 */
	public void toParse(){		
		if (this.reader != null) {
			preparation();
			String data  = getNextDatasFile();
			while (! this.reader.isEndReading()) {
				executeAction(data);
				data = getNextDatasFile();
			}
			finishing();	
		}
	}
	
	/**
	 * to parse
	 * @param nbDatas number datas to parse
	 */
	public void toParse(int nbDatas){
		int nb = 0;	
		if (this.reader != null) {
			String data  = getNextDatasFile(); 
			while (! this.reader.isEndReading() && nb < nbDatas){
				executeAction(data);
				nb++;
				if (nb < nbDatas){
					data = getNextDatasFile();
				}
			}
		}
	}
	
	/**
	 * get next datas to parse
	 * @return the next datas to parse
	 */
	private String getNextDatasFile(){
		switch (codeDatas){
		case DATAS_CHAR :
			this.reader.readChar();
			return "" + this.reader.currentChar();
		case DATAS_WORD :
			this.reader.readWord();
			return this.reader.currentWord().replace("\r", "");
		case DATAS_LINE :
			this.reader.readLine();
			return this.reader.currentLine().replace("\r", "");
		default : 
			System.out.println("ERROR CODE DATAS");
		}
		return null;
	}
		
	/**
	 * excecute action one data parsing
	 */
	public abstract void executeAction(String datas);
	
	/**
	 * get length of file
	 * @return the length of file
	 */
	public long getFileLength() {
		return this.reader.length();
	}
	
	/**
	 * get progress of parsing
	 * @return the progress parsing
	 */
	public double getProgress() {
		return this.reader.getProgressReading();
	}
}
