package com.jyvm.instructions.constants.consts;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;

/*
* 写入一个固定常量long类型数到操作数栈
* */
public class Lconst_0 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushLong(0L);
    }
}
