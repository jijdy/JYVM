package com.jyvm.instructions.control;

import com.jyvm.instructions.base.BranchInstruction;
import com.jyvm.instructions.base.Instruction;
import com.jyvm.runtimeDate.Frame;

/*
* 无条件的实现跳转操作
* */
public class Goto extends BranchInstruction {

    @Override
    public void execute(Frame frame) {
        Instruction.branch(frame, this.offset());
    }
}
