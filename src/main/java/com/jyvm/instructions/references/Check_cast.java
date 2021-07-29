package com.jyvm.instructions.references;

import com.jyvm.instructions.base.Index16Instruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;
import com.jyvm.runtimeDate.heap.constantpool.ClassRef;
import com.jyvm.runtimeDate.heap.constantpool.RuntimePool;
import com.jyvm.runtimeDate.heap.method.Class;
import com.jyvm.runtimeDate.heap.method.Object;

/*
* 和instanceof类似，不过若判断失败，
* 会直接抛出ClassCastException异常
* */
public class Check_cast extends Index16Instruction {

    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        Object ref = (Object) stack.popRef();
        stack.pushRef(ref); //不改变操作数栈中的数据将引用对象取出来
        if (ref ==null) {
            return;
        }
        RuntimePool pool = frame.method().clazz.runtimePool;
        ClassRef classRef = (ClassRef) pool.getConstant(this.index);
        Class clazz = classRef.resolveClass();
        if (!ref.isInstanceOf(clazz)) {
            throw new ClassCastException();
        }
    }
}
