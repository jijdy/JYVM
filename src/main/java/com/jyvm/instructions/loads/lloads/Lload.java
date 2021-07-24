package com.jyvm.instructions.loads.lloads;

import com.jyvm.instructions.base.Index8Instruction;
import com.jyvm.runtimeDate.Frame;

/*
* long类型的加载
* */
public class Lload extends Index8Instruction {

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushLong(frame.getLocalVars().getLong(this.index));
    }
}
