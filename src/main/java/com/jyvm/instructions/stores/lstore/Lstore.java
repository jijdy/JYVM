package com.jyvm.instructions.stores.lstore;

import com.jyvm.instructions.base.Index8Instruction;
import com.jyvm.runtimeDate.Frame;

/*
* long类型的存储操作
* */
public class Lstore extends Index8Instruction {

    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setLong(frame.getOperandStack().popLong(), this.index);
    }
}
