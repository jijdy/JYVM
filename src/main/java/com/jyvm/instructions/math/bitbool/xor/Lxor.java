package com.jyvm.instructions.math.bitbool.xor;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;

/*
* 异或运算，对操作数栈的栈顶两个元素进行，按位运算
* */
public class Lxor extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        long val1 = stack.popLong();
        long val2 = stack.popLong();
        stack.pushLong(val1 ^ val2);
    }
}
