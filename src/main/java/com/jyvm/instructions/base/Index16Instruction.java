package com.jyvm.instructions.base;

import com.jyvm.runtimeDate.Frame;

public class Index16Instruction implements Instruction{
    int index;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.index = reader.readShort();
    }

    @Override
    public void execute(Frame frame) {

    }
}
