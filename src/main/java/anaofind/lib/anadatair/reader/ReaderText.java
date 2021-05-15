package anaofind.lib.anadatair.reader;

import java.util.Objects;

/**
 * reader text
 * @author lrauzier
 *
 */
public class ReaderText extends ReaderImpl{

	/**
	 * the text
	 */
	private String text;
	
	/**
	 * the length text
	 */
	private long lengthText;
		
	/**
	 * construct
	 * @param text the text
	 */
	public ReaderText(String text) {
		Objects.requireNonNull(text);
		this.text = text;
		this.lengthText = text.length();
	}
	
	@Override
	public long length() {
		return this.lengthText;
	}

	@Override
	public char nextChar() {
		return this.text.charAt((int)this.indexChar());
	}
	
	public static void main (String[] args) {
		String text = "coucou\r\ncomment �ava ?";
		Reader r = new ReaderText(text.replace("\r", ""));
		r.readLine();
		System.out.println("debut " + r.currentLine());
		r.readLine();
		System.out.println(r.currentLine() + " fin");
	}
}
