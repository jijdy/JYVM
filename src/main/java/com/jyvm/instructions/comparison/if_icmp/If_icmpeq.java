package com.jyvm.instructions.comparison.if_icmp;

import com.jyvm.instructions.base.BranchInstruction;
import com.jyvm.instructions.base.Instruction;
import com.jyvm.runtimeDate.Frame;

/*
* 比较跳转指令，从栈顶取出两个int型数据进行比较，满足条件则跳转pc指针
* */
public class If_icmpeq extends BranchInstruction {

    @Override
    public void execute(Frame frame) {
        int val1 = frame.getOperandStack().popInt();
        int val2 = frame.getOperandStack().popInt();
        if (val1 == val2) {
            Instruction.branch(frame, this.offset());
        }
    }
}
