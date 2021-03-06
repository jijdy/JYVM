package com.jyvm.classfile.constantpool.implement;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.constantpool.ConstantInfo;

public class ConstantMethodHandle implements ConstantInfo {
    //引用类型
    private int referIndex;
    //引用名称
    private int nameIndex;

    @Override
    public void readInfo(ClassReader reader) {
        referIndex = reader.readNextU1();
        nameIndex = reader.readNextU2Int();
    }

    @Override
    public int tag() {
        return this.CONSTANT_METHODHANDLE;
    }
}
