package com.jyvm.classfile.attribute.Implements;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.attribute.AttributeInfo;

/*
* 用于指出和标记源文件中存在的类或接口，即在编译时期生成的类或接口，一把用于支持内部
* 嵌套类或嵌套接口等功能
* */
public class SyntheticAttribute implements AttributeInfo {

    @Override
    public void readInfo(ClassReader reader) {
        //null
    }
}
