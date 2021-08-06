package com.jyvm.instructions.stores.xastore;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;
import com.jyvm.runtimeDate.heap.method.Object;

/*
* 将一个给定值赋值给到数组中的位置
* */
public class Castore extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack  = frame.getOperandStack();
        //赋值给数组的值
        int ref = stack.popInt();
        //数组位置与数组引用
        int index = stack.popInt();
        Object arrRef = stack.popRef();
        if (null == arrRef) {
            throw new RuntimeException();
        }
        char[] refs = arrRef.chars();
        if (index < 0 || refs.length <= index) {
            throw new ArrayIndexOutOfBoundsException();
        }
        refs[index] = (char) ref;
    }
}
