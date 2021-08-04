package com.jyvm.instructions.references;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;
import com.jyvm.runtimeDate.heap.method.Object;

/*
* 用于计算数组的长度的指令,通过Object类中的方法直接获取长度
* 并将长度存放在操作数栈顶中
* */
public class Array_length extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Object ref = stack.popRef();
        if (null == ref) {
            throw new NullPointerException();
        }
        int length = ref.arrayLength();
        stack.pushInt(length);
    }
}
