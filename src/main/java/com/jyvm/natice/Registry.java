package com.jyvm.natice;

import com.jyvm.natice.java.*;
import java.util.HashMap;
import java.util.Map;

/*
 *用于注册和发现本地方法
 * */
public class Registry {

    private static final Map<String, NativeMethod> registry = new HashMap<>();

    public static void initNative() {
        new _Class();
        new _Double();
        new _Float();
        new _Object();
        new _String();
        new _System();
    }

    public static void register(String className, String methodName, String desc, NativeMethod method) {
        String key = className + "~" + methodName + "~" + desc;
        registry.put(key, method);
    }

    public static NativeMethod findNativeMethod(String className, String methodName, String methodDescriptor) {
        String key = className + "~" + methodName + "~" + methodDescriptor;
        return registry.get(key);
    }

}
