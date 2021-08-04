package com.jyvm.instructions.control.returns;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.Thread;
import com.jyvm.runtimeDate.heap.method.Object;

/*
* 将计算结果返回到其调用栈帧，
* 从当前使用栈帧中调用出并推入到栈顶帧元素
* */
public class Freturn extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        Thread thread = frame.thread();
        Frame currentFrame = thread.popStack();
        Frame invokeFrame = thread.topFrame();
        float ref = currentFrame.getOperandStack().popFloat();
        invokeFrame.getOperandStack().pushFloat(ref);
    }
}
