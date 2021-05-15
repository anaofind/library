package anaofind.lib.ananetwork.http;

import anaofind.lib.anadatair.Anadatair;

/**
 * the request http
 * @author anaofind
 *
 */
public class RequestHttp {
	/**
	 * the type of request
	 */
	private RequestType type;
	
	/**
	 * the route
	 */
	private String route;
	
	/**
	 * the body
	 */
	private Anadatair body;
	
	/**
	 * @param type the type
	 * @param route the route
	 * @param body the body
	 */
	public RequestHttp(RequestType type, String route, Anadatair body) {
		this.type = type;
		this.route = route;
		this.body = body;
	}

	/**
	 * get type
	 * @return the type
	 */
	public RequestType getType() {
		return type;
	}

	/**
	 * get route
	 * @return the route
	 */
	public String getRoute() {
		return route;
	}

	/**
	 * get body
	 * @return the body
	 */
	public Anadatair getBody() {
		return body;
	}
	
	/**
	 * the type of request http
	 * @author anaofind
	 */
	public enum RequestType {
		GET, POST, DELETE, UPDATE;
		
		/**
		 * get request type with string
		 * @param typeString the type string
		 * @return the request type
		 */
		public static RequestType get(String typeString) {
			switch (typeString) {
			case "GET" : 
				return RequestType.GET;
			case "POST" : 
				return RequestType.POST;
			}
			return null;		
		}
	}
}
