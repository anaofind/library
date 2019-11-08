package anaofind.lib.anadatatext.ir;

import java.util.Objects;

import anaofind.lib.anadatext.data.*;

import java.util.*;

/**
 * compilator ir
 * @author anaofind
 *
 */
public class IRCompilator extends DataCompilator{

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
	 * @param ir the ir text
	 */
	public IRCompilator(String ir) {
		super(ir);
		this.value = this.readIRValue();
		Objects.requireNonNull(this.value);
	}

	/**
	 * read the next ir value
	 * @return the ir value readed
	 */
	private IRValue readIRValue() {
		while (! this.isEndRead()) {
			if (this.currentChar == Start.VALUE) {
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
		while (! this.isEndRead()) {
			this.readCharWithoutSpace();
			if (this.currentChar == End.TYPE) {
				return type;
			}
			type += this.currentChar;
		}
		return null;
	}

	/**
	 * read object
	 * @return the object
	 */
	private IRValue readObject() {
		IRObject object = new IRObject();
		while (! this.isEndRead()) {
			this.readCharWithoutSpace();
			switch (this.currentChar) {
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
		while (! this.isEndRead()) {
			this.readChar();
			if (this.currentChar == End.ATTRIBUTE) {
				return attribute;
			}
			attribute += this.currentChar;
		}
		return null;
	}

	/**
	 * read value array
	 * @return the value array
	 */
	private IRValue readArray() {
		List<IRConverter> values = new ArrayList<IRConverter>();
		while (! this.isEndRead()) {
			this.readCharWithoutSpace();
			switch (this.currentChar) {
			case End.VALUE :
				return new IRArray(values.toArray(new IRValue[values.size()]));
			case Start.VALUE :
				IRConverter value = this.readIRValue();
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
		while (! this.isEndRead()) {
			if (this.currentChar == End.VALUE) {
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
			valueString += this.currentChar;
			this.readChar();
		}
		return null;
	}
	
	/**
	 * get data
	 */
	public DataValue getData() {
		return this.value.toDataValue();
	}
	
	/**
	 * test main method
	 * @param args the args
	 */
	public static void main(String[] args) {
		IRCompilator comp = new IRCompilator("(object:<tableau>(array:(string:coucou dd)(double:5.0)(integer:10)(boolean:false)(null:))<valeur>(integer:3))");
		IRValue ir = comp.value;
		DataValue data = ir.toDataValue();
		System.out.println(comp.value.toIR());
		System.out.println(data.getContainsString());
	}

}
