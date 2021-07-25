package com.jyvm.instructions.math.iinc;

import com.jyvm.instructions.base.BytecodeReader;
import com.jyvm.instructions.base.Instruction;
import com.jyvm.runtimeDate.Frame;

/*
* 使局部变量表中索引中的int值增加一个常量(byte大小内)，
* byte存储索引和常量数据
* */
public class Iinc implements Instruction {

    public int index;
    int constVal;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.index = reader.readByte();
        this.constVal = reader.readByte();
    }

    @Override
    public void execute(Frame frame) {
        int val = frame.getLocalVars().getInt(this.index);
        val += this.constVal;
        frame.getLocalVars().setInt(val, this.index);
    }
}
