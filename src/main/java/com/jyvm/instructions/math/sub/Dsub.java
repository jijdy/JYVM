package com.jyvm.instructions.math.sub;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;

/*
* 将操作数栈顶的两个元素进行减法操作，栈深-栈浅，double类型
* */
public class Dsub extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        double val1 = stack.popDouble();
        double val2 = stack.popDouble();
        stack.pushDouble(val2 - val1);
    }
}
