package com.jyvm.instructions.conversion.d2x;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;

/*
* double强制转换为float类型，从操作数栈开始
* */
public class D2f extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        double val = frame.getOperandStack().popDouble();
        float val1 = (float) val;
        frame.getOperandStack().pushFloat(val1);
    }
}
