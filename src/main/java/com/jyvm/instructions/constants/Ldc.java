package com.jyvm.instructions.constants;

import com.jyvm.instructions.base.Index8Instruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;
import com.jyvm.runtimeDate.heap.StringPool;
import com.jyvm.runtimeDate.heap.constantpool.ClassRef;
import com.jyvm.runtimeDate.heap.constantpool.RuntimePool;
import com.jyvm.runtimeDate.heap.method.Class;
import com.jyvm.runtimeDate.heap.method.Object;

/*
* 从运行时常量池中加载常量数据，并推入到操作数栈顶
* 有int,float,的操作，
* 拿到字符串的引用，先在字符串常量池中寻找，若无则新建并放入到常量池
* */
public class Ldc extends Index8Instruction {

    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Class clazz = frame.method().clazz;
        RuntimePool pool = frame.method().clazz.runtimePool;
        java.lang.Object ref = pool.getConstant(this.index);
        if (ref instanceof Integer) {
            stack.pushInt((Integer) ref);
        } else if (ref instanceof Float) {
            stack.pushFloat((Float) ref);
        }else if (ref instanceof String) {
            Object internedStr = StringPool.jString(clazz.loader, (String) ref);
            stack.pushRef(internedStr);
        }

        throw new RuntimeException("todo Ldc");
    }
}
