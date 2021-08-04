package com.jyvm.instructions.references;

import com.jyvm.instructions.base.ClassInitLogic;
import com.jyvm.instructions.base.Index16Instruction;
import com.jyvm.instructions.base.MethodInvokeLogic;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.heap.constantpool.MethodRef;
import com.jyvm.runtimeDate.heap.constantpool.RuntimePool;
import com.jyvm.runtimeDate.heap.method.Class;
import com.jyvm.runtimeDate.heap.method.Method;

/*
* 调用static方法，进行静态方法的调用
* */
public class Invoke_static extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        RuntimePool runtimePool = frame.method().clazz.runtimePool;
        MethodRef methodRef = (MethodRef) runtimePool.getConstant(this.index);
        Method method = methodRef.getMethod();

        if (!method.isStatic()) {
            throw new IncompatibleClassChangeError();
        }

        Class clazz = method.clazz;
        if (!clazz.initStarted()) {
            frame.revertNextPC();
            ClassInitLogic.initClass(frame.thread(), clazz);
            return;
        }
        //最终执行的方法
        MethodInvokeLogic.invokeMethod(frame, method);
    }
}
