package com.jyvm.classfile.attribute.Implements;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.ConstantPool;
import com.jyvm.classfile.attribute.AttributeInfo;

/*
* 用于记录操作数栈的最大深度，局部变量表，字节码，以及异常处理和属性表
* 主要运用在运行时数据区的处理中
* */
public class CodeAttribute implements AttributeInfo {

    private ConstantPool constantPool;
    private int maxStack;
    private int maxLocals;
    private byte[] bytes;
    private ExceptionTableEntry[] exceptionTables;
    //属性数组
    private AttributeInfo[] attributeInfos;

    @Override
    public void readInfo(ClassReader reader) {
        maxStack = reader.readNextU2Int();
        maxLocals = reader.readNextU2Int();
        int codeLength = (int) reader.readNextU4Long();
        bytes = reader.read(codeLength);
        exceptionTables = ExceptionTableEntry.readExceptionTable(reader);
        attributeInfos = AttributeInfo.readAttributes(reader, constantPool);
    }

    public CodeAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public AttributeInfo[] getAttributeInfos() {
        return attributeInfos;
    }

    //查找行数据信息
    public LineNumberTableAttribute lineNumberTableAttribute() {
        for (AttributeInfo attrInfo : this.attributeInfos) {
            if (attrInfo instanceof LineNumberTableAttribute) {
                return (LineNumberTableAttribute) attrInfo;
            }
        }
        return null;
    }

    public ExceptionTableEntry[] exceptionTable() {
        return this.exceptionTables;
    }

    static class ExceptionTable {
        private final int startPC;
        private final int endPC;
        private final int handlerPC;
        private final int catchType;


        ExceptionTable(int s, int e, int h, int c) {
            this.startPC = s;
            this.endPC = e;
            this.handlerPC = h;
            this.catchType = c;
        }

        static ExceptionTable[] readException(ClassReader reader) {
            int length = reader.readNextU2Int();
            ExceptionTable[] exceptionTables = new ExceptionTable[length];

            for (int i = 0; i < length ; i ++) {
                exceptionTables[i] = new ExceptionTable(reader.readNextU2Int(),reader.readNextU2Int(), reader.readNextU2Int(),reader.readNextU2Int());
            }
            return exceptionTables;
        }

        public int getStartPC() {
            return startPC;
        }

        public int getEndPC() {
            return endPC;
        }

        public int getHandlerPC() {
            return handlerPC;
        }

        public int getCatchType() {
            return catchType;
        }

    }

    public int getMaxStack() {
        return maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public byte[] getBytes() {
        return bytes;
    }


    public static class ExceptionTableEntry {

        private final int startPC;
        private final int endPC;
        private final int handlerPC;
        private final int catchType;

        ExceptionTableEntry(int startPC, int endPC, int handlerPC, int catchType) {
            this.startPC = startPC;
            this.endPC = endPC;
            this.handlerPC = handlerPC;
            this.catchType = catchType;
        }

        static ExceptionTableEntry[] readExceptionTable(ClassReader reader) {
            int exceptionTableLength = reader.readNextU2Int();
            ExceptionTableEntry[] exceptionTable = new ExceptionTableEntry[exceptionTableLength];
            for (int i = 0; i < exceptionTableLength; i++) {
                exceptionTable[i] = new ExceptionTableEntry(reader.readNextU2Int(), reader.readNextU2Int(), reader.readNextU2Int(), reader.readNextU2Int());
            }
            return exceptionTable;
        }

        public int startPC() {
            return this.startPC;
        }

        public int endPC() {
            return this.endPC;
        }

        public int handlerPC() {
            return this.handlerPC;
        }

        public int catchType() {
            return this.catchType;
        }

    }
}
