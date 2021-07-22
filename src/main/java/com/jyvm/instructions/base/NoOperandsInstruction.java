package com.jyvm.instructions.base;

import com.jyvm.runtimeDate.Frame;

public class NoOperandsInstruction implements Instruction{
    @Override
    public void fetchOperands(BytecodeReader reader) {
        //null
    }

    @Override
    public void execute(Frame frame) {
        //null
    }

}
