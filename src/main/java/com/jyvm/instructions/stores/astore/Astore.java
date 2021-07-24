package com.jyvm.instructions.stores.astore;

import com.jyvm.instructions.base.Index8Instruction;
import com.jyvm.runtimeDate.Frame;

/*
* 引用类型的存储，从操作数栈中取出数据放入到局部变量表中
* */
public class Astore extends Index8Instruction {

    @Override
    public void execute(Frame frame) {
        frame.getLocalVars().setRef(frame.getOperandStack().popRef(), this.index);
    }
}
