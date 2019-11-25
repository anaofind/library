package anaofind.lib.anadatair.compilator;

import java.util.Objects;

import anaofind.lib.anadatair.json.*;

import java.util.*;

/**
 * compilator json
 * @author anaofind
 *
 */
public class CompilatorJSON extends Compilator{
	
	/**
	 * the json value
	 */
	private JsonValue value;

	/**
	 * construct
	 * @param json the json text
	 */
	public CompilatorJSON(String json) {
		super(json);
		try {
			this.value = this.readJsonValue();
		} catch (CompilatorException e) {
			e.printStackTrace();
		}
		Objects.requireNonNull(this.value);
	}

	/**
	 * getter value
	 * @return the json value
	 */
	public JsonValue getValue() {
		return this.value;
	}
	
	/**
	 * read json value
	 * @return the json value readed
	 * @throws CompilatorException 
	 */
	private JsonValue readJsonValue() throws CompilatorException {
		if (this.isSpace()) {
			this.readCharWithoutSpace();
		}
		while (! this.isEndRead()) {

			switch (this.currentChar) {
			case '{' :
				return this.readJsonObject();
			case '[' :
				return this.readJsonArray();
			default:
				return this.readJsonBase();
			}
		}
		return null;
	}
	
	/**
	 * read json object
	 * @return the json object
	 * @throws CompilatorException 
	 */
	private JsonObject readJsonObject() throws CompilatorException{
		JsonObject object = new JsonObject();
		this.readCharWithoutSpace();
		boolean end = this.currentChar == '}';
		while (! this.isEndRead() && !end) {			
			if (this.currentChar != '\"') {
				this.callException("char must be \'\"\'");
				return null;
			}
			
			String attribute = this.readString();
			
			if (this.currentChar != ':') {
				this.callException("char must be \':\'");
				return null;
			}
			this.readCharWithoutSpace();
			JsonValue value = this.readJsonValue();
			if (value == null) {
				this.callException("value must not be null");
				return null;
			}
			
			object.addAttribute(attribute, value);
			
			if (this.currentChar != ',') {
				end = true;
			} else {
				this.readCharWithoutSpace();
			}
		}
		
		if (this.currentChar != '}') {
			this.callException("char must be \'}\'");
		}
		this.readCharWithoutSpace();
		return object;
	}


	/**
	 * read json array
	 * @return the json array
	 * @throws CompilatorException 
	 */
	private JsonArray readJsonArray() throws CompilatorException {
		List<JsonValue> values = new ArrayList<JsonValue>();
		this.readCharWithoutSpace();
		boolean end = this.currentChar == ']';
		while (! this.isEndRead() && !end) {
			JsonValue value = this.readJsonValue();
			
			if (value == null) {
				return null;
			}
			
			values.add(value);
			
			if (this.currentChar != ',') {
				end = true;
			} else {
				this.readCharWithoutSpace();	
			}
		}
		
		if (this.currentChar != ']') {
			this.callException("char must be \']\'");
		}
		
		this.readCharWithoutSpace();
		return new JsonArray(values.toArray(new JsonValue[values.size()]));
	}
	
	/**
	 * read json base
	 * @return the json base
	 * @throws CompilatorException 
	 */
	private JsonValue readJsonBase() throws CompilatorException {
		
		if (this.currentChar == '\"') {
			return new JsonString(this.readString());
		}
		
		String value = this.readValue();
		
		if (value.matches("^([+-]?\\d+[Ee]?)+$")) {
			return new JsonNumber(Integer.parseInt(value));
		}
		if (value.matches("^([+-]?\\d+(.\\d+)?[Ee]?)+$")) {
			return new JsonNumber(Double.parseDouble(value));
		}
		if (value.matches("^true|false|True|False$")) {
			return new JsonBoolean(Boolean.parseBoolean(value));
		}
		if (value.equals("null")) {
			return new JsonNull();
		}
		
		this.callException("value not accepted");
		System.out.println("NULL BASE");
		return null;
	}
	
	/**
	 * read string (not json string)
	 * @return the string readed
	 */
	private String readString() {
		String string = "";
		while (! this.isEndRead()) {
			this.readChar();
			if (this.currentChar == '\"') {
				this.readCharWithoutSpace();
				return string;
			}
			string += this.currentChar;
		}
		System.out.println("NULL STRING");
		return null;
	}
	
	/**
	 * read value 
	 * @return the string of value readed
	 */
	private String readValue() {
		String string = "";
		while (! this.isEndRead()) {
			if (this.isSpace() | this.currentChar == ',') {
				if (this.isSpace()) {
					this.readCharWithoutSpace();
				}
				return string;
			}
			string += this.currentChar;
			this.readChar();
		}
		System.out.println("NULL VALUE");
		return null;
	}
}
