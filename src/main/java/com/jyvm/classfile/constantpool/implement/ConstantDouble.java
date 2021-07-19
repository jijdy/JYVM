package com.jyvm.classfile.constantpool.implement;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.constantpool.ConstantInfo;

/*
* 存储double型数据
* */
public class ConstantDouble implements ConstantInfo {

    private double value;

    @Override
    public void readInfo(ClassReader reader) {
        value = reader.readNextU4Double();
    }

    @Override
    public int tag() {
        return ConstantInfo.CONSTANT_DOUBLE;
    }

    public double value() {
        return this.value;
    }
}
