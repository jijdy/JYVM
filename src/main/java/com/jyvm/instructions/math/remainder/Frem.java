package com.jyvm.instructions.math.remainder;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;

/*
* 取余操作，%运算，栈浅%栈深
* */
public class Frem extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        float val1 = stack.popFloat();
        float val2 = stack.popFloat();
        stack.pushFloat(val2 % val1);
    }
}
