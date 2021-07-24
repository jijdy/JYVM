package com.jyvm.instructions.stores.fstore;

import com.jyvm.instructions.base.Index8Instruction;
import com.jyvm.runtimeDate.Frame;


/*
* float类型数据的存储操作
* */
public class Fstore extends Index8Instruction {

    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setFloat(frame.getOperandStack().popFloat(), this.index);
    }
}
