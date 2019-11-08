package anaofind.lib.anadatext.data;

/**
 * data null
 * @author anaofind
 */
public class DataNull implements DataValue{

	@Override
	public boolean isNull() {
		return true;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public String getContainsString() {
		return "Null";
	}

}
