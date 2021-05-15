package anaofind.lib.ananetwork.http;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import anaofind.lib.ananetwork.protocol.Protocol;

/**
 * http protocol
 * @author anaofind
 */
public class HttpServerProtocol implements Protocol{

	@Override
	public String encodingMessage(String messageDecoding) {
		// TODO Auto-generated method stub
		StringBuilder headBuilder = new StringBuilder();
		String body = messageDecoding;
		headBuilder.append("HTTP/1.1 200 OK\n");
		headBuilder.append("Last-modified: Fri, 09 Aug 1996 14:21:40 GMT\n");
		headBuilder.append("Date: " + getDateString() + "\n");
		headBuilder.append("Content-Length: " + body.length() + "\n");
		headBuilder.append("Content-Type: text/html\n");
		headBuilder.append("Connection: Closed\n\n");
		headBuilder.append(body);
		
		// System.out.println(headBuilder.toString());
		
		return headBuilder.toString();
	}

	@Override
	public String decodinMessage(String messageEncoding) {
		return messageEncoding;
	}


	private String getDateString() {
		Date today = new Date();

		DateFormat shortDateFormatEN = DateFormat.getDateTimeInstance(
				DateFormat.SHORT,
				DateFormat.SHORT, new Locale("EN","en"));
		return shortDateFormatEN.format(today);
	}
}
