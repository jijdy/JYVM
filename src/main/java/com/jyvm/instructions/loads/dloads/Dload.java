package com.jyvm.instructions.loads.dloads;

import com.jyvm.instructions.base.Index8Instruction;
import com.jyvm.runtimeDate.Frame;

/*
* 从局部变量表中加载一个double型数据到操作数栈中
* */
public class Dload extends Index8Instruction {

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushDouble(frame.getLocalVars().getDouble(this.index));
    }
}
