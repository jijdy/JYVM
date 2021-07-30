package com.jyvm.classfile.constantpool.implement;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.constantpool.ConstantInfo;

/*
* 存储整形数据
* */
public class ConstantInteger implements ConstantInfo {
    public int tag;

    public int value;

    @Override
    public void readInfo(ClassReader reader) {
        value = (int) reader.readNextU4Long();
    }

    @Override
    public int tag() {
        return ConstantInfo.CONSTANT_INTEGER;
    }

    public int value() {
        return this.value;
    }
}
