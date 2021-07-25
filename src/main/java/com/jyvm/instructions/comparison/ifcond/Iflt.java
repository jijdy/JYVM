package com.jyvm.instructions.comparison.ifcond;

import com.jyvm.instructions.base.BranchInstruction;
import com.jyvm.instructions.base.Instruction;
import com.jyvm.runtimeDate.Frame;

/*
* 若操作数栈顶元素满足条件，则将栈帧的pc值针进行跳转，
* 跳转量从code二进制数组数据中获得
* */
public class Iflt extends BranchInstruction {

    @Override
    public void execute(Frame frame) {
        int val = frame.getOperandStack().popInt();
        if (val < 0) {
            Instruction.branch(frame, this.offset());
        }
    }
}
