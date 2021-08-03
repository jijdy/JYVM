package com.jyvm.instructions.base;

import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.Slot;
import com.jyvm.runtimeDate.Thread;
import com.jyvm.runtimeDate.heap.method.Method;

/**
 * 方法调用的基本逻辑，通用方法，
 * 创建一个新的栈帧，并将其推入到线程中的栈内，
 * 并通过该方法中的参数数据从操作数栈中拿出参数传入到方法中
 * 将操作数栈的数据传入到局部变量表中
 */
public class MethodInvokeLogic {

    public static void invokeMethod(Frame invokerFrame, Method method) {
        Thread thread = invokerFrame.thread();
        Frame newFrame = thread.frame(method);
        thread.pushStack(newFrame);

        int argSlotCount = method.argSlotCount;
        if (argSlotCount > 0) {
            for (int i = argSlotCount - 1; i >= 0; i--) {
                Slot slot = invokerFrame.getOperandStack().popSlot();
                newFrame.getLocalVars().setSlot(i, slot);
            }
        }

        //hack，本地方法调用留
        if (method.isNative()) {
            if ("registerNatives".equals(method.name())) {
                thread.popStack();
            } else {
                throw new RuntimeException("native method " + method.name());
            }
        }
    }

}
