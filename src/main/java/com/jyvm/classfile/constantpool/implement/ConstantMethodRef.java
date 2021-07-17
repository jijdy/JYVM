package com.jyvm.classfile.constantpool.implement;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.constantpool.ConstantInfo;
import com.jyvm.classfile.constantpool.ConstantPool;

public class ConstantMethodRef implements ConstantInfo {

    private int classIndex;
    private int nameIndex;
    private ConstantPool constantPool;

    public ConstantMethodRef(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }
    @Override
    public void readInfo(ClassReader reader) {
        classIndex = reader.readNextU2Int();
        nameIndex = reader.readNextU2Int();
    }

    @Override
    public int tag() {
        return ConstantInfo.CONSTANT_METHODREF;
    }

    public String classValue() {
        return constantPool.getClass(classIndex);
    }

    public String nameValue() {
        return constantPool.getName(nameIndex);
    }

}
