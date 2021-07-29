package com.jyvm.instructions.references;

import com.jyvm.instructions.base.Index16Instruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;
import com.jyvm.runtimeDate.heap.constantpool.ClassRef;
import com.jyvm.runtimeDate.heap.constantpool.FieldRef;
import com.jyvm.runtimeDate.heap.constantpool.RuntimePool;
import com.jyvm.runtimeDate.heap.method.Class;
import com.jyvm.runtimeDate.heap.method.Field;
import com.jyvm.runtimeDate.heap.method.Object;

/*
* 判断对象是否是某个类的实例，或继承了某个类或实现了某个接口
* 子类在为栈顶的引用对象，可能的父类有index在运行时常量池中找到
* */
public class Instance_of extends Index16Instruction {

    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Object ref = (Object) stack.popRef();
        if (ref == null) {
            stack.pushInt(0);
            return;
        }
        RuntimePool pool = frame.method().clazz.runtimePool;
        ClassRef classRef = (ClassRef) pool.getConstant(this.index);
        Class clazz = classRef.resolveClass();
        if (ref.isInstanceOf(clazz)) {
            stack.pushInt(1);
        } else {
            stack.pushInt(0);
        }

    }
}
