package com.jyvm.instructions.loads.lloads;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;

public class Lload_3 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushLong(frame.getLocalVars().getLong(3));
    }
}
