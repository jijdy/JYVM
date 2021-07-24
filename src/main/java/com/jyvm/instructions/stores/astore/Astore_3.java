package com.jyvm.instructions.stores.astore;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;

public class Astore_3 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame){
        frame.getLocalVars().setRef(frame.getOperandStack().popRef(), 3);
    }
}
