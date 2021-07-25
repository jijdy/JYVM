package com.jyvm.instructions.extended;

import com.jyvm.instructions.base.BytecodeReader;
import com.jyvm.instructions.base.Instruction;
import com.jyvm.runtimeDate.Frame;

/*
* 和goto无差，主要在于使用的case的存储大小，若大于2字节，用于goto的
* case选项过大的扩展指令
* */
public class Goto_w implements Instruction {

    int offset;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.offset = reader.readInt();  //普通goto使用short存储
    }

    @Override
    public void execute(Frame frame) {
        Instruction.branch(frame, offset);
    }
}
