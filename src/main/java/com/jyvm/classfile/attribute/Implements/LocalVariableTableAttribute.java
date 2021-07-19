package com.jyvm.classfile.attribute.Implements;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.attribute.AttributeInfo;

/*
* 存放方法的局部变量信息
* */
public class LocalVariableTableAttribute implements AttributeInfo {

    private LocalValue[] lineNumbers;

    @Override
    public void readInfo(ClassReader reader) {
        int length = reader.readNextU2Int();
        lineNumbers = new LocalValue[length];
        for (int i = 0; i < length; i ++) {
            lineNumbers[i] = new LocalValue(reader);
        }
    }

    public LocalValue[] getLineNumbers() {
        return this.lineNumbers;
    }

    static class LocalValue {
        private final int startPC;
        private final int length;
        private final int nameIndex;
        private final int descIndex;
        private final int index;

        LocalValue(ClassReader reader) {
            startPC = reader.readNextU2Int();
            length = reader.readNextU2Int();
            nameIndex = reader.readNextU2Int();
            descIndex = reader.readNextU2Int();
            index = reader.readNextU2Int();
        }

        public int getStartPC() {
            return this.startPC;
        }

        public int getLength() {
            return length;
        }

        public int getNameIndex() {
            return nameIndex;
        }

        public int getDescIndex() {
            return descIndex;
        }

        public int getIndex() {
            return index;
        }
    }
}
