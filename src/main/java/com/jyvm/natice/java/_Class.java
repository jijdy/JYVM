package com.jyvm.natice.java;

import com.jyvm.natice.NativeMethod;
import com.jyvm.natice.Registry;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.LocalVars;
import com.jyvm.runtimeDate.OperandStack;
import com.jyvm.runtimeDate.heap.ClassLoader;
import com.jyvm.runtimeDate.heap.StringPool;
import com.jyvm.runtimeDate.heap.method.Class;
import com.jyvm.runtimeDate.heap.method.Object;

public class _Class {

    public _Class() {
        String jlClass = "java/lang/Class";
        Registry.register(jlClass, "getPrimitiveClass", "(Ljava/lang/String;)Ljava/lang/Class;", new NativeMethod(this, "getPrimitiveClass"));
        Registry.register(jlClass, "getName0", "()Ljava/lang/String;", new NativeMethod(this, "getName0"));
        Registry.register(jlClass, "desiredAssertionStatus0", "(Ljava/lang/Class;)Z", new NativeMethod(this, "desiredAssertionStatus0"));
        Registry.register(jlClass, "registerNatives", "()V", new NativeMethod(this, "registerNatives"));
    }

    public void registerNatives(Frame frame) {
        // do nothing
    }

    public void getPrimitiveClass(Frame frame) {
        Object nameObj = (Object) frame.getLocalVars().getRef(0);
        String name = StringPool.goString(nameObj);

        ClassLoader loader = frame.method().clazz.loader;
        Object jClass = loader.loadClass(name).jClass;

        frame.getOperandStack().pushRef(jClass);
    }

    public void getName0(Frame frame) {
        Object thiz = (Object) frame.getLocalVars().getThis();
        Class clazz = (Class) thiz.extra();

        String name = "虚拟机本地方法getName0获取类名：" + clazz.javaName();
        Object nameObj = StringPool.jString(clazz.loader, name);

        frame.getOperandStack().pushRef(nameObj);
    }

    public void desiredAssertionStatus0(Frame frame) {
        frame.getOperandStack().pushBoolean(false);
    }

    public void isInterface(Frame frame) {
        LocalVars vars = frame.getLocalVars();
        Object thiz = (Object) vars.getThis();
        Class clazz = (Class) thiz.extra();

        OperandStack stack = frame.getOperandStack();
        stack.pushBoolean(clazz.isInterface());
    }

    public void isPrimitive(Frame frame) {
        LocalVars vars = frame.getLocalVars();
        Object thiz = (Object) vars.getThis();
        Class clazz = (Class) thiz.extra();

        OperandStack stack = frame.getOperandStack();
        stack.pushBoolean(clazz.isPrimitive());
    }
}
