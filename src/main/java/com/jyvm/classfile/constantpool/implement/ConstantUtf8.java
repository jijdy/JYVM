package com.jyvm.classfile.constantpool.implement;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.constantpool.ConstantInfo;


/*
* 存储mutf-8编码的字符串
* */
public class ConstantUtf8 implements ConstantInfo {

    private String value;

    @Override
    public void readInfo(ClassReader reader) {
        int size = reader.readNextU2Int();
        byte[] bytes = new byte[size];
        value = new String(bytes);
    }

    @Override
    public int tag() {
        return ConstantInfo.CONSTANT_UTF8;
    }

    public String value() {
        return this.value;
    }
}
