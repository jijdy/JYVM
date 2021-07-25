package com.jyvm.instructions.comparison.if_acmp;

import com.jyvm.instructions.base.BranchInstruction;
import com.jyvm.instructions.base.Instruction;
import com.jyvm.runtimeDate.Frame;

/*
* 从栈顶拿出两个引用类型变量进行比较，满足条件则跳转
* */
public class If_acmpne extends BranchInstruction {

    @Override
    public void execute(Frame frame) {
        Object val1 = frame.getOperandStack().popRef();
        Object val2 = frame.getOperandStack().popRef();
        if (val1 != val2) {
            Instruction.branch(frame, this.offset());
        }
    }
}
