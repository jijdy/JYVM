package com.jyvm.instructions.loads.floads;

import com.jyvm.instructions.base.Index8Instruction;
import com.jyvm.runtimeDate.Frame;

/*
* float类型数据加载到操作数栈中
* */
public class Fload extends Index8Instruction {

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushFloat(frame.getLocalVars().getFloat(this.index));
    }
}
