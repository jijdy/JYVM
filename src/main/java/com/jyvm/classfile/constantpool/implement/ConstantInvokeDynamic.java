package com.jyvm.classfile.constantpool.implement;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.constantpool.ConstantInfo;

public class ConstantInvokeDynamic implements ConstantInfo {
    private int bootIndex;
    private int nameIndex;

    @Override
    public void readInfo(ClassReader reader) {
        bootIndex = reader.readNextU2Int();
        nameIndex = reader.readNextU2Int();
    }

    @Override
    public int tag() {
        return this.CONSTANT_INVOKEDYNAMIC;
    }
}

