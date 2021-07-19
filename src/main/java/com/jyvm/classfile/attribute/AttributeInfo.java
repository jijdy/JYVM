package com.jyvm.classfile.attribute;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.ConstantPool;
import com.jyvm.classfile.attribute.Implements.*;

public interface AttributeInfo {
    void readInfo(ClassReader reader);

    static AttributeInfo[] readAttributes(ClassReader reader, ConstantPool constantPool) {
        int attrCount = reader.readNextU2Int();
        AttributeInfo[] attributeInfos = new AttributeInfo[attrCount];

        for (int i =0; i < attrCount; i ++) {
            attributeInfos[i] = readAttribute(reader,constantPool);
        }
        return attributeInfos;
    }

    static AttributeInfo readAttribute(ClassReader reader, ConstantPool constantPool) {
        int attrIndex = reader.readNextU2Int();
        String attrName = constantPool.getUtf8(attrIndex);
        long attrLen = reader.readNextU4Long();
        AttributeInfo attribute = newAttribute(attrName, attrLen, constantPool);
        attribute.readInfo(reader);
        return attribute;
    }

    //选择创建对象的具体类型，若未定义，则new一个为解析的自定义属性
    static AttributeInfo newAttribute(String name, long attrLen, ConstantPool constantPool) {
        switch (name) {
            case "BootstrapMethods":
                return new BootstrapMethodsAttribute();
            case "Code":
                return new CodeAttribute(constantPool);
            case "ConstantValue":
                return new ConstantValueAttribute();
            case "Deprecated":
                return new DeprecatedAttribute();
            case "EnclosingMethod":
                return new EnclosingMethodAttribute(constantPool);
            case "Exceptions":
                return new ExceptionsAttribute();
            case "InnerClasses":
                return new InnerClassesAttribute();
            case "LineNumberTable":
                return new LineNumberTableAttribute();
            case "LocalVariableTable":
                return new LocalVariableTableAttribute();
            case "LocalVariableTypeTable":
                return new LocalVariableTypeTableAttribute();
            case "Signature":
                return new SignatureAttribute(constantPool);
            case "SourceFile":
                return new SourceFileAttribute(constantPool);
            case "Synthetic":
                return new SyntheticAttribute();
            default:
                return new UnParseAttribute(attrLen, name);
        }
    }
}
