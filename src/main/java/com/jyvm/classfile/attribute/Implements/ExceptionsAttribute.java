package com.jyvm.classfile.attribute.Implements;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.attribute.AttributeInfo;

/*
* 记录方法抛出的异常表
* */
public class ExceptionsAttribute implements AttributeInfo {

    private int[] exceptions;

    @Override
    public void readInfo(ClassReader reader) {
        exceptions = reader.readU2Ints();
    }

    public int[] getExceptions() {
        return exceptions;
    }
}
