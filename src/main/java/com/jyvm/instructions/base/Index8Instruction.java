package com.jyvm.instructions.base;

import com.jyvm.runtimeDate.Frame;

/*
* 根据索引获得局部变量表中数据，索引由单个字节存储并获得
* */
public class Index8Instruction implements Instruction{
    public int index;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.index = reader.readByte();
    }

    @Override
    public void execute(Frame frame) {

    }
}
