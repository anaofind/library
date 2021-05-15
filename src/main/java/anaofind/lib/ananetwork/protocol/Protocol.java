package anaofind.lib.ananetwork.protocol;

/**
 * protocol
 * @author anaofind
 *
 */
public interface Protocol {

	/**
	 * get the message encoding
	 * @param messageDecoding the message decoding 
	 * @return the message encoding
	 */
	public String encodingMessage(String messageDecoding);
	
	/**
	 * get the message decoding
	 * @param messageEncoding the message encoding
	 * @return the message decoding
	 */
	public String decodinMessage(String messageEncoding);
	
}
