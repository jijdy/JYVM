package com.jyvm.classfile;

import com.jyvm.classfile.attribute.AttributeInfo;
import com.jyvm.classfile.attribute.Implements.CodeAttribute;

/*
* 字段或方法表
* */
public class FieldOrMethodInfo {
    private final int accessFlag;
    private final int nameIndex;
    private final int descIndex;
    private final AttributeInfo[] attributes;
    private ConstantPool constantPool;

    public FieldOrMethodInfo(ClassReader reader, ConstantPool constantPool) {
        this.constantPool = constantPool;
        this.accessFlag = reader.readNextU2Int();
        this.nameIndex = reader.readNextU2Int();
        this.descIndex = reader.readNextU2Int();
        this.attributes = AttributeInfo.readAttributes(reader, constantPool);
    }


    public int getAccessFlag() {
        return accessFlag;
    }

    public String getName() {
        return constantPool.getUtf8(nameIndex);
    }

    public String getDesc() {
        return constantPool.getUtf8(descIndex);
    }

    public AttributeInfo[] getAttributes() {
        return attributes;
    }

    //拿到code属性表并完成加载
    public CodeAttribute getCodeAttribute() {
        for (AttributeInfo attribute : this.attributes) {
            if (attribute instanceof CodeAttribute) {
                return (CodeAttribute) attribute;
            }
        }
        return null;
    }
}
