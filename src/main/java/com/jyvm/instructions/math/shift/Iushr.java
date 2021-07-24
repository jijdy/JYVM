package com.jyvm.instructions.math.shift;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;

/*
* 位移操作，移位运算，<< or >>
* */
public class Iushr extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int size = stack.popInt();
        int val = stack.popInt();
        size = size & 0x1f;
        stack.pushInt(val >> size);
    }
}
