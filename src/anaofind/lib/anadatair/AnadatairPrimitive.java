package anaofind.lib.anadatair;

/**
 * anadatair primitive
 * @author anaofind
 *
 */
public abstract class AnadatairPrimitive implements Anadatair{

	@Override
	public Anadatair getData(String attribute) {
		return null;
	}
	
	@Override
	public Anadatair getData(int index) {
		if (index == 0) {
			return this;
		}
		return null;
	}
	
	@Override
	public String getString(String attribute) {
		return null;
	}

	@Override
	public Long getInteger(String attribute) {
		return null;
	}

	@Override
	public Double getDouble(String attribute) {
		return null;
	}

	@Override
	public Boolean getBoolean(String attribute) {
		return null;
	}

	@Override
	public int size() {
		return 1;
	}
}
