package com.jyvm.instructions.conversion.d2x;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;

/*
* double强制转换为long类型，从操作数栈开始
* */
public class D2l extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        double val = frame.getOperandStack().popDouble();
        long val1 = (long) val;
        frame.getOperandStack().pushLong(val1);
    }
}
