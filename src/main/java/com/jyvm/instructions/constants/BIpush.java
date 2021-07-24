package com.jyvm.instructions.constants;

import com.jyvm.instructions.base.BytecodeReader;
import com.jyvm.instructions.base.Instruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.utils.ByteUtil;

/*
* 从操作数中获取一个byte型数据并转换为int型写入到操作数栈
* */
public class BIpush implements Instruction {

    byte aByte;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.aByte = reader.readByte();
    }

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushInt(aByte);
    }
}
