package com.jyvm.instructions.math.sub;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;

/*
* 将操作数栈顶的两个元素进行减法操作，栈深-栈浅，int类型
* */
public class Isub extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int val1 = stack.popInt();
        int val2 = stack.popInt();
        stack.pushInt(val2 - val1);
    }
}
