package anaofind.lib.anadatair.util;

/**
 * determine the type of object or primitive
 */
public class TypeResolver {
	
	/**
	 * long type string
	 */
    public static final String LONG = "long";
    
    /**
     * integer type string
     */
    public static final String INT = "int";
    
    /**
     * floar type string
     */
    public static final String FLOAT = "float";
    
    /**
     * double type string
     */
    public static final String DOUBLE = "double";
    
    /**
     * char type string
     */
    public static final String CHAR = "char";
    
    /**
     * boolean type string
     */
    public static final String BOOLEAN = "boolean";
    
    /**
     * short type string
     */
    public static final String SHORT = "short";
    
    /**
     * byte type string
     */
    public static final String BYTE = "byte";
    
    /**
     * string type string
     */
    public static final String STRING = "string";
    
    /**
     * array type string
     */
    public static final String ARRAY = "array";
    
    /**
     * object type string
     */
    public static final String OBJECT = "object";
    
    /**
     * null type string
     */
    public static final String NULL = "object";

    /**
     * get type for integer
     * @param x the value
     * @return the type
     */
    public static String getType(int x)
    {
        return TypeResolver.INT;
    }

    /**
     * get type for byte
     * @param x the value
     * @return the type
     */
    public static String getType(byte x)
    {
        return TypeResolver.BYTE;
    }
    
    /**
     * get type for floate
     * @param x the value
     * @return the type
     */
    public static String getType(float x)
    {
        return TypeResolver.FLOAT;
    }

    /**
     * get type for double
     * @param x the value
     * @return the type
     */
    public static String getType(double x)
    {
        return TypeResolver.DOUBLE;
    }

    /**
     * get type for boolean
     * @param x the value
     * @return the type
     */
    public static String getType(boolean x)
    {
        return TypeResolver.BOOLEAN;
    }

    /**
     * get type for short
     * @param x the value
     * @return the type
     */
    public static String getType(short x)
    {
        return TypeResolver.SHORT;
    }

    /**
     * get type for long
     * @param x the value
     * @return the type
     */
    public static String getType(long x)
    {
        return TypeResolver.LONG;
    }

    /**
     * get type for char
     * @param x the value
     * @return the type
     */
    public static String getType(char x)
    {
        return TypeResolver.CHAR;
    }
}

