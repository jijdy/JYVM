package com.jyvm.instructions.constants.consts;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;

/*
* 写入一个double值到操作数栈
* */
public class Dconst_0 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushDouble(0.0d);
    }
}
