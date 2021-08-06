package com.jyvm.natice.java;

import com.jyvm.natice.NativeMethod;
import com.jyvm.natice.Registry;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.LocalVars;
import com.jyvm.runtimeDate.heap.method.Class;
import com.jyvm.runtimeDate.heap.method.Object;

public class _System {

    public _System() {
        String jlSystem = "java/lang/System";
        Registry.register(jlSystem, "arraycopy", "()Ljava/lang/String;", new NativeMethod(this, "arraycopy"));
        Registry.register(jlSystem,"registerNatives", "()V",new NativeMethod(this,"registerNatives"));
    }

    public void registerNatives(Frame frame) {
        // do nothing
    }

    public void arraycopy(Frame frame) {
        LocalVars vars = frame.getLocalVars();
        Object src = (Object) vars.getRef(0);
        int srcPos = vars.getInt(1);
        Object dest = (Object) vars.getRef(2);
        int destPos = vars.getInt(4);
        int length = vars.getInt(4);

        if (null == src || dest == null) {
            throw new NullPointerException();
        }

        if (!checkArrayCopy(src, dest)) {
            throw new ArrayStoreException();
        }

        if (srcPos < 0 || destPos < 0 || length < 0 ||
                srcPos + length > src.arrayLength() ||
                destPos + length > dest.arrayLength()) {
            throw new IndexOutOfBoundsException();
        }

        System.arraycopy(src, srcPos, dest, destPos, length);

       //todo

    }

    public boolean checkArrayCopy(Object src, Object dest) {
        Class srcClass = src.clazz();
        Class destClass = dest.clazz();

        if (!srcClass.isArray() || !destClass.isArray()) {
            return false;
        }

        if (srcClass.componentClass().isPrimitive() || destClass.componentClass().isPrimitive()) {
            return srcClass == destClass;
        }

        return true;

    }
}
