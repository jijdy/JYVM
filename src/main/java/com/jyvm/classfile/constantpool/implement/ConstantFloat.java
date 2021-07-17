package com.jyvm.classfile.constantpool.implement;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.constantpool.ConstantInfo;

public class ConstantFloat implements ConstantInfo {
    private float value;

    @Override
    public void readInfo(ClassReader reader) {
        value = reader.raedNextU2Float();
    }

    @Override
    public int tag() {
        return ConstantInfo.CONSTANT_FLOAT;
    }

    public float value() {
        return this.value;
    }
}
