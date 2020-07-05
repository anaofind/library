package anaofind.lib.anadatair.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
	 * decode json to java object
	 * @param json the json string
	 * @param model the class model
	 * @return the object java equivalent to json string
	 */
	public static <T> Object jsonDecodeObject(String json, Class<T> model) {
		return decode(jsonDecode(json), model);
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
	public static <T> Object[] castObjectInArray(T value) {
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
	public static Map<String, Object> castObjectInMapStringKey(Object object) {
		Map<String, Object> mapCasted = new HashMap<String, Object>();
		if (object instanceof Map<?,?>) {
			Map<?,?> map = (Map<?,?>) object;
			for (Object key : map.keySet()) {
				mapCasted.put(String.valueOf(key), map.get(key));
			}	
		} else {
			for (Field field : object.getClass().getFields()) {
				try {
					mapCasted.put(field.getName(), field.get(object));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return mapCasted;
	}

	/**
	 * encode object to anadatair
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
			Object[] array = castObjectInArray(value);
			return encode(array);
		}
		if (value instanceof Collection<?>){
			Collection<?> collection = (Collection<?>) value;
			return encode(collection);
		}
		if (value instanceof Anadatair) {			
			return (Anadatair) value;
		}
		
		Map<String, Object> map = castObjectInMapStringKey(value);
		return encode(map);
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
	 * decode anadatair to object java
	 * @param anadatair the anadatair
	 * @param model the class model
	 * @return the object java equivalent to anadatair
	 */
	@SuppressWarnings("unchecked")
	public static <T> T decode(Anadatair anadatair, Class<T> model) {
		if (anadatair != null) {	
			switch(anadatair.getType()) {
			case TypeResolver.ARRAY : 
				return decode((AnadatairArray) anadatair, model);
			case TypeResolver.OBJECT : 
				return decode((AnadatairObject) anadatair, model);
			}
			return (T) anadatair.getValue();
		}
		return null;
	}

	/**
	 * decode anadatair array
	 * @param <T> the type of array
	 * @param anadatair the anadatair array
	 * @param model the model array
	 * @return the array equivalent
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T decode(AnadatairArray anadatair, Class<T> model) {
		if (model.isArray()) {
			Object array = Array.newInstance(model.getComponentType(), anadatair.size());

			for (int i = 0; i < anadatair.size(); i++) {
				Array.set(array, i, decode(anadatair.getData(i), model.getComponentType()));
			}

			return (T) array;	
		}

		if (Collection.class.isAssignableFrom(model)) {
			Collection collection = null;
			if (List.class.isAssignableFrom(model)) {
				collection = new ArrayList<Object>();	
			}
			if (Set.class.isAssignableFrom(model)) {
				collection = new HashSet<Object>();
			}
			if (collection != null) {
				for (int i = 0; i < anadatair.size(); i++) {
					collection.add(anadatair.getData(i).getValue());
				}	
				return (T) collection;
			}
		}

		return null;
	}

	/**
	 * decode anadatair array
	 * @param <T> the type of array
	 * @param anadatair the anadatair array
	 * @param model the model array
	 * @return the array equivalent
	 */
	public static <T> T decode(AnadatairObject anadatair, Class<T> model) {
		try {
			System.out.println(model.getName());
			T instance = model.getConstructor().newInstance();
			for(Field field : model.getFields()) {
				if (anadatair.contains(field.getName())) {
					Object valueField = decode(anadatair.getData(field.getName()), field.getType());
					field.set(instance, valueField);	
				}
			}
			return (T) instance;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
}