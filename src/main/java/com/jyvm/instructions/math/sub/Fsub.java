package com.jyvm.instructions.math.sub;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;

/*
* 将操作数栈顶的两个元素进行减法操作，栈深-栈浅，float类型
* */
public class Fsub extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        float val1 = stack.popFloat();
        float val2 = stack.popFloat();
        stack.pushFloat(val2 - val1);
    }
}
