package anaofind.lib.anadatext.data;

/**
 * data double
 * @author anaofind
 */
public class DataDouble implements DataValue{

	/**
	 * the value
	 */
	double[] value = new double[1];
	
	/**
	 * construct
	 * @param value the value
	 */
	public DataDouble(double value) {
		this.value[0] = value;
	}
	

	@Override
	public double[] getArrayDouble() {
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
		return "Double : " + this.value[0];
	}

}
