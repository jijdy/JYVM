package com.jyvm.instructions.stores.fstore;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;

public class Fstore_3 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setFloat(frame.getOperandStack().popFloat(), 3);
    }
}
