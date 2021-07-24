package com.jyvm.instructions.loads.iloads;

import com.jyvm.instructions.base.Index8Instruction;
import com.jyvm.runtimeDate.Frame;

/*
* int型数据加载
* */
public class Iload extends Index8Instruction {

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt(frame.getLocalVars().getInt(this.index));
    }
}
