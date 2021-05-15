package anaofind.anadatair;

import java.util.Arrays;

import anaofind.lib.anadatair.util.UtilAnadatair;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;

public class TestJSON {

	@Property
	boolean singleString(@ForAll("getString") String value) {
		return UtilAnadatair.decode(UtilAnadatair.encode(value), String.class).equals(value);
	}
	
	@Property
	boolean singleBoolean(@ForAll boolean value) {
		return UtilAnadatair.decode(UtilAnadatair.encode(value), boolean.class) == value;
	}
	
	@Property
	boolean singleInt(@ForAll int value) {
		return UtilAnadatair.decode(UtilAnadatair.encode(value), int.class) == value;
	}
	
	@Property
	boolean singleDouble(@ForAll double value) {
		return UtilAnadatair.decode(UtilAnadatair.encode(value), double.class) == value;
	}
	
	@Property
	boolean singleLong(@ForAll long value) {
		return UtilAnadatair.decode(UtilAnadatair.encode(value), long.class) == value;
	}
	
	@Property
	boolean singleArrayString(@ForAll("getArrayString") String[] value) {
		return Arrays.equals(UtilAnadatair.decode(UtilAnadatair.encode(value), String[].class), value);
	}
	
	@Property
	boolean singleArrayBoolean(@ForAll boolean[] value) {
		return Arrays.equals(UtilAnadatair.decode(UtilAnadatair.encode(value), boolean[].class), value);
	}
	
	@Property
	boolean singleArrayInt(@ForAll int[] value) {
		return Arrays.equals(UtilAnadatair.decode(UtilAnadatair.encode(value), int[].class), value);
	}
	
	@Property
	boolean singleArrayDouble(@ForAll double[] value) {
		return Arrays.equals(UtilAnadatair.decode(UtilAnadatair.encode(value), double[].class), value);
	}
	
	@Property
	boolean singleArrayLong(@ForAll long[] value) {
		return Arrays.equals(UtilAnadatair.decode(UtilAnadatair.encode(value), long[].class), value);
	}

	@Property
	boolean singleArrayPritivies(
			@ForAll("getString") String value1, 
			@ForAll boolean value2,
			@ForAll int value3,
			@ForAll double value4,
			@ForAll long value5
			) {
		Object[] value = new Object[] {value1, value2, value3, value4, value5};
		return Arrays.equals(UtilAnadatair.decode(UtilAnadatair.encode(value), Object[].class), value);
	}
	
	@Provide
	Arbitrary<String> getString() {  
		return Arbitraries.strings().ofMinLength(1)
				.withCharRange('a', 'z')
				.withCharRange('A', 'Z')
				.withCharRange('1', '9')
				.withChars("_/()*+-");
	}

	@Provide
	Arbitrary<String[]> getArrayString() {  
		return Arbitraries.strings().ofMinLength(1)
				.withCharRange('a', 'z')
				.withCharRange('A', 'Z')
				.withCharRange('1', '9')
				.withChars("_/()*+-")
				.array(String[].class);
	}
}
