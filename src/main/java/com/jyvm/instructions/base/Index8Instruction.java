package com.jyvm.instructions.base;

import com.jyvm.runtimeDate.Frame;

public class Index8Instruction implements Instruction{
    int index;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.index = reader.readByte();
    }

    @Override
    public void execute(Frame frame) {

    }
}
