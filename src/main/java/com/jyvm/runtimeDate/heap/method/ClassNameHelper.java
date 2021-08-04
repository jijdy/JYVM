package com.jyvm.runtimeDate.heap.method;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用map来将类型描述符进行存储，再将其进行对比
 */
class ClassNameHelper {

    static Map<String, String> primitiveTypes = new HashMap<String, String>() {
        {
            put("void", "V");
            put("boolean", "Z");
            put("byte", "B");
            put("short", "S");
            put("int", "I");
            put("long", "J");
            put("char", "C");
            put("float", "F");
            put("double", "D");
        }
    };

    // [XXX -> [[XXX
    // int -> [I
    // XXX -> [LXXX;
    static String getArrayClassName(String className) {
        return "[" + toDescriptor(className);
    }

    static String getComponentClassName(String className) {
        if (className.getBytes()[0] == '[') {
            String componentTypeDescriptor = className.substring(1);
            return toClassName(componentTypeDescriptor);
        }
        throw new RuntimeException("Not array " + className);
    }

    private static String toDescriptor(String className) {
        if (className.getBytes()[0] == '[') {
            return className;
        }

        String d = primitiveTypes.get(className);
        if (null != d) return d;

        return "L" + className + ";";
    }

    private static String toClassName(String desc) {
        byte descByte = desc.getBytes()[0];
        if (descByte == '[') {
            return desc;
        }

        if (descByte == 'L') {
            return desc.substring(1, desc.length() - 1);
        }

        for (Map.Entry<String, String> entry : primitiveTypes.entrySet()) {
            if (entry.getValue().equals(desc)) {
                //primitive
                return entry.getKey();
            }
        }

        throw new RuntimeException("Invalid descriptor " + desc);

    }

}
