package com.jyvm.classfile.attribute.Implements;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.attribute.AttributeInfo;

/*
* 该属性用于在编译器中指出 类，接口，字段，方法等数据已经不建议使用，即表示已经过时的方法等
* 可以在编译器中使用使用@Deprecated注解抑制该信息
* */
public class DeprecatedAttribute implements AttributeInfo {

    @Override
    public void readInfo(ClassReader reader) {
        //null
    }
}
