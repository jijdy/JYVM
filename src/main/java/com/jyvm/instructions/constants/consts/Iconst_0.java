package com.jyvm.instructions.constants.consts;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;

/*
* 写入一个固定的int值到操作数栈
* */
public class Iconst_0 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt(0);
    }
}
