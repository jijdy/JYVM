package com.jyvm.natice.java;

import com.jyvm.natice.NativeMethod;
import com.jyvm.natice.Registry;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.heap.StringPool;
import com.jyvm.runtimeDate.heap.method.Object;

public class _String {

    public _String() {
        String jlString = "java/lang/String";
        Registry.register(jlString, "intern", "()Ljava/lang/String;", new NativeMethod(this, "intern"));
        Registry.register(jlString,"registerNatives", "()V",new NativeMethod(this,"registerNatives"));
    }

    public void registerNatives(Frame frame) {
        // do nothing
    }

    public void intern(Frame frame){
        Object thiz = (Object) frame.getLocalVars().getThis();
        Object interned = StringPool.internString(thiz);
        frame.getOperandStack().pushRef(interned);
    }
}
