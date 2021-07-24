package com.jyvm.instructions.stores.istore;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;

public class Istore_2 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setInt(frame.getOperandStack().popInt(), 2);
    }
}
