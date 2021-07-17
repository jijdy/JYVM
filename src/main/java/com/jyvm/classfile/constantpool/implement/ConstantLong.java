package com.jyvm.classfile.constantpool.implement;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.constantpool.ConstantInfo;

public class ConstantLong implements ConstantInfo {

    private long value;

    @Override
    public void readInfo(ClassReader reader) {
        value = reader.readNextU4Long();
    }

    @Override
    public int tag() {
        return ConstantInfo.CONSTANT_LONG;
    }

    public long value() {
        return this.value;
    }
}
