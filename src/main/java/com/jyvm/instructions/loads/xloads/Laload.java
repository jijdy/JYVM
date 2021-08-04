package com.jyvm.instructions.loads.xloads;

import com.jyvm.instructions.base.NoOperandsInstruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;
import com.jyvm.runtimeDate.heap.method.Object;

/*
* 从数组中根据栈中索引和数组引用，从数组中获取到元素并推入到操作数栈顶
* */
public class Laload extends NoOperandsInstruction {

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        //数组索引
        int index = stack.popInt();
        //数组引用
        Object ref = stack.popRef();
        if (null == ref) {
            throw new RuntimeException();
        }
        long[] refs = ref.longs();
        if (index < 0 || refs.length <= index) {
            throw new ArrayIndexOutOfBoundsException();
        }
        stack.pushLong(refs[index]);
    }
}
