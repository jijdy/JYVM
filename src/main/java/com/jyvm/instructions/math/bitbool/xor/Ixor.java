package com.jyvm.instructions.math.bitbool.xor;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;

/*
* 异或运算，对操作数栈的栈顶两个元素进行，按位运算
* */
public class Ixor extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int val1 = stack.popInt();
        int val2 = stack.popInt();
        stack.pushInt(val1 ^ val2);
    }
}
