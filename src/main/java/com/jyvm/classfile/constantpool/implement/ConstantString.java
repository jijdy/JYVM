package com.jyvm.classfile.constantpool.implement;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.constantpool.ConstantInfo;
import com.jyvm.classfile.ConstantPool;

/*
* 存储一个索引指向一个utf8字符串表示String类型的数据
* */
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

    @Override
    public String toString() {
        return constantPool.getUtf8(sIndex);
    }
}
