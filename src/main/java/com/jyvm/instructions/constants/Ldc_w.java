package com.jyvm.instructions.constants;

import com.jyvm.instructions.base.Index8Instruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;
import com.jyvm.runtimeDate.heap.constantpool.ClassRef;
import com.jyvm.runtimeDate.heap.constantpool.RuntimePool;

/*
和Ldc没有什么差距
* */
public class Ldc_w extends Index8Instruction {

    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        RuntimePool pool = frame.method().clazz.runtimePool;
        Object ref = pool.getConstant(this.index);
        if (ref instanceof Integer) {
            stack.pushInt((Integer) ref);
        } else if (ref instanceof Float) {
            stack.pushFloat((Float) ref);
        }
        //String \ ClassRef \ 为实现
    }

}
