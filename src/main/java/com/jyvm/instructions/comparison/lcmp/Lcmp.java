package com.jyvm.instructions.comparison.lcmp;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;

/*
* long类型的大小比较，栈深>栈浅为1，否则为-1,0，
* 以int类型来表示其比较结果
* */
public class Lcmp extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        long val1 = stack.popLong();
        long val2 = stack.popLong();
        if (val2 > val1) {
            stack.pushInt(1);
        } else if (val2 < val1) {
            stack.pushInt(-1);
        } else {
            stack.pushInt(0); // =
        }
    }
}
