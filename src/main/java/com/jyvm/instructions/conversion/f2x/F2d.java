package com.jyvm.instructions.conversion.f2x;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;

/*
* float强转为其他类型
* */
public class F2d extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        float val = frame.getOperandStack().popFloat();
        double val1 = (double) val;
        frame.getOperandStack().pushDouble(val1);
    }
}
