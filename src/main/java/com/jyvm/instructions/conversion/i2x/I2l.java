package com.jyvm.instructions.conversion.i2x;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;

/*
  将int类型强制转换我其他类型
**/
public class I2l extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        int val = frame.getOperandStack().popInt();
        long val1 = (long) val;
        frame.getOperandStack().pushLong(val1);
    }
}
