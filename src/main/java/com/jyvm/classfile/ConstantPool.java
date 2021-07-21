package com.jyvm.classfile;

import com.jyvm.classfile.constantpool.ConstantInfo;
import com.jyvm.classfile.constantpool.implement.*;

public class ConstantPool {

    private final ConstantInfo[] constantInfos;
    private final int size;

    public ConstantPool(ClassReader classReader) {
        this.size = classReader.readNextU2Int();
        constantInfos = new ConstantInfo[size];
        for (int i = 1; i < size; i ++) {
            constantInfos[i] = ConstantInfo.readerNewConstantInfo(classReader,this);
            if (constantInfos[i].tag() == ConstantInfo.CONSTANT_DOUBLE || constantInfos[i].tag() == ConstantInfo.CONSTANT_LONG) {
                i ++;
            }

        }
    }

    public String getUtf8(int index) {
        ConstantUtf8 constantUtf8 = (ConstantUtf8) this.constantInfos[index];
        return constantUtf8 == null ? "" : constantUtf8.toString();
    }

//    public String getUtf8(int index) {
//        ConstantUtf8 constantUtf8 = (ConstantUtf8) this.constantInfos[index];
//        return constantUtf8 == null ? "" : constantUtf8.value();
//    }

    public String getClass(int index) {
        ConstantClass constantClass = (ConstantClass) constantInfos[index];
        return constantClass.value();
    }

    public String getName(int index) {
        ConstantNameAndType constantNameAndType = (ConstantNameAndType) constantInfos[index];
        return constantNameAndType.nameValue() + " " + constantNameAndType.descValue();
    }

    public int getSize() {
        return size;
    }


}
