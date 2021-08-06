package com.jyvm.natice;

import com.jyvm.runtimeDate.Frame;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
*使用该类来模拟本地方法的运行环境
* */
public class NativeMethod {

    private final String methodName;
    private final Object object;

    public NativeMethod(Object o, String methodName) {
        this.methodName = methodName;
        this.object = o;
    }

    //使用frame传入参数连接jvm
    public void invoke(Frame frame) {
        try {
            Method method = object.getClass().getMethod(methodName,frame.getClass());
            method.invoke(object, frame);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
