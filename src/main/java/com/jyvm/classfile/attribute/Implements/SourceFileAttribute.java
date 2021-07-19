package com.jyvm.classfile.attribute.Implements;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.ConstantPool;
import com.jyvm.classfile.attribute.AttributeInfo;

/*
* 用于指出源文件名称，属性长度固定为2
* */
public class SourceFileAttribute implements AttributeInfo {

    private ConstantPool constantPool;
    private int sourceIndex;

    @Override
    public void readInfo(ClassReader reader) {
        sourceIndex = reader.readNextU2Int();
    }

    public SourceFileAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public String sourceName() {
        return constantPool.getUtf8(sourceIndex);
    }
}
