package com.jyvm.natice.java;

import com.jyvm.natice.NativeMethod;
import com.jyvm.natice.Registry;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.heap.method.Class;
import com.jyvm.runtimeDate.heap.method.Object;

public class _Object {

    public _Object() {
        String jlObject = "java/lang/Object";
        Registry.register(jlObject, "getClass", "()Ljava/lang/Class;", new NativeMethod(this, "getClass"));
        Registry.register(jlObject,"hashCode", "()I",new NativeMethod(this,"_hashCode"));
        Registry.register(jlObject,"clone", "()Ljava/lang/Object;",new NativeMethod(this,"_clone"));
        Registry.register(jlObject,"registerNatives", "()V",new NativeMethod(this,"registerNatives"));
    }

    public void registerNatives(Frame frame) {
        // do nothing
    }

    public void getClazz(Frame frame) {
        Object thiz = (Object) frame.getLocalVars().getThis();
        Object clazz = thiz.clazz().jClass;
        frame.getOperandStack().pushRef(clazz);
    }

    public void _hashCode(Frame frame) {
        Object thiz = (Object) frame.getLocalVars().getThis();
        frame.getOperandStack().pushInt(thiz.hashCode());
    }

    public void _clone(Frame frame) throws CloneNotSupportedException {
        Object thiz = (Object) frame.getLocalVars().getThis();

        Class cloneable = thiz.clazz().loader.loadClass("java/lang/Cloneable");

        if (!thiz.clazz().isImplements(cloneable)) {
            throw new CloneNotSupportedException();
        }

        frame.getOperandStack().pushRef(thiz);
    }

}
