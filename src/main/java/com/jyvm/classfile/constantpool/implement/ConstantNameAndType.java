package com.jyvm.classfile.constantpool.implement;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.constantpool.ConstantInfo;
import com.jyvm.classfile.ConstantPool;

/*
* 字段或者方法的名称和表示符，都按照索引指向一个utf8常量
* 描述符表示该字段或方法的类型等等
* */
public class ConstantNameAndType implements ConstantInfo {
    private int nameIndex;
    private ConstantPool constantPool;
    private int descIndex;

    public ConstantNameAndType(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }
    @Override
    public void readInfo(ClassReader reader) {
        nameIndex = reader.readNextU2Int();
        descIndex = reader.readNextU2Int();
    }

    @Override
    public int tag() {
        return ConstantInfo.CONSTANT_NAMEANDTYPE;
    }

    public String nameValue() {
        return constantPool.getUtf8(nameIndex);
    }

    public String descValue() {
        return constantPool.getUtf8(descIndex);
    }
}
