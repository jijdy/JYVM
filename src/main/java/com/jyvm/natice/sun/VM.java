package com.jyvm.natice.sun;

import com.jyvm.instructions.base.MethodInvokeLogic;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.heap.StringPool;
import com.jyvm.runtimeDate.heap.method.Class;
import com.jyvm.runtimeDate.heap.method.Method;
import com.jyvm.runtimeDate.heap.method.Object;

/*
* 使用jvm的拆箱和装箱操作
* */
public class VM {

    public void initialize(Frame frame) {
        Class vmClass = frame.method().clazz;
        Object savedProps = vmClass.getRefVar("savedProps", "Ljava/util/Properties;");
        Object key = StringPool.jString(vmClass.loader, "foo");
        Object val = StringPool.jString(vmClass.loader, "bar");

        frame.getOperandStack().pushRef(savedProps);
        frame.getOperandStack().pushRef(key);
        frame.getOperandStack().pushRef(val);

        Class propsClass = vmClass.loader.loadClass("java/util/Properties");
        Method setPropMethod = propsClass.getInstanceMethod("setProperty",
                "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;");
        MethodInvokeLogic.invokeMethod(frame, setPropMethod);
    }
}
