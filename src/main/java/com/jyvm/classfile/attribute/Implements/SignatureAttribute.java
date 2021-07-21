package com.jyvm.classfile.attribute.Implements;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.ConstantPool;
import com.jyvm.classfile.attribute.AttributeInfo;

/*
* 接口签名属性
* */
public class SignatureAttribute implements AttributeInfo {

    int signatureIndex;
    ConstantPool constantPool;

    @Override
    public void readInfo(ClassReader reader) {
        signatureIndex = reader.readNextU2Int();
    }

    public SignatureAttribute(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

}
