package com.jyvm.instructions.constants;

import com.jyvm.instructions.base.BytecodeReader;
import com.jyvm.instructions.base.Instruction;
import com.jyvm.runtimeDate.Frame;

/*
* 从操作数中获得一个short并转换为int写入到操作数栈中
* */
public class SIpush implements Instruction {

    short aShort;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.aShort = reader.readShort();
    }

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt(this.aShort);
    }
}
