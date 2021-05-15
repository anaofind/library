package anaofind.lib.ananetwork.protocol;

/**
 * protocol without effect
 * @author anaofind
 */
public class NoProtocol implements Protocol{

	@Override
	public String encodingMessage(String messageDecoding) {
		return messageDecoding;
	}

	@Override
	public String decodinMessage(String messageEncoding) {
		return messageEncoding;
	}

}
