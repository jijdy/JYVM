package com.jyvm.classfile.constantpool.implement;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.constantpool.ConstantInfo;
import com.jyvm.classfile.constantpool.ConstantPool;

public class ConstantString implements ConstantInfo {

    private ConstantPool constantPool;
    private int sIndex;

    public ConstantString(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        sIndex = reader.readNextU2Int();
    }

    @Override
    public int tag() {
        return ConstantInfo.CONSTANT_STRING;
    }

    public String value() {
        return constantPool.getUtf8(sIndex);
    }
}
