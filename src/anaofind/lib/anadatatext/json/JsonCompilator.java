package anaofind.lib.anadatatext.json;

import java.util.Objects;

import anaofind.lib.anadatatext.json.*;

import java.util.*;

/**
 * compilator json
 * @author anaofind
 *
 */
public class JsonCompilator {

	/**
	 * enum char start
	 */
	public enum Start {
		OBJECT('{'),
		ARRAY('['),
		STRING('\"'),
		ATTRIBUTE(':');

		/**
		 * the character
		 */
		public char c;

		/**
		 * private construct
		 * @param c the character
		 */
		private Start(char c) {
			this.c = c;
		}		
	}

	/**
	 * enum char end 
	 */
	public enum End {
		OBJECT('}'),
		ARRAY(']'),
		STRING('\"'),
		VALUE(',');

		/**
		 * the character
		 */
		public char c;

		/**
		 * private construct
		 * @param c the character
		 */
		private End(char c) {
			this.c = c;
		}

	}

	/**
	 * the text json
	 */
	private String json;
	
	/**
	 * the json value
	 */
	private JsonValue value;

	/**
	 * the index char
	 */
	private int indexChar;
	
	/**
	 * the current char
	 */
	private int currentChar;

	/**
	 * length of json text
	 */
	private int lengthJson;

	/**
	 * construct
	 * @param json the text json
	 */
	public JsonCompilator(String json) {
		Objects.requireNonNull(json);

		this.indexChar = 0;
		this.lengthJson = json.length();
		this.json = json;
		this.value = this.readJsonValue();
	}

	/**
	 * to json value
	 * @return the json value
	 */
	private JsonValue readJsonValue() {
		this.readChar();
		if (! this.isEndRead()) {
			if (currentChar == Start.OBJECT.c) {
				return this.readJsonObject();
			}
			if (currentChar == Start.ARRAY.c) {
				return this.readJsonArray();
			}
		}
		return null;
	}

	/**
	 * read the json object
	 * @return the json object readed
	 */
	private JsonObject readJsonObject() {
		JsonObject jo = new JsonObject();
		String attributeName = "";
		while (! this.isEndRead()) {
			this.readChar();
			if (this.currentChar == End.OBJECT.c) {
				return jo;
			}
			if (this.currentChar == Start.STRING.c) {
				attributeName = this.readString();
			}
			if (this.currentChar == End.VALUE.c) {
				if (attributeName == null) {
					return null;
				}
				jo.add(attributeName, this.readJsonValue());
			}
		}
		return null;
	}
	
	/**
	 * read the json array
	 * @return the json array readed
	 */
	private JsonArray readJsonArray() {
		List<JsonValue> values = new ArrayList<JsonValue>();
		JsonValue currentValue;
		while (! this.isEndRead()) {
			this.readChar();
			if (this.currentChar == End.ARRAY.c) {
				JsonValue[] array = values.toArray(new JsonValue[values.size()]);
				return new JsonArray(array);
			}
			currentValue = this.readJsonValue();
			if (this.currentChar == End.VALUE.c) {
				values.add(currentValue);
			}
		}
		return null;
	}
		
	/**
	 * read the string
	 * @return the string readed
	 */
	private String readString() {
		String string = "";
		while (! this.isEndRead()) {
			this.readChar();
			if (this.currentChar == End.STRING.c) {
				return string;
			}
			string += this.currentChar;			
		}
		return null;
	}
	
	/**
	 * read the char
	 * @return the char readed
	 */
	private void readChar() {
		if (! this.isEndRead()) {
			this.currentChar = json.charAt(this.indexChar);
			this.indexChar++;
		}
	}
	
	/**
	 * is end read the json
	 * @return boolean : true if is end | false else
	 */
	private boolean isEndRead() {
		return (this.indexChar >= this.lengthJson);
	}

}
