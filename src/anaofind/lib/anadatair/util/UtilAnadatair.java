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
	 * encode anadatair to json pretty string
	 * @param anadatair the anadatair to encode
	 * @return the json pretty string equivalent to anadatair
	 */
	public static String jsonEncodePretty(Anadatair anadatair) {
		return anadatair.toJson().prettyString(0);
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
		file.writer().write(jsonEncodePretty(anadatair));
	}

	/**
	 * cast object to array object
	 * @param value the object to cast
	 * @return the array casted
	 */
	public static <T> Object[] castArrayObject(T value) {
		if (value.getClass().isArray()) {
			if (! (value instanceof Object[])) {
				if (value instanceof int[]) {
					int[] array = (int[]) value;
					Object[] arrayInt = new Integer[array.length];
					for (int i = 0; i<array.length; i++) {
						arrayInt[i] = array[i];
					}
					return arrayInt;
				}
				if (value instanceof double[]) {
					double[] array = (double[]) value;
					Object[] arrayDouble = new Double[array.length];
					for (int i = 0; i<array.length; i++) {
						arrayDouble[i] = array[i];
					}
					return arrayDouble;
				}
				if (value instanceof long[]) {
					long[] array = (long[]) value;
					Object[] arrayLong = new Long[array.length];
					for (int i = 0; i<array.length; i++) {
						arrayLong[i] = array[i];
					}
					return arrayLong;
				}
				if (value instanceof boolean[]) {
					boolean[] array = (boolean[]) value;
					Object[] arrayBoolean = new Boolean[array.length];
					for (int i = 0; i<array.length; i++) {
						arrayBoolean[i] = array[i];
					}
					return arrayBoolean;
				}
			} else {
				return (Object[]) value;	
			}
		}
		return new Object[0];
	}
	
	/**
	 * cast key of map in string
	 * @param map the map
	 * @return the map casted
	 */
	public static Map<String, Object> castMapStringKey(Object object) {
		Map<String, Object> mapCasted = new HashMap<String, Object>();
		if (object instanceof Map<?,?>) {
			Map<?,?> map = (Map<?,?>) object;
			for (Object key : map.keySet()) {
				mapCasted.put(String.valueOf(key), map.get(key));
			}	
		}
		return mapCasted;
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
		if (value.getClass().equals(Long.class))  {
			return new AnadatairLong((Long) value);
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
		if (value.getClass().isArray()) {
			Object[] array = castArrayObject(value);
			return encode(array);
		}
		if (value instanceof Collection<?>){
			Collection<?> collection = (Collection<?>) value;
			return encode(collection);
		}
		if (value instanceof Map<?,?>){
			Map<String, Object> map = castMapStringKey(value);
			
			return encode(map);
		}
		if (value instanceof Anadatair) {			
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
	
	/**
	 * decode anadatair primitif
	 * @param anadatair the anadatair
	 * @return the data decoded
	 */
	public static Object decodePrimitif(Anadatair anadatair) {
		switch(anadatair.getType()) {
		case TypeResolver.INT : 
			System.out.println(anadatair.toJson());
			return anadatair.getInteger();
		case TypeResolver.LONG : 
			return anadatair.getLong(); 
		case TypeResolver.DOUBLE : 
			return anadatair.getDouble(); 
		case TypeResolver.BOOLEAN : 
			return anadatair.getBoolean();
		case TypeResolver.STRING : 
			return anadatair.getString();
		case TypeResolver.NULL : 
			return null;
		}
		return decodePrimitif(anadatair.getData());
	}

	/**
	 * decode anadatair to array
	 * @param anadatatair the anadatair array
	 * @return the array decoded
	 */
	public static Object[] decodeArray(Anadatair anadatatair) {
		List<Object> array = new ArrayList<Object>();
		if (anadatatair.getType().equals(TypeResolver.ARRAY)) {
			for (int i = 0; i<anadatatair.size(); i++) {
				array.add(decodePrimitif(anadatatair.getData(i)));
			}	
		}
		return array.toArray(new Object[array.size()]);
	}
}
