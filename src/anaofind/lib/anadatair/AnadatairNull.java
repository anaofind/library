package anaofind.lib.anadatair;

import anaofind.lib.anadatair.json.JsonNull;
import anaofind.lib.anadatair.json.JsonValue;
import anaofind.lib.anadatair.util.TypeResolver;

public class AnadatairNull extends AnadatairPrimitive {

	@Override
	public String getString(int index) {
		return null;
	}

	@Override
	public Long getInteger(int index) {
		return null;
	}

	@Override
	public Double getDouble(int index) {
		return null;
	}

	@Override
	public Boolean getBoolean(int index) {
		return null;
	}

	@Override
	public String getType() {
		return TypeResolver.NULL;
	}

	@Override
	public JsonValue toJson() {
		return new JsonNull();
	}

	@Override
	public boolean equals(Anadatair other) {
		return other.getType().equals(TypeResolver.NULL);
	}

}
