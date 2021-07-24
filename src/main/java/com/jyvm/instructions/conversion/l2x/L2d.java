package com.jyvm.instructions.conversion.l2x;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;

/*
* 将long类型数据强制转换为其他类型
* */
public class L2d extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        long val = frame.getOperandStack().popLong();
        double val1 = (double) val;
        frame.getOperandStack().pushDouble(val1);
    }
}
