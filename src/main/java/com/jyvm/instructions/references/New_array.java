package com.jyvm.instructions.references;

import com.jyvm.instructions.base.BytecodeReader;
import com.jyvm.instructions.base.Instruction;
import com.jyvm.runtimeDate.Frame;
import com.jyvm.runtimeDate.OperandStack;
import com.jyvm.runtimeDate.heap.ClassLoader;
import com.jyvm.runtimeDate.heap.method.Class;
import com.jyvm.runtimeDate.heap.method.Object;

/*
* 用于创建8种基本数据类型的数组指令
* 根据操作数栈中的数组大小，和字节码数据中的字节类型来创建数组
* */
public class New_array implements Instruction {

    byte type;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.type = reader.readByte();
    }

    @Override
    public void execute(Frame frame) {
        OperandStack stack = frame.getOperandStack();
        int count = stack.popInt();
        if (count < 0) {
            throw new NegativeArraySizeException();
        }

        ClassLoader classLoader = frame.method().clazz.loader;
        Class arrClass = getPrimitiveArrayClass(classLoader, this.type);
        Object arr = arrClass.newArray(count);
        stack.pushRef(arr);
    }

    private Class getPrimitiveArrayClass(ClassLoader loader, byte type) {
        switch (type) {
            case ArrayType.AT_BOOLEAN:
                return loader.loadClass("[Z");
            case ArrayType.AT_BYTE:
                return loader.loadClass("[B");
            case ArrayType.AT_CHAR:
                return loader.loadClass("[C");
            case ArrayType.AT_SHORT:
                return loader.loadClass("[S");
            case ArrayType.AT_INT:
                return loader.loadClass("[I");
            case ArrayType.AT_LONG:
                return loader.loadClass("[J");
            case ArrayType.AT_FLOAT:
                return loader.loadClass("[F");
            case ArrayType.AT_DOUBLE:
                return loader.loadClass("[D");
            default:
                throw new RuntimeException("Invalid atype!");
        }
    }

    static class ArrayType{
        static final byte AT_BOOLEAN = 4;
        static final byte AT_CHAR = 5;
        static final byte AT_FLOAT = 6;
        static final byte AT_DOUBLE = 7;
        static final byte AT_BYTE = 8;
        static final byte AT_SHORT = 9;
        static final byte AT_INT = 10;
        static final byte AT_LONG = 11;
    }
}
