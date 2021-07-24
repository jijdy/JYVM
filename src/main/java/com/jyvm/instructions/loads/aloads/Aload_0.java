package com.jyvm.instructions.loads.aloads;

import com.jyvm.instructions.base.BytecodeReader;
import com.jyvm.instructions.base.Instruction;
import com.jyvm.runtimeDate.Frame;

/*
* 从指定的(index = 0)为置处拿到引用元素并存入到操作数栈中
* */
public class Aload_0 implements Instruction {

    @Override
    public void fetchOperands(BytecodeReader reader) {

    }

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushRef(frame.getLocalVars().getRef(0));
    }
}
