package com.jyvm.instructions.references;

import com.jyvm.instructions.base.Index16Instruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;
import com.jyvm.runtimeDate.heap.constantpool.MethodRef;
import com.jyvm.runtimeDate.heap.constantpool.RuntimePool;
import com.jyvm.runtimeDate.heap.method.Method;

/*
* 调用实例方法
* */
public class Invoke_virtual extends Index16Instruction {

    public void execute(Frame frame) {
        RuntimePool pool = frame.method().clazz.runtimePool;
        MethodRef methodRef = (MethodRef) pool.getConstant(this.index);
        if (methodRef.getName().equals("println")) {
            OperandStack stack = frame.getOperandStack();
            switch (methodRef.getDesc()) {
                case "(Z)V":
                    System.out.println(stack.popInt() != 0);
                    break;
                case "(C)V":
                    System.out.println(stack.popInt());
                    break;
                case "(I)V":
                case "(B)V":
                case "(S)V":
                    System.out.println(stack.popInt());
                    break;
                case "(F)V":
                    System.out.println(stack.popFloat());
                    break;
                case "(J)V":
                    System.out.println(stack.popLong());
                    break;
                case "(D)V":
                    System.out.println(stack.popDouble());
                    break;
                default:
                    System.out.println(methodRef.getDesc());
                    break;
            }
            stack.popRef();
        }
    }

}
