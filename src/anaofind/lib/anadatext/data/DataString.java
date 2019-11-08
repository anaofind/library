package anaofind.lib.anadatext.data;

import java.util.Objects;

/**
 * data string
 * @author anaofind
 */
public class DataString implements DataValue{

	/**
	 * the value
	 */
	private String[] value = new String[1];
	
	/**
	 * construct
	 * @param value the value
	 */
	public DataString(String value) {
		Objects.requireNonNull(value);
		this.value[0] = value;
	}
	

	@Override
	public String[] getArrayString() {
		return this.value;
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public String getContainsString() {
		return "String : \"" + this.value[0] + "\"";
	}

}
