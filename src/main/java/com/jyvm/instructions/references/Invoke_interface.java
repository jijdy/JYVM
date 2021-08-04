package com.jyvm.instructions.references;

import com.jyvm.instructions.base.BytecodeReader;
import com.jyvm.instructions.base.Instruction;
import com.jyvm.instructions.base.MethodInvokeLogic;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.heap.constantpool.InterfaceMethodRef;
import com.jyvm.runtimeDate.heap.constantpool.RuntimePool;
import com.jyvm.runtimeDate.heap.method.Method;
import com.jyvm.runtimeDate.heap.method.MethodLookup;
import com.jyvm.runtimeDate.heap.method.Object;

/*
* 调用接口相关方法，和invoke_virtual类似，但主要用于加快效率
* */
public class Invoke_interface implements Instruction {

    private int idx;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.idx = reader.readShort();
        reader.readByte();
        reader.readByte();
    }

    @Override
    public void execute(Frame frame) {
        RuntimePool runTimeConstantPool = frame.method().clazz.runtimePool;
        InterfaceMethodRef methodRef = (InterfaceMethodRef) runTimeConstantPool.getConstant(this.idx);
        Method resolvedMethod = methodRef.resolvedInterfaceMethod();
        if (resolvedMethod.isStatic() || resolvedMethod.isPrivate()) {
            throw new IncompatibleClassChangeError();
        }
        Object ref = frame.getOperandStack().getRefFromTop(resolvedMethod.argSlotCount - 1);
        if (null == ref) {
            throw new NullPointerException();
        }
        if (!ref.clazz().isImplements(methodRef.resolveClass())) {
            throw new IncompatibleClassChangeError();
        }
        Method methodToBeInvoked = MethodLookup.lookupMethodInClass(ref.clazz(), methodRef.getName(), methodRef.getDesc());
        if (null == methodToBeInvoked || methodToBeInvoked.isAbstract()) {
            throw new AbstractMethodError();
        }
        if (!methodToBeInvoked.isPublic()) {
            throw new IllegalAccessError();
        }

        MethodInvokeLogic.invokeMethod(frame, methodToBeInvoked);

    }

}
