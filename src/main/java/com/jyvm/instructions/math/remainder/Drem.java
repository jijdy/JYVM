package com.jyvm.instructions.math.remainder;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;

/*
* remainder,取余操作，%运算，栈浅%栈深
* */
public class Drem extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        double val1 = stack.popDouble();
        double val2 = stack.popDouble();
        stack.pushDouble(val2 % val1);
    }
}
