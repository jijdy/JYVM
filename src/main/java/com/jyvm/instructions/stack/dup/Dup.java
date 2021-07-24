package com.jyvm.instructions.stack.dup;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.Slot;

/*
* 复制操作数栈的栈顶元素在栈顶，将栈顶元素推出，再放入两次
bottom -> top
[...][c][b][a]
             \_
               |
               V
[...][c][b][a][a]
* */
public class Dup extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        Slot slot = frame.getOperandStack().popSlot();
        frame.getOperandStack().pushSlot(slot);
        frame.getOperandStack().pushSlot(slot);
    }
}
