package com.jyvm.classfile.constantpool;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.constantpool.implement.*;

public class ConstantPool {

    private final ConstantInfo[] constantInfos;

    public ConstantPool(ClassReader classReader) {
        int size = classReader.readNextU2Int();
        constantInfos = new ConstantInfo[size];
        for (int i = 1; i < size; i ++) {
            constantInfos[i] = ConstantInfo.readerNewConstantInfo(classReader,this);
            if (constantInfos[i] instanceof ConstantLong || constantInfos[i] instanceof ConstantDouble) {
                i ++;
            }

        }
    }

    public String getUtf8(int index) {
        ConstantUtf8 constantUtf8 = (ConstantUtf8) constantInfos[index];
        return constantUtf8 == null ? "" : constantUtf8.value();
    }

    public String getClass(int index) {
        ConstantClass constantClass = (ConstantClass) constantInfos[index];
        return constantClass.value();
    }

    public String getName(int index) {
        ConstantNameAndType constantNameAndType = (ConstantNameAndType) constantInfos[index];
        return constantNameAndType.nameValue() + " " + constantNameAndType.descValue();
    }


}
