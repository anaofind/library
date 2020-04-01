package anaofind.lib.anadatair.util;

import java.util.*;

import anaofind.lib.anadatair.*;
import anaofind.lib.anadatair.compilator.*;
import anaofind.lib.anadatair.compilator.Compilator.CompilatorException;
import anaofind.lib.anadatair.reader.*;
import anaofind.lib.anaofile.*;

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
	 * json decode file
	 * @param pathFile the path file
	 * @return the anadatair equivalent
	 */
	public static Anadatair jsonDecodeFile(String pathFile) {
		Reader file = new ReaderFile(pathFile);
		String json = "";
		while (! file.isEndReading()) {
			file.readLine();
			json += file.currentLine();
		}
		return jsonDecode(json);
	}
	
	/**
	 * encode anadatair to json string in file
	 * @param anadatair the anadatair to encode
	 * @param pathFile the path of file to encode
	 */
	public static void jsonEncodeFile(Anadatair anadatair, String pathFile) {
		AnaoFile file = new AnaoFile(pathFile);
		if (! file.exist()) {
			file.create();
		}
		file.writer().write(jsonEncode(anadatair));
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
