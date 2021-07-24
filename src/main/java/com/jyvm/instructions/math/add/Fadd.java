package com.jyvm.instructions.math.add;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;


//float类型
public class Fadd extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        float val1 = stack.popFloat();
        float val2 = stack.popFloat();
        stack.pushDouble(val1 + val2);
    }
}
