package com.jyvm.classfile.attribute.Implements;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.attribute.AttributeInfo;

/*
* 用于表示常量表达式的值，一般为一个基本类型常量
* 存储的值为一个不定类型的值
* */
public class ConstantValueAttribute implements AttributeInfo {

    private int constantValue;

    @Override
    public void readInfo(ClassReader reader) {
        constantValue = reader.readNextU2Int();
    }

    public int constantValue() {
        return this.constantValue;
    }
}
