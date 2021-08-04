package com.jyvm.instructions.references;

import com.jyvm.instructions.base.Index16Instruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;
import com.jyvm.runtimeDate.heap.constantpool.ClassRef;
import com.jyvm.runtimeDate.heap.constantpool.RuntimePool;
import com.jyvm.runtimeDate.heap.method.Class;
import com.jyvm.runtimeDate.heap.method.Object;

/*
* 用于根据引用的类型创建引用类型的数组,
* 从类中获取到相关类的信息，并创建数组再推入到操作数栈中
* */
public class Anew_array extends Index16Instruction {

    @Override
    public void execute(Frame frame) {
        RuntimePool runtimePool = frame.method().clazz.runtimePool;
        ClassRef classRef = (ClassRef) runtimePool.getConstant(this.index);
        Class clazz = classRef.resolveClass();

        OperandStack stack = frame.getOperandStack();
        int count = stack.popInt();
        if (count < 0) {
            throw new NegativeArraySizeException();
        }
        Class arrClass = clazz.arrayClass();
        Object ar = arrClass.newArray(count);
        stack.pushRef(ar);
    }
}
