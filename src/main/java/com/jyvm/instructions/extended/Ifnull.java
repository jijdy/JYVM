package com.jyvm.instructions.extended;

import com.jyvm.instructions.base.BranchInstruction;
import com.jyvm.instructions.base.Instruction;
import com.jyvm.runtimeDate.Frame;

/*
* 根据栈顶的引用类型元素是否为null来判断是否进行指令跳转
* */
public class Ifnull extends BranchInstruction {

    @Override
    public void execute(Frame frame) {
        Object val = frame.getOperandStack().popRef();
        if (null == val) {
            Instruction.branch(frame, this.offset());
        }
    }
}
