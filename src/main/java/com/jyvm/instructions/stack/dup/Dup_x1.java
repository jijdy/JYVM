package com.jyvm.instructions.stack.dup;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;
import com.jyvm.runtimeDate.Slot;

/*
bottom -> top
[...][c][b][a]
          __/
         |
         V
[...][c][a][b][a]
* */
public class Dup_x1 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();
        Slot slot1 = stack.popSlot();
        Slot slot2 = stack.popSlot();
        stack.pushSlot(slot1);
        stack.pushSlot(slot2);
        stack.pushSlot(slot1);
    }
}
