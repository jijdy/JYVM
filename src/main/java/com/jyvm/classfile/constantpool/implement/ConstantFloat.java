package com.jyvm.classfile.constantpool.implement;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.constantpool.ConstantInfo;

/*
* 存储float浮点类型数据
* */
public class ConstantFloat implements ConstantInfo {
    private float value;

    @Override
    public void readInfo(ClassReader reader) {
        value = reader.raedNextU4Float();
    }

    @Override
    public int tag() {
        return ConstantInfo.CONSTANT_FLOAT;
    }

    public float value() {
        return this.value;
    }
}
