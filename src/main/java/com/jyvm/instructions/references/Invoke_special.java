package com.jyvm.instructions.references;

import com.jyvm.instructions.base.Index16Instruction;
import com.jyvm.runtimeDate.Frame;

/*
* 调用超类构造方法，实例初始化方法，构造方法
* */
public class Invoke_special extends Index16Instruction {

    public void execute(Frame frame) {
        frame.getOperandStack().popRef();
    }
}
