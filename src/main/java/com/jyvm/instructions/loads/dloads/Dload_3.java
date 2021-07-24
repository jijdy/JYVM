package com.jyvm.instructions.loads.dloads;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;

/*
* 从指定位置拿到一个double值并存放在操作数栈中
* */
public class Dload_3 extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        frame.getOperandStack().pushDouble(frame.getLocalVars().getDouble(3));
    }
}
