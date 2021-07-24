package com.jyvm.instructions.stores.dstore;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;

public class Dstore_3 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setDouble(frame.getOperandStack().popDouble(), 3);
    }
}
