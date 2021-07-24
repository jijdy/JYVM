package com.jyvm.instructions.stores.lstore;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;

public class Lstore_3 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setLong(frame.getOperandStack().popLong(), 3);
    }
}
