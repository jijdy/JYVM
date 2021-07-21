package com.jyvm.classfile.attribute.Implements;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.attribute.AttributeInfo;
import com.sun.org.apache.bcel.internal.classfile.LineNumber;

/*
* 用于存放方法的行数信息,可以当做为调试信息
* */
public class LineNumberTableAttribute implements AttributeInfo {

    private LineNumber[] lineNumbers;

    @Override
    public void readInfo(ClassReader reader) {
        int length = reader.readNextU2Int();
        lineNumbers = new LineNumber[length];
        for (int i = 0; i < length; i ++) {
            lineNumbers[i] = new LineNumber(reader);
        }
    }

    public LineNumber[] getLineNumbers() {
        return this.lineNumbers;
    }

    static class LineNumber{
        private final int startPC;
        private final int LineNumber;

        LineNumber(ClassReader reader) {
            startPC = reader.readNextU2Int();
            LineNumber = reader.readNextU2Int();
        }

        public int getStartPC() {
            return this.startPC;
        }
        public int getLineNumber() {
            return this.LineNumber;
        }
    }

}
