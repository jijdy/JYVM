package com.jyvm.instructions.math.add;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;

/*
 * 将栈顶的两个元素相加并存放的栈顶，double类型
 * */
public class Dadd extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        double val1 = stack.popDouble();
        double val2 = stack.popDouble();
        stack.pushDouble(val1 + val2);
    }
}
