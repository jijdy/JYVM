package com.jyvm.classfile.constantpool;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.ConstantPool;
import com.jyvm.classfile.constantpool.implement.*;

public interface ConstantInfo {
    //定义tag常量值判断
    int CONSTANT_UTF8 = 1;
    int CONSTANT_INTEGER = 3;
    int CONSTANT_FLOAT = 4;
    int CONSTANT_LONG = 5;
    int CONSTANT_DOUBLE = 6;
    int CONSTANT_CLASS = 7;
    int CONSTANT_STRING = 8;
    int CONSTANT_FIELDREF = 9;
    int CONSTANT_METHODREF = 10;
    int CONSTANT_INTERFACE = 11;
    int CONSTANT_NAMEANDTYPE = 12;
    int CONSTANT_METHODTYPE = 16;
    int CONSTANT_METHODHANDLE = 15;
    int CONSTANT_INVOKEDYNAMIC = 18;//未实现

    //读取数据并进行存储
    void readInfo(ClassReader reader);

    //返回其含有的tag值
    int tag();

    //对接收常量池的信息进行封装和分层，进行存储之后再返回
    static ConstantInfo readerNewConstantInfo(ClassReader classReader, ConstantPool constantPool) {
        int tag = classReader.readNextU1();
        ConstantInfo constantInfo = newConstantInfo(tag, constantPool);
        constantInfo.readInfo(classReader);
        return constantInfo;
    }

    static ConstantInfo newConstantInfo(int tag, ConstantPool constantPool) {
        switch (tag) {
            case CONSTANT_UTF8:
               return new ConstantUtf8();
            case CONSTANT_INTEGER:
               return new ConstantInteger();
             case CONSTANT_FLOAT:
               return new ConstantFloat();
             case CONSTANT_LONG:
               return new ConstantLong();
             case CONSTANT_DOUBLE:
               return new ConstantDouble();
             case CONSTANT_CLASS:
               return new ConstantClass(constantPool);
             case CONSTANT_STRING:
               return new ConstantString(constantPool);
             case CONSTANT_FIELDREF:
               return new ConstantFieldRef(constantPool);
             case CONSTANT_METHODREF:
               return new ConstantMethodRef(constantPool);
             case CONSTANT_INTERFACE:
               return new ConstantInterfaceMethod(constantPool);
             case CONSTANT_NAMEANDTYPE:
               return new ConstantNameAndType(constantPool);
             case CONSTANT_METHODTYPE:
               return new ConstantMethodType();
             case CONSTANT_METHODHANDLE:
               return new ConstantMethodHandle();
            default:
                throw new ClassFormatError("Tag Error!");
        }
    }


}
