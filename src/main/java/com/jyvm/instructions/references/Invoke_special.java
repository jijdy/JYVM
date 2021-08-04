package com.jyvm.instructions.references;

import com.jyvm.instructions.base.Index16Instruction;
import com.jyvm.instructions.base.MethodInvokeLogic;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.heap.constantpool.MethodRef;
import com.jyvm.runtimeDate.heap.constantpool.RuntimePool;
import com.jyvm.runtimeDate.heap.method.Class;
import com.jyvm.runtimeDate.heap.method.Method;
import com.jyvm.runtimeDate.heap.method.MethodLookup;
import com.jyvm.runtimeDate.heap.method.Object;

/*
* 调用超类构造方法，实例初始化方法，构造方法
* 即调用private方法和init方法，在调用前需要先检查调用者是否有权限进行方法的调用
* */
public class Invoke_special extends Index16Instruction {

    public void execute(Frame frame) {
        Class currentClass = frame.method().clazz;
        RuntimePool runTimeConstantPool = currentClass.runtimePool;
        MethodRef methodRef = (MethodRef) runTimeConstantPool.getConstant(this.index);
        Class resolvedClass = methodRef.resolveClass();
        Method resolvedMethod = methodRef.getMethod();
        if ("<init>".equals(resolvedMethod.name()) && resolvedMethod.clazz != resolvedClass) {
            throw new NoSuchMethodError();
        }
        if (resolvedMethod.isStatic()) {
            throw new IncompatibleClassChangeError();
        }

        Object ref = frame.getOperandStack().getRefFromTop(resolvedMethod.argSlotCount - 1);
        if (null == ref) {
            throw new NullPointerException();
        }

        if (resolvedMethod.isProtected() &&
                resolvedMethod.clazz.isSubClassOf(currentClass) &&
                !resolvedMethod.clazz.getPackageName().equals(currentClass.getPackageName()) &&
                ref.clazz() != currentClass &&
                !ref.clazz().isSubClassOf(currentClass)) {
            throw new IllegalAccessError();
        }

        if (currentClass.isSuper() &&
                resolvedClass.isSubClassOf(currentClass) &&
                !resolvedMethod.name().equals("<init>")) {
            MethodLookup.lookupMethodInClass(currentClass.superClass, methodRef.getName(), methodRef.getDesc());
        }

        if (resolvedMethod.isAbstract()) {
            throw new AbstractMethodError();
        }

        MethodInvokeLogic.invokeMethod(frame, resolvedMethod);
    }
}
