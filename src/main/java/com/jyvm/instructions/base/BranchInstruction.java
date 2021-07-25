package com.jyvm.instructions.base;

import com.jyvm.runtimeDate.Frame;

/*
* 表示跳转指令的类
* */
public class BranchInstruction implements Instruction{

    int offset; //跳转偏移量

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.offset = reader.readShort();
    }

    @Override
    public void execute(Frame frame) {

    }

    //跳转指令，根据帧得到线程当前pc值，再进行跳转
    public void branch(Frame frame, int offset) {
        int pc = frame.thread().getPC();
        int nextPC = pc + offset;
        frame.setNextPC(nextPC);
    }

    public int offset() {
        return this.offset;
    }
}
