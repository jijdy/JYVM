package com.jyvm.instructions.math.div;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;

/*
* division除法操作，栈深/栈浅
* */
public class Idiv extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int val1 = stack.popInt();
        int val2 = stack.popInt();
        stack.pushInt(val2/val1);
    }
}
