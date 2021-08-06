package com.jyvm.instructions.constants;

import com.jyvm.instructions.base.Index8Instruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;
import com.jyvm.runtimeDate.heap.StringPool;
import com.jyvm.runtimeDate.heap.constantpool.RuntimePool;
import com.jyvm.runtimeDate.heap.method.Class;
import com.jyvm.runtimeDate.heap.method.Object;

/*
和Ldc没有什么差距
* */
public class Ldc_w extends Index8Instruction {

    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Class clazz = frame.method().clazz;
        RuntimePool pool = clazz.runtimePool;
        java.lang.Object ref = pool.getConstant(this.index);
        if (ref instanceof Integer) {
            stack.pushInt((Integer) ref);
        } else if (ref instanceof Float) {
            stack.pushFloat((Float) ref);
        }else if (ref instanceof String) {
            Object internedStr = StringPool.jString(clazz.loader, (String) ref);
            stack.pushRef(internedStr);
        }
        throw new RuntimeException("todo Ldc_w");
    }

}
