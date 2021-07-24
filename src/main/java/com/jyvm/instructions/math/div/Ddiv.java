package com.jyvm.instructions.math.div;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;

/*
* division除法操作，栈深/栈浅
* */
public class Ddiv extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        double val1 = stack.popDouble();
        double val2 = stack.popDouble();
        stack.pushDouble(val2/val1);
    }
}
