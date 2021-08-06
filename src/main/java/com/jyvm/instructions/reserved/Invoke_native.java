package com.jyvm.instructions.reserved;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.natice.NativeMethod;
import com.jyvm.natice.Registry;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.heap.method.Method;

/*
* 根据类名，方法名，方法描述符从本地方法注册表查找并调用本地方法
* */
public class Invoke_native extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        Method method = frame.method();
        String className = method.clazz.name;
        String methodName = method.name();
        String methodDesc = method.desc();
        NativeMethod nativeMethod = Registry.findNativeMethod(className,methodName,methodDesc);
        if (null == nativeMethod) {
            String methodInfo = className + " " + methodName + methodDesc;
            throw new UnsatisfiedLinkError(methodInfo);
        }

        nativeMethod.invoke(frame);
    }
}
