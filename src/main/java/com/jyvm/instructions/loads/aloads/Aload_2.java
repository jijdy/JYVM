package com.jyvm.instructions.loads.aloads;

import com.jyvm.instructions.base.BytecodeReader;
import com.jyvm.instructions.base.Instruction;
import com.jyvm.runtimeDate.Frame;

public class Aload_2 implements Instruction {

    @Override
    public void fetchOperands(BytecodeReader reader) {

    }

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushRef(frame.getLocalVars().getRef(2));
    }
}
