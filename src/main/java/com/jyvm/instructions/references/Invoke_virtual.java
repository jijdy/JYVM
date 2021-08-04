package com.jyvm.instructions.references;

import com.jyvm.instructions.base.Index16Instruction;
import com.jyvm.instructions.base.MethodInvokeLogic;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;
import com.jyvm.runtimeDate.heap.constantpool.MethodRef;
import com.jyvm.runtimeDate.heap.constantpool.RuntimePool;
import com.jyvm.runtimeDate.heap.method.Class;
import com.jyvm.runtimeDate.heap.method.Method;
import com.jyvm.runtimeDate.heap.method.MethodLookup;
import com.jyvm.runtimeDate.heap.method.Object;

/*
* 调用实例方法
* 从对象的类中寻找调用的方法，若为抽象则抛错误
* */
public class Invoke_virtual extends Index16Instruction {

    public void execute(Frame frame) {
        Class currentClass = frame.method().clazz;
        RuntimePool runTimeConstantPool = currentClass.runtimePool;
        MethodRef methodRef = (MethodRef) runTimeConstantPool.getConstant(this.index);
        Method resolvedMethod = methodRef.getMethod();
        if (resolvedMethod.isStatic()) {
            throw new IncompatibleClassChangeError();
        }

        Object ref = frame.getOperandStack().getRefFromTop(resolvedMethod.argSlotCount - 1);
        if (null == ref) {
            if ("println".equals(methodRef.getName())) {
                _println(frame.getOperandStack(), methodRef.getDesc());
                return;
            }
            throw new NullPointerException();
        }

        if (resolvedMethod.isProtected() &&
                resolvedMethod.clazz.isSubClassOf(currentClass) &&
                !resolvedMethod.clazz.getPackageName().equals(currentClass.getPackageName()) &&
                ref.clazz() != currentClass &&
                !ref.clazz().isSubClassOf(currentClass)) {
            throw new IllegalAccessError();
        }

        Method methodToBeInvoked = MethodLookup.lookupMethodInClass(ref.clazz(), methodRef.getName(), methodRef.getDesc());
        if (null == methodToBeInvoked || methodToBeInvoked.isAbstract()) {
            throw new AbstractMethodError();
        }

        MethodInvokeLogic.invokeMethod(frame, methodToBeInvoked);
    }

    //hack
    private void _println(OperandStack stack, String descriptor) {
        switch (descriptor) {
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
                System.out.println(descriptor);
                break;
        }
        stack.popRef();

    }
}
