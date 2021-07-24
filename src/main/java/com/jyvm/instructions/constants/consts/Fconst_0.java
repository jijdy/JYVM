package com.jyvm.instructions.constants.consts;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;

/*
* 写入一个float值到操作数栈
* */
public class Fconst_0 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushFloat(0.0f);
    }
}
