package com.jyvm.instructions.math.sub;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;

/*
* 将操作数栈顶的两个元素进行减法操作，栈深-栈浅，long类型
* */
public class Lsub extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        long val1 = stack.popLong();
        long val2 = stack.popLong();
        stack.pushLong(val2 - val1);
    }
}
