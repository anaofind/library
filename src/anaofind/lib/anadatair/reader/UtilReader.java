package anaofind.lib.anadatair.reader;

/**
 * util reader
 * @author anaofind
 */
public class UtilReader {
	/**
	 * remove space in text
	 * @param textSpace the text with space
	 * @return the text without space
	 */
	public static String removeSpace(String textSpace) {
		String textWithoutSpace = "";
		Reader reader = new ReaderText(textSpace);
		reader.readCharWithoutSpace();
		while (! reader.isEndReading()) {
			textWithoutSpace += reader.currentChar();
			reader.readCharWithoutSpace();
		}
		return textWithoutSpace;
	}
}
