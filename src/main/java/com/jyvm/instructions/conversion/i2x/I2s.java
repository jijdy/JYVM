package com.jyvm.instructions.conversion.i2x;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;

/*
  将int类型强制转换我其他类型
**/
public class I2s extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        int val = frame.getOperandStack().popInt();
        int val1 = (short) val;
        frame.getOperandStack().pushInt(val1);
    }
}
