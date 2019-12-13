package anaofind.lib.anaofile.writer;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * writer of file
 * @author anaofind
 */
public class WriterFile {

	/**
	 * the writer of file
	 */
	private FileWriter writer;
	
	/**
	 * the datas
	 */
	private ArrayList<AnaoData> datas = new ArrayList<AnaoData>();
	
	/**
	 * construct 
	 * @param file the file
	 */
	public WriterFile(File file) {
		Objects.requireNonNull(file);
		try {
			if (! file.exists()) {
				throw new Exception("file not found : " + file.getPath());
			}
			if (! file.canRead()) {
				throw new Exception("file can not be readed : " + file.getPath());
			}
			this.writer = new FileWriter(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * add data
	 * @param line the line
	 * @param column the column
	 * @param data the data
	 */
	public void addData(int line, int column, String data) {
		AnaoData d = new AnaoData(line, column, data);
		this.datas.add(d);
		Collections.sort(this.datas);
	}
	
	/**
	 * datas to string
	 * @return string equivalent of datas
	 */
	private String getStringDatas() {
		String ds = "";
		int nl = 1;
		int nc = 1;
		
		for (AnaoData d: datas) {			
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
		}
		
		this.datas.clear();
		return ds;
	}
		
	/**
	 * write datas in file
	 * @param datas the datas to write
	 */
	public void write(String datas) {
		BufferedWriter w = new BufferedWriter(writer);
		try {
			w.write(datas);
			w.flush();
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * write datas in file
	 */
	public void writeDatas() {
		this.write(this.getStringDatas());
	}
	
}
