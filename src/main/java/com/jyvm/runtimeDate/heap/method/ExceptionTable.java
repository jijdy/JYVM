package com.jyvm.runtimeDate.heap.method;

import com.jyvm.classfile.attribute.Implements.CodeAttribute;
import com.jyvm.runtimeDate.heap.constantpool.ClassRef;
import com.jyvm.runtimeDate.heap.constantpool.RuntimePool;

/*
* 异常处理表的实现和查找
* */
public class ExceptionTable {

    private final ExceptionHandler[] exceptionTable;

    public ExceptionTable(CodeAttribute.ExceptionTableEntry[] entries, RuntimePool runTimeConstantPool) {
        this.exceptionTable = new ExceptionHandler[entries.length];
        int i = 0;
        for (CodeAttribute.ExceptionTableEntry entry : entries) {
            ExceptionHandler handler = new ExceptionHandler(
                    entry.startPC(),
                    entry.endPC(),
                    entry.handlerPC(),
                    getCatchType(entry.catchType(), runTimeConstantPool)
            );
            this.exceptionTable[i++] = handler;
        }
    }

    public ClassRef getCatchType(int idx, RuntimePool runTimeConstantPool) {
        if (idx == 0) return null;
        return (ClassRef) runTimeConstantPool.getConstant(idx);
    }

    public ExceptionHandler findExceptionHandler(Class exClass, int pc) {
        for (ExceptionHandler handler : exceptionTable) {
            if (pc >= handler.startPC && pc < handler.endPC) {
                if (null == handler.catchType) {
                    return handler;
                }
                Class catchClass = handler.catchType.resolveClass();
                if (catchClass == exClass || catchClass.isSubClassOf(exClass)) {
                    return handler;
                }
            }
        }
        return null;
    }


    static class ExceptionHandler {

        int startPC;
        int endPC;
        int handlerPC;
        ClassRef catchType;

        ExceptionHandler(int startPC, int endPC, int handlerPC, ClassRef catchType) {
            this.startPC = startPC;
            this.endPC = endPC;
            this.handlerPC = handlerPC;
            this.catchType = catchType;
        }
    }
}
