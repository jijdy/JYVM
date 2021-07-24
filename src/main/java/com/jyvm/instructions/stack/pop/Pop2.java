package com.jyvm.instructions.stack.pop;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;

/*
* 将操作数栈顶的元素，long和double占有两个位置的需要会用pop2
* */
public class Pop2 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().popSlot();
        frame.getOperandStack().popSlot();
    }
}
