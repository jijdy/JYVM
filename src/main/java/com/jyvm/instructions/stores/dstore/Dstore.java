package com.jyvm.instructions.stores.dstore;

import com.jyvm.instructions.base.Index8Instruction;
import com.jyvm.runtimeDate.Frame;

/*
* doule类型的存储
* */
public class Dstore extends Index8Instruction {

    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setDouble(frame.getOperandStack().popDouble(), this.index);
    }
}
