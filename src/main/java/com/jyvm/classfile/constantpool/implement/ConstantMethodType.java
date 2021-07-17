package com.jyvm.classfile.constantpool.implement;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.constantpool.ConstantInfo;

public class ConstantMethodType implements ConstantInfo {
    private int descIndex;

    @Override
    public void readInfo(ClassReader reader) {
        descIndex = reader.readNextU2Int();
    }

    @Override
    public int tag() {
        return this.CONSTANT_METHODTYPE;
    }
}
