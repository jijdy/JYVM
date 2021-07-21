package com.jyvm.classfile.attribute.Implements;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.ConstantPool;
import com.jyvm.classfile.attribute.AttributeInfo;

/*
* 未解析的属性
* */
public class UnParseAttribute implements AttributeInfo {

    private String name;
    private final int length;
    private byte[] info;

    @Override
    public void readInfo(ClassReader reader) {
        this.info = reader.read(this.length);
    }

    public UnParseAttribute(int attrLen, String name) {
        this.length = attrLen;
        this.name = name;
    }
}
