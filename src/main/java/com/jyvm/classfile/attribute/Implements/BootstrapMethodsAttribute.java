package com.jyvm.classfile.attribute.Implements;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.attribute.AttributeInfo;

/*
* 启动项的方法存放信息
* */
public class BootstrapMethodsAttribute implements AttributeInfo {

    BootStrap[] bootStraps;

    @Override
    public void readInfo(ClassReader reader) {
        int length = reader.readNextU2Int();
        bootStraps = new BootStrap[length];
        for (int i = 0; i <length; i++) {
            bootStraps[i] = new BootStrap(reader.readNextU2Int(), reader.readU2Ints());
        }

    }

    static class BootStrap{
        int bootStrapMethodRef;
        int[] bootStrapArguments;

        BootStrap(int b , int[] bs) {
            this.bootStrapMethodRef = b;
            this.bootStrapArguments = bs;
        }
    }
}
