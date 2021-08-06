package com.jyvm.runtimeDate.heap;

import com.jyvm.runtimeDate.heap.method.Object;

import java.util.HashMap;
import java.util.Map;

/*
* 字符串常量池，使用HashMap来模拟String中的Hash值和Value的char[]值
* */
public class StringPool {
    private static final Map<String, Object> internedStrs = new HashMap<>();

    public static Object jString(ClassLoader loader, String goStr) {
        Object internedStr = internedStrs.get(goStr);
        if (null != internedStr) return internedStr;

        char[] chars = goStr.toCharArray();
        Object jChars = new Object(loader.loadClass("[C"), chars);

        Object jStr = loader.loadClass("java/lang/String").newObject();
        jStr.setRefVal("value", "[C", jChars);
        //将新的字符串加入到常量池的map存储结构中
        internedStrs.put(goStr, jStr);
        return jStr;
    }

    public static String goString(Object jStr) {
        Object charArr = jStr.getRefVar("value", "[C");
        return new String(charArr.chars());
    }

    public static Object internString(Object jStr) {
        String goStr = goString(jStr);
        Object internedStr = internedStrs.get(goStr);
        if (null != internedStr) return internedStr;

        internedStrs.put(goStr, jStr);
        return jStr;
    }
}
