package com.jyvm.instructions.conversion.f2x;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;

/*
* float强转为其他类型
* */
public class F2i extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        float val = frame.getOperandStack().popFloat();
        int val1 = (int) val;
        frame.getOperandStack().pushInt(val1);
    }
}
