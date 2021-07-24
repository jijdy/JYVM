package com.jyvm.instructions.loads.aloads;

import com.jyvm.instructions.base.Index8Instruction;
import com.jyvm.runtimeDate.Frame;

/*
* 根据索引从局部变量表获取数据，并放入到操作数栈中
* */
public class Aload extends Index8Instruction {

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushRef(frame.getLocalVars().getRef(this.index));
    }
}
