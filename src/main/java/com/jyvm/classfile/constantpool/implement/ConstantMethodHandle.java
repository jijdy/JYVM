package com.jyvm.classfile.constantpool.implement;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.constantpool.ConstantInfo;

public class ConstantMethodHandle implements ConstantInfo {
    private int referIndex;
    private int nameIndex;

    @Override
    public void readInfo(ClassReader reader) {
        referIndex = reader.readNextU2Int();
        nameIndex = reader.readNextU2Int();
    }

    @Override
    public int tag() {
        return this.CONSTANT_METHODHANDLE;
    }
}
