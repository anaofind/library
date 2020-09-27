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
	public int size() {
		return 1;
	}
	
	@Override
	public boolean equals(Anadatair other) {
		return other.getValue().equals(this.getValue());
	}
}
