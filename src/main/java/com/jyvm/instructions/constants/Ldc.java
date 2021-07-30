package com.jyvm.instructions.constants;

import com.jyvm.instructions.base.Index8Instruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;
import com.jyvm.runtimeDate.heap.constantpool.ClassRef;
import com.jyvm.runtimeDate.heap.constantpool.RuntimePool;
import com.jyvm.runtimeDate.heap.method.Class;

/*
* 从运行时常量池中加载常量数据，并推入到操作数栈顶
* 有int,float,的操作，为实现字符串和数组的相关操作
* */
public class Ldc extends Index8Instruction {

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
