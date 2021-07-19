package com.jyvm.classfile;

import com.jyvm.classfile.attribute.AttributeInfo;

/*
* 字段或方法表
* */
public class FieldOrMethodInfo {
    private final int accessFlag;
    private final int nameIndex;
    private final int descIndex;
    private AttributeInfo[] attributes;
    private ConstantPool constantPool;

    public FieldOrMethodInfo(ClassReader reader, ConstantPool constantPool) {
        this.constantPool = constantPool;
        this.accessFlag = reader.readNextU2Int();
        this.nameIndex = reader.readNextU2Int();
        this.descIndex = reader.readNextU2Int();
        readAttribute(reader);
    }

    public void readAttribute(ClassReader reader) {
        //read

    }

    public int getAccessFlag() {
        return accessFlag;
    }

    public String getName() {
        return constantPool.getUtf8(nameIndex);
    }

    public String getDesc() {
        return constantPool.getUtf8(descIndex);
    }

}
