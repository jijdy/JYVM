package com.jyvm.classfile.attribute.Implements;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.attribute.AttributeInfo;

/*
* 内部类的属性表
* */
public class InnerClassesAttribute implements AttributeInfo {

    private InnerClassInfo[] innerClassInfos;

    @Override
    public void readInfo(ClassReader reader) {
        int size = reader.readNextU2Int();
        innerClassInfos = new InnerClassInfo[size];
        for (int i = 0; i < size; i ++) {
            innerClassInfos[i] = new InnerClassInfo(reader.readNextU2Int(), reader.readNextU2Int(), reader.readNextU2Int(),reader.readNextU2Int());
        }
    }

    static class InnerClassInfo {

        int innerClassInfoIndex;
        int outerClassInfoIndex;
        int innerNameIndex;
        int innerClassAccessFlags;

        InnerClassInfo(int innerClassInfoIndex, int outerClassInfoIndex, int innerNameIndex, int innerClassAccessFlags) {
            this.innerClassInfoIndex = innerClassInfoIndex;
            this.outerClassInfoIndex = outerClassInfoIndex;
            this.innerNameIndex = innerNameIndex;
            this.innerClassAccessFlags = innerClassAccessFlags;
        }
    }

}
