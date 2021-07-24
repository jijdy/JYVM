package com.jyvm.instructions.stack.pop;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;

/*
* 将操作数栈顶的元素弹出，只能弹出占用一个位的元素，long和double需要会用pop2
* */
public class Pop extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().popSlot();
    }
}
