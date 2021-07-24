package com.jyvm.instructions.math.mul;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;

/*
 乘法运算，对操作数栈的栈顶两个匀速进行并返回为一个元素
* */
public class Dmul extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        double val1 = stack.popDouble();
        double val2 = stack.popDouble();
        stack.pushDouble(val2 * val1);
    }
}
