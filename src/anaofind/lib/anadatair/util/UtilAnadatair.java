package anaofind.lib.anadatair.util;

import java.util.*;

import anaofind.lib.anadatair.*;
import anaofind.lib.anadatair.compilator.*;
import anaofind.lib.anadatair.compilator.Compilator.CompilatorException;

/**
 * util anadatair method
 * @author anaofind
 */
public class UtilAnadatair {

	/**
	 * decode json to anadatair
	 * @param json the json text
	 * @return the anadatair equivalent
	 * @throws CompilatorException 
	 */
	public static Anadatair jsonDecode(String json) {
		CompilatorJSON cmp;
		try {
			cmp = new CompilatorJSON(json);
			return cmp.getValue().toAnadatair();
		} catch (CompilatorException e) {
			System.out.println("ERROR JSON DECODE");
		}
		return new AnadatairNull();
	}
	
	/**
	 * encode anadatair to json string
	 * @param anadatair the anadatair to encode
	 * @return the json string equivalent to anadatair
	 */
	public static String jsonEncode(Anadatair anadatair) {
		return anadatair.toJson().toString();
	}
	
	/**
	 * decode object to anadatair
	 * @param value the object to decode
	 * @return anadatair equivalent
	 */
	public static <T> Anadatair encode(T value) {
		if (value == null) {
			return new AnadatairNull();
		}
		if (value.getClass().equals(Integer.class))  {
			return new AnadatairInteger((Integer) value);
		}
		if (value.getClass().equals(Double.class))  {
			return new AnadatairDouble((Double) value);
		}
		if (value.getClass().equals(Boolean.class))  {
			return new AnadatairBoolean((Boolean) value);
		}
		if (value.getClass().equals(String.class))  {
			return new AnadatairString((String) value);
		}
		if (value.getClass().equals(String.class))  {
			return new AnadatairString((String) value);
		}
		if (value instanceof Anadatair)  {
			return (Anadatair) value;
		}
		return new AnadatairNull();
	}
	
	/**
	 * decode collection of objects to anadatair
	 * @param values the collection of objects
	 * @return anadatair equivalent
	 */
	public static <T> Anadatair encode(Collection<T> values) {
		if (values == null) {
			return new AnadatairNull();
		}
		AnadatairArray array = new AnadatairArray();
		for (Object object : values) {
			array.add(object);
		}
		return array;
	}
	
	/**
	 * decode array of objects to anadatair
	 * @param values the array of objects
	 * @return anadatair equivalent
	 */
	public static <T> Anadatair encode(T[] values) {
		if (values == null) {
			return new AnadatairNull();
		}
		AnadatairArray array = new AnadatairArray();
		for (Object object : values) {
			array.add(object);
		}
		return array;
	}

	/**
	 * decode array of objects to anadatair
	 * @param values the array of objects
	 * @return anadatair equivalent
	 */
	public static <T> Anadatair encode(Map<String, T> values) {
		if (values == null) {
			return new AnadatairNull();
		}
		AnadatairObject object = new AnadatairObject();
		for (String attribute : values.keySet()) {
			object.add(attribute, values.get(attribute));
		}
		return object;
	}
}
