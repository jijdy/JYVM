package com.jyvm.instructions.math.shift;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;

/*
* 位移操作，移位运算，<< or >>
* */
public class Lshr extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int size = stack.popInt();
        long val = stack.popLong();
        size = size & 0x3f; //long长度64位，最多移位
        stack.pushLong(val >> size);
    }
}
