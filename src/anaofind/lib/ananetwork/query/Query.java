package anaofind.lib.ananetwork.query;

/**
 * classe représentant une requete
 * @author anaofind
 *
 */
public class Query {
	
	/**
	 * le separateur de l'entete et des arguments
	 */
	public static final String SEPARATOR_HEADER = "SEPARATEUR_ENTETE";
	
	/**
	 * le separateur des arguments
	 */
	public static final String SEPARATOR_ARGUMENTS = "SEPARATEUR_ARGUMENTS";
	
	/**
	 * l'entete de la requete
	 */
	private int header;
	
	/**
	 * les arguments
	 */
	private String[] arguments;
	
	/**
	 * constructeur
	 * @param header l'entete
	 * @param arguments les arguments
	 */
	public Query(int header, String[] arguments){
		this.header = header;
		this.arguments = arguments;
	}
		
	
	/**
	 * méthode permettant de creer une requete
	 * @param plainQuery la requete brut
	 * @return la requete parsé
	 */
	public static Query parseQuery(String plainQuery){
		String[] r = plainQuery.split(SEPARATOR_HEADER);
		if (r.length > 0){
			int header;
			try {
				header = Integer.parseInt(r[0]);
			} catch (NumberFormatException e){
				header = -1;
			}
			if (r.length == 2){
				String[] arguments = r[1].split(SEPARATOR_ARGUMENTS);
				return new Query(header, arguments);
			} 
			String[] empty = new String[1];
			empty[0] = "";
			return new Query(header, empty);
		}
		return null;
	}

	
	/**
	 * getter entete
	 * @return l'entete
	 */
	public int getHeader() {
		return header;
	}

	/**
	 * getter arguments
	 * @return les arguments
	 */
	public String[] getArguments() {
		return arguments;
	}
	
	/**
	 * methode permettant de creer une requete brut
	 * @param header l'entete
	 * @param arguments les arguments
	 * @return la requete brut
	 */
	public static String createPlainQuery(int header, String...arguments) {
		String pq = "" + header + SEPARATOR_HEADER ;
		if (arguments.length > 0 ) {
			for (int i = 0; i<arguments.length -1; i++) {
				pq += arguments[i] + SEPARATOR_ARGUMENTS;
			}
			pq += arguments[arguments.length-1];
		}
		return pq;
	}
	
	
	
}
