package com.jyvm.instructions.stack;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.Slot;

/*
* 用于交换操作数栈顶的两个元素
* */
public class Swap extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        Slot slot1 = frame.getOperandStack().popSlot();
        Slot slot2 = frame.getOperandStack().popSlot();
        frame.getOperandStack().pushSlot(slot1);
        frame.getOperandStack().pushSlot(slot2);
    }
}
