package com.jyvm.instructions.references;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.natice.java._Throwable;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;
import com.jyvm.runtimeDate.Thread;
import com.jyvm.runtimeDate.heap.StringPool;
import com.jyvm.runtimeDate.heap.method.Object;

/*
* athrow指令，将异常在jvm即本地方法级别new出来并在异常出现时将堆栈进行释放
* 并打印堆栈的基本信息，再将new的异常进行抛出
* */
public class Athrow extends NoOperandsInstruction {
    @Override
    public void execute(Frame frame) {
        Object ex = frame.getOperandStack().popRef();
        if (ex == null) {
            throw new NullPointerException();
        }

        Thread thread = frame.thread();
        if (!findAndGotoExceptionHandler(thread, ex)) {
            handleUncaughtException(thread, ex);
        }
    }

    private boolean findAndGotoExceptionHandler(Thread thread, Object ex) {
        do {
            Frame frame = thread.topFrame();
            int pc = frame.nextPC() - 1;

            int handlerPc = frame.method().findExceptionHandler(ex.clazz(), pc);
            if (handlerPc > 0) {
                OperandStack stack = frame.getOperandStack();
                stack.clear();
                stack.pushRef(ex);
                frame.setNextPC(handlerPc);
                return true;
            }

            thread.popStack();
        } while (!thread.isStackEmpty());
        return false;
    }

    private void handleUncaughtException(Thread thread, Object ex) {
        thread.clearStack();

        Object jMsg = ex.getRefVar("detailMessage", "Ljava/lang/String;");

        String msg = StringPool.goString(jMsg);

        System.out.println(ex.clazz().javaName() + "：" + msg);

        java.lang.Object extra = ex.extra();

        _Throwable[] throwables = (_Throwable[]) extra;
        for (_Throwable t : throwables) {
            System.out.println(t.string());
        }

    }
}
