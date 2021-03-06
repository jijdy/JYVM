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
* 1. 首先找到类引用，若无则通过类加载其加载到相关类并返回用于创建数组
* 2. 通过操作数栈的数组长度，和具有数组描述的类名在class中进行数组的创建
* 3. 创建的数组使用lang.Object类来存储数据，并存储了该数组的完全限定类的引用
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
        //在类名className前加’[‘号，表示数组的前缀，为8种基本类型数据
        Class arrClass = clazz.arrayClass();
        Object ar = arrClass.newArray(count);
        stack.pushRef(ar);
    }
}
