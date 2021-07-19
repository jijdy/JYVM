package com.jyvm.classfile.constantpool.implement;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.constantpool.ConstantInfo;
import com.jyvm.classfile.ConstantPool;

/*
* 存储类或接口的查找索引，按照索引存储到utf8字符串中
* */
public class ConstantClass implements ConstantInfo {

    private int value;
    private ConstantPool constantPool;

    public ConstantClass(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        value = reader.readNextU2Int();
    }

    @Override
    public int tag() {
        return ConstantInfo.CONSTANT_CLASS;
    }

    public String value() {
        return constantPool.getUtf8(value);
    }
}
