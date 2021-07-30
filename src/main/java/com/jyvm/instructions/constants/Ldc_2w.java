package com.jyvm.instructions.constants;

import com.jyvm.instructions.base.Index16Instruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;
import com.jyvm.runtimeDate.heap.constantpool.RuntimePool;

/*
* 从运行时常量池中获取到的数据为long和double这样的双占位数据
* */
public class Ldc_2w extends Index16Instruction {

    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        RuntimePool pool = frame.method().clazz.runtimePool;
        Object ref = pool.getConstant(this.index);
        if (ref instanceof Long) {
            stack.pushLong((Long) ref);
        } else if (ref instanceof Double) {
            stack.pushDouble((Double) ref);
        } else
            throw new ClassFormatError();
    }
}
