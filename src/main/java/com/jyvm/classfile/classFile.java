package com.jyvm.classfile;

import com.jyvm.classfile.constantpool.ConstantPool;

public class classFile {

    private final ClassReader cr;
    private final ConstantPool constantPool;
    private final int accessFlag;
    private final int classIndex;
    private final int fatherIndex;
    private int[] interfaceIndex;


    classFile(byte[] bytes) {
        cr = new ClassReader(bytes);
        this.checkMagicNumber();
        this.checkVersion();
        this.constantPool = new ConstantPool(cr);
        this.accessFlag = cr.readNextU2Int();
        this.classIndex = cr.readNextU2Int();
        this.fatherIndex = cr.readNextU2Int();
        this.interfaceIndex(cr);
    }

    //魔数检查
    void checkMagicNumber() {
        long magicNumber = cr.readNextU4Long();
        if (magicNumber != 0xcafababe) {
            throw new ClassFormatError("Magic Error!");
        }
    }

    //主版本和此版本的选择，仅符合java 8 的标准
    void checkVersion() {
        int minorVersion = cr.readNextU2Int();
        int majorVersion = cr.readNextU2Int();
        switch (majorVersion) {
            case 45:
                return;
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            if (minorVersion == 0) {
                return;
            }
        }
        throw new UnsupportedClassVersionError("请使用正确版本！");
    }

    public void interfaceIndex(ClassReader classReader) {
        int size = classReader.readNextU2Int();
        interfaceIndex = new int[size];
        for (int i = 0; i < size; i ++) {
            interfaceIndex[i] = classReader.readNextU2Int();
        }
    }

    public int[] getInterfaceIndex() {
        return interfaceIndex;
    }


    public int accessFlag() {
        return accessFlag;
    }

    public int classIndex() {
        return classIndex;
    }

    public int fatherIndex() {
        return fatherIndex;
    }

    public ConstantPool constant() {
        return constantPool;
    }

}
