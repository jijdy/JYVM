package com.jyvm.instructions.math.div;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;

/*
* division除法操作，栈深/栈浅
* */
public class Ldiv extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        long val1 = stack.popLong();
        long val2 = stack.popLong();
        stack.pushLong(val2/val1);
    }
}
