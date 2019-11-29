package anaofind.lib.anadatair.compilator;

import java.util.Objects;

import anaofind.lib.anadatair.ir.*;
import anaofind.lib.anadatair.reader.*;

import java.util.*;

/**
 * compilator ir
 * @author anaofind
 *
 */
public class CompilatorIR extends Compilator{

	/**
	 * private class represente the start syntaxe char
	 */
	private class Start {
		/**
		 *start char
		 */
		public static final char VALUE = '(',
				ATTRIBUTE = '<';
	}

	/**
	 * private class represente the end syntaxe char
	 */
	private class End {
		/**
		 * end char
		 */
		public static final char VALUE = ')',
				TYPE = ':',
				ATTRIBUTE = '>';
	}


	/**
	 * private class represente the type
	 */
	private class TYPE {
		/**
		 * the type string
		 */
		public static final String OBJECT = "object",
				ARRAY = "array",
				INTEGER = "integer",
				DOUBLE = "double",
				STRING = "string",
				BOOLEAN = "boolean",
				NULL = "null";
	}

	/**
	 * the ir value
	 */
	private IRValue value;

	/**
	 * construct
	 * @param textIR the ir text
	 */
	public CompilatorIR(String textIR) {
		super(UtilReader.removeSpace(textIR));
		this.value = this.readIRValue();
		Objects.requireNonNull(this.value);
	}

	/**
	 * get value compiled
	 * @return the value compiled
	 */
	public IRValue getValue() {
		return this.value;
	}
	
	/**
	 * read the next ir value
	 * @return the ir value readed
	 */
	private IRValue readIRValue() {
		while (! this.isEndReading()) {
			if (this.currentChar() == Start.VALUE) {
				String type = this.readType();
				switch (type) {
				case TYPE.OBJECT :
					return this.readObject();
				case TYPE.ARRAY :
					return this.readArray();
				default :
					return this.readBaseValue(type);
				}
			}
			this.readCharWithoutSpace();
		}
		return null;
	}

	/**
	 * read ir type
	 * @return the ir type
	 */
	private String readType() {
		String type = "";
		while (! this.isEndReading()) {
			this.readCharWithoutSpace();
			if (this.currentChar() == End.TYPE) {
				return type;
			}
			type += this.currentChar();
		}
		return null;
	}

	/**
	 * read object
	 * @return the object
	 */
	private IRValue readObject() {
		IRObject object = new IRObject();
		while (! this.isEndReading()) {
			this.readCharWithoutSpace();
			switch (this.currentChar()) {
			case End.VALUE :
				return object;
			case Start.ATTRIBUTE :
				String attribute = this.readAttribute();
				Objects.requireNonNull(attribute);
				IRValue value = this.readIRValue();
				Objects.requireNonNull(value);
				object.addAttribute(attribute, value);
				break;
			}
		}
		return null;
	}

	/**
	 * read attribute
	 * @return the attribute name
	 */
	private String readAttribute() {
		String attribute = "";
		while (! this.isEndReading()) {
			this.readChar();
			if (this.currentChar() == End.ATTRIBUTE) {
				return attribute;
			}
			attribute += this.currentChar();
		}
		return null;
	}

	/**
	 * read value array
	 * @return the value array
	 */
	private IRValue readArray() {
		List<IRValue> values = new ArrayList<IRValue>();
		while (! this.isEndReading()) {
			this.readCharWithoutSpace();
			switch (this.currentChar()) {
			case End.VALUE :
				return new IRArray(values.toArray(new IRValue[values.size()]));
			case Start.VALUE :
				IRValue value = this.readIRValue();
				Objects.requireNonNull(value);
				values.add(value);
			}
		}
		return null;
	}

	/**
	 * read base value
	 * @param type the type of base value
	 * @return the base value
	 */
	private IRValue readBaseValue(String type) {
		String valueString = "";
		this.readCharWithoutSpace();
		while (! this.isEndReading()) {
			if (this.currentChar() == End.VALUE) {
				switch (type) {
				case TYPE.BOOLEAN :
					return new IRBoolean(Boolean.parseBoolean(valueString));
				case TYPE.STRING :
					return new IRString(valueString);
				case TYPE.INTEGER :
					return new IRInteger(Integer.parseInt(valueString));
				case TYPE.DOUBLE :
					return new IRDouble(Double.parseDouble(valueString));
				case TYPE.NULL :
					return new IRNull();
				}
			}
			valueString += this.currentChar();
			this.readChar();
		}
		return null;
	}
		
	
}
