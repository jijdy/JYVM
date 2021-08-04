package com.jyvm.instructions.control.returns;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.Thread;
import com.jyvm.runtimeDate.heap.method.Object;

/*
return，无参数的传递时，直接将栈的当前栈帧推出即可
* */
public class Return extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        frame.thread().popStack();
    }
}
