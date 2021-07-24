package com.jyvm.instructions.loads.iloads;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;

public class Iload_1 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt(frame.getLocalVars().getInt(1));
    }
}
