package com.jyvm.instructions.math.remainder;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;

/*
* 取余操作，%运算，栈浅%栈深
* */
public class Lrem extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        long val1= stack.popLong();
        long val2= stack.popLong();
        stack.pushLong(val2 % val1);
    }
}
