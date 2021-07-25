package com.jyvm.instructions.comparison.dcmp;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;

/*
* 对栈顶的两个double元素进行比较，有可能出现NAN，即无法比较的情况
* g返回大于，l返回小于
* */
public class Dcmpl extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        double val1 = stack.popDouble();
        double val2 = stack.popDouble();
        if (val2 > val1) {
            stack.pushInt(1);
            return;
        }
        if (val2 < val1) {
            stack.pushInt(-1);
            return;
        }
        if (val2 == val1) {
            stack.pushInt(0);
            return;
        }
        stack.pushInt(-1); //无法比较，返回-1，即小于
    }
}
