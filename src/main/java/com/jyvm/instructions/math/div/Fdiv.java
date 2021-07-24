package com.jyvm.instructions.math.div;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;

/*
* division除法操作，栈深/栈浅
* */
public class Fdiv extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        float val1 = stack.popFloat();
        float val2 = stack.popFloat();
        stack.pushFloat(val2/val1);
    }
}
