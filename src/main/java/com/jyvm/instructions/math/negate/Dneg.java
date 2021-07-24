package com.jyvm.instructions.math.negate;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;

/*
* 使得栈顶元素为其反值 否运算
* */
public class Dneg extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        double val = stack.popDouble();
        stack.pushDouble(-val);
    }
}
