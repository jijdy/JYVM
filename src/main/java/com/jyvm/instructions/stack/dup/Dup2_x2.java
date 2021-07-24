package com.jyvm.instructions.stack.dup;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;
import com.jyvm.runtimeDate.Slot;

/*
 bottom -> top
[...][d][c][b][a]
       ____/ __/
      |   __/
      V  V
[...][b][a][d][c][b][a]
* */
public class Dup2_x2 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame){
        OperandStack stack = frame.getOperandStack();
        Slot slot1 = stack.popSlot();
        Slot slot2 = stack.popSlot();
        Slot slot3 = stack.popSlot();
        Slot slot4 = stack.popSlot();
        stack.pushSlot(slot2);
        stack.pushSlot(slot1);
        stack.pushSlot(slot4);
        stack.pushSlot(slot3);
        stack.pushSlot(slot2);
        stack.pushSlot(slot1);
    }
}
