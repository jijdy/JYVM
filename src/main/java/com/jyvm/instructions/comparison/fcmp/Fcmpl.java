package com.jyvm.instructions.comparison.fcmp;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;

/*
* 对栈顶的两个float元素进行比较，有可能出现NAN，即无法比较的情况
* */
public class Fcmpl extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        float val1 = stack.popFloat();
        float val2 = stack.popFloat();
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
        stack.pushInt(-1); //无法比较，返回1，即大于
    }
}
