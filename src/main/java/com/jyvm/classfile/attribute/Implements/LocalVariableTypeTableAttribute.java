package com.jyvm.classfile.attribute.Implements;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.attribute.AttributeInfo;

public class LocalVariableTypeTableAttribute implements AttributeInfo {

    LocalValueTable[] lineNumbers;

    @Override
    public void readInfo(ClassReader reader) {
        int length = reader.readNextU2Int();
        lineNumbers = new LocalValueTable[length];
        for (int i = 0; i < length; i ++) {
            lineNumbers[i] = new LocalValueTable(reader);

        }
    }

    static class LocalValueTable {
        final int startPC;
        final int length;
        final int nameIndex;
        final int descIndex;
        final int index;

        LocalValueTable(ClassReader reader) {
            startPC = reader.readNextU2Int();
            length = reader.readNextU2Int();
            nameIndex = reader.readNextU2Int();
            descIndex = reader.readNextU2Int();
            index = reader.readNextU2Int();
        }
    }
}
