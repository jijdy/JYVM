package com.jyvm.classfile.attribute.Implements;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.ConstantPool;
import com.jyvm.classfile.attribute.AttributeInfo;

/*
* 包装方法，或匿名方法
* */
public class EnclosingMethodAttribute implements AttributeInfo {

    ConstantPool constantPool;
    int classIndex;
    int methodIndex;

    @Override
    public void readInfo(ClassReader reader) {
       this.classIndex = reader.readNextU2Int();
       this.methodIndex = reader.readNextU2Int();
    }

    public EnclosingMethodAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }
}
