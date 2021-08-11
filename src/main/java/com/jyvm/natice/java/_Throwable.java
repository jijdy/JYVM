package com.jyvm.natice.java;

import com.jyvm.natice.NativeMethod;
import com.jyvm.natice.Registry;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.Thread;
import com.jyvm.runtimeDate.heap.method.Class;
import com.jyvm.runtimeDate.heap.method.Method;
import com.jyvm.runtimeDate.heap.method.Object;

/*
* 通过本地调用实现Throwable类方法
* */
public class _Throwable {

    private StackTraceElement stackTraceElement;

    public _Throwable() {
        String jlThrowable = "java/lang/Throwable";
        Registry.register(jlThrowable, "fillInStackTrace", "(I)Ljava/lang/Throwable;", new NativeMethod(this, "fillInStackTrace"));
        Registry.register(jlThrowable, "registerNatives", "()V", new NativeMethod(this, "registerNatives"));
    }

    public void registerNatives(Frame frame) {
        // do nothing
    }

    public String string() {
        return String.format("%s.%s(%s:%d)", this.stackTraceElement.className, this.stackTraceElement.methodName, this.stackTraceElement.fileName, this.stackTraceElement.lineNumber);
    }

    public void fillInStackTrace(Frame frame) {
        Object thiz = (Object) frame.getLocalVars().getThis();
        frame.getOperandStack().pushRef(thiz);

        _Throwable[] stes = createStackTraceElements(thiz, frame.thread());
        thiz.setExtra(stes);
    }

    private _Throwable[] createStackTraceElements(Object tObj, Thread thread) {
        int skip = distanceToObject(tObj.clazz()) + 2;
        Frame[] frames = thread.getFrames();
        _Throwable[] stes = new _Throwable[frames.length - skip];
        int idx = 0;
        for (int i = skip; i < frames.length; i++) {
            stes[idx] = createStackTraceElement(frames[i]);
        }
        return stes;
    }

    private int distanceToObject(Class clazz) {
        int distance = 0;
        for (Class c = clazz.superClass; c != null; c = c.superClass) {
            distance++;
        }
        return distance;
    }

    private _Throwable createStackTraceElement(Frame frame) {
        Method method = frame.method();
        Class clazz = method.clazz;
        StackTraceElement stackTraceElement = new StackTraceElement();
        stackTraceElement.fileName = clazz.sourceFile();
        stackTraceElement.className = clazz.javaName();
        stackTraceElement.methodName = method.name();
        stackTraceElement.lineNumber = method.getLineNumber(frame.nextPC() - 1);
        _Throwable throwable = new _Throwable();
        throwable.stackTraceElement = stackTraceElement;
        return throwable;
    }

    private static class StackTraceElement {
        private String fileName;
        private String className;
        private String methodName;
        private int lineNumber;
    }

}
