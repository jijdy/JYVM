package com.jyvm.instructions.math.mul;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;

/*
 乘法运算，对操作数栈的栈顶两个匀速进行并返回为一个元素
* */
public class Fmul extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        float val1 = stack.popFloat();
        float val2 = stack.popFloat();
        stack.pushFloat(val2 * val1);
    }
}
