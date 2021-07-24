package com.jyvm.instructions.stores.istore;

import com.jyvm.instructions.base.Index8Instruction;
import com.jyvm.runtimeDate.Frame;

/*
* int类型的存储操作
* */
public class Istore extends Index8Instruction {

    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setInt(frame.getOperandStack().popInt(), this.index);
    }
}
