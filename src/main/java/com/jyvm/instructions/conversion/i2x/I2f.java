package com.jyvm.instructions.conversion.i2x;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;

/*
  将int类型强制转换我其他类型
**/
public class I2f extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        int val = frame.getOperandStack().popInt();
        float val1 = (float) val;
        frame.getOperandStack().pushFloat(val1);
    }
}
