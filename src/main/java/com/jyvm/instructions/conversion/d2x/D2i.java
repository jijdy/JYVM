package com.jyvm.instructions.conversion.d2x;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;

/*
* double强制转换为int类型，从操作数栈开始
* */
public class D2i extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        double val = frame.getOperandStack().popDouble();
        int val1 = (int) val;
        frame.getOperandStack().pushInt(val1);
    }
}
