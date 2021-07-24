package com.jyvm.instructions.conversion.l2x;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;

/*
* 将long类型数据强制转换为其他类型
* */
public class L2i extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        long val = frame.getOperandStack().popLong();
        int val1 = (int) val;
        frame.getOperandStack().pushInt(val1);
    }
}
