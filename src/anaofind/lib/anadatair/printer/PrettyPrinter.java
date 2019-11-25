package anaofind.lib.anadatair.printer;

import java.util.*;

/**
 * abstract printer
 * @author anaofind
 */
public class PrettyPrinter {
	
	/**
	 * the default margin char
	 */
	private static final char DEFAULT_MARGIN_CHAR = ' ';
	
	/**
	 * the default margin size base
	 */
	private static final int DEFAULT_MARGIN_SIZE_BASE = 0;
	
	/**
	 * the lines
	 */
	protected List<String> lines = new ArrayList<String>();
	
	/**
	 * the tampon current line 
	 */
	private String tamponLine = "";
	
	/**
	 * the num of current line
	 */
	private int numLine = 0;
	
	/**
	 * the char of margin
	 */
	private char marginChar;
	
	/**
	 * the char of margin
	 */
	private char endLineChar = '\n';
	
	/**
	 * the margin size base
	 */
	protected int marginSizeBase;
		
	/**
	 * construct
	 */
	public PrettyPrinter() {
		this(DEFAULT_MARGIN_CHAR, DEFAULT_MARGIN_SIZE_BASE);
	}
		
	/**
	 * construct
	 * @param marginChar the margin char
	 * @param jumpChar the jump char
	 */
	public PrettyPrinter(char marginChar) {
		this(marginChar, DEFAULT_MARGIN_SIZE_BASE);
	}
	
	/**
	 * construct
	 * @param marginSizeBase the margin size base
	 */
	public PrettyPrinter(int marginSizeBase) {
		this(DEFAULT_MARGIN_CHAR, marginSizeBase);
	}
	
	/**
	 * construct
	 * @param marginChar the margin char
	 * @param marginSizeBase the margin size base
	 */
	public PrettyPrinter(char marginChar, int marginSizeBase) {
		try {
			if (this.marginSizeBase < 0) {
				throw new Exception("margin size base must not be negative : " + marginSizeBase);
			}
			this.marginChar = marginChar;
			this.marginSizeBase = marginSizeBase;	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * add line
	 * @param marginSize the size of margin
	 * @param endLine boolean indicate if jump line after text
	 * @param text the line
	 */
	public void addText(int marginSize, String text, boolean endLine) {
		Objects.requireNonNull(text);
		try {
			if (marginSize < 0) {
				throw new Exception("margin size must not be negative : " + marginSize);
			}
			
			if (this.numLine < 0) {
				throw new Exception("num of line must not be negative : " + this.numLine);
			}
			
			if (this.numLine > this.lines.size()) {
				for (int i = this.lines.size()-1; i<this.numLine; i++) {
					this.lines.add("");
				}
			}
			
			String margin = "";
			for (int i = 0; i<marginSize; i++) {
				margin += this.marginChar;
			}
			
			this.tamponLine += margin + text;
			
			if (endLine) {
				String marginBase = "";
				for (int i = 0; i<this.marginSizeBase; i++) {
					marginBase += this.marginChar;
				}
				this.lines.add(marginBase + tamponLine);
				this.numLine ++;
				this.tamponLine = "";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * add line
	 * @param marginSize the size of margin
	 * @param jumpLine boolean indicate if jump line after text
	 * @param text the line
	 */
	public void addText(int marginSize, String text) {
		this.addText(marginSize, text, false);
	}
	
	/**
	 * jump line
	 * @param jumpSize the size of jump
	 */
	public void jumpLine(int jumpSize) {
		try {
			if (jumpSize < 0 ) {
				throw new Exception("jump size size must not be negative : " + jumpSize);
			}
			for (int i = 0; i<jumpSize; i++) {
				this.addText(0, "", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * clear text
	 */
	public void clear() {
		lines.clear();
	}
	
	/**
	 * get the text
	 * @return the text
	 */
	public String getText() {
		String text = "";
		if (lines.size() > 0) {
			for (String line : this.lines) {
				text += line + this.endLineChar;
			}
			if (! this.tamponLine.isEmpty()) {
				text += this.tamponLine;
			} else {
				text = text.substring(0, text.length()-1);	
			}
		}
		return text;
	}
	
	/**
	 * to print text
	 */
	public void print() {
		System.out.println(this.getText());
	}
	
	
	public static void main (String[] args) {
		PrettyPrinter pp = new PrettyPrinter();
		pp.addText(0, "coucou");
		pp.addText(1, "comment çava ?", true);
		pp.jumpLine(2);
		pp.marginSizeBase ++;
		pp.addText(0, "tres bien merci ;)");
		
		pp.print();
	}
}
