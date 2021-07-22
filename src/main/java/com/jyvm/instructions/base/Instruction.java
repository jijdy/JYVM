package com.jyvm.instructions.base;

import com.jyvm.runtimeDate.Frame;

public interface Instruction {

    void fetchOperands(BytecodeReader reader);

    void execute(Frame frame);


}
