package com.jyvm.instructions.base;

import com.jyvm.runtimeDate.Frame;

/*
* 根据索引获得2字节的数据访问运行时常量池constantPool
* */
public class Index16Instruction implements Instruction{
    public int index;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.index = reader.readShort();
    }

    @Override
    public void execute(Frame frame) {

    }


}
