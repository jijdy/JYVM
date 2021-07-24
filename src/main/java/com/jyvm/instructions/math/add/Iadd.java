package com.jyvm.instructions.math.add;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;

/*
* 将栈顶的两个元素相加并存放的栈顶，int类型
* */
public class Iadd extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int val1 = stack.popInt();
        int val2 = stack.popInt();
        stack.pushInt(val1 + val2);
    }
}
