package com.jyvm.classfile;

import com.jyvm.classfile.attribute.AttributeInfo;

public class ClassFile {

    private final ClassReader cr;
    private final ConstantPool constantPool;
    private final int accessFlag;
    private final int classIndex;
    private final int fatherIndex;
    private final int[] interfaceIndex;
    private final FieldOrMethodInfo[] fields;
    private final FieldOrMethodInfo[] methods;
    private final AttributeInfo[] attributeInfos;
    private int minorVersion;
    private int majorVersion;

    public ClassFile(byte[] bytes) {
        cr = new ClassReader(bytes);
        this.checkMagicNumber();
        this.checkVersion();
        this.constantPool = new ConstantPool(cr);
        this.accessFlag = cr.readNextU2Int();
        this.classIndex = cr.readNextU2Int();
        this.fatherIndex = cr.readNextU2Int();
        interfaceIndex = this.interfaceIndex(cr);
        fields = this.readFOrM(cr);
        methods = this.readFOrM(cr);
        attributeInfos = AttributeInfo.readAttributes(cr,constantPool);
    }

    //魔数检查
    void checkMagicNumber() {
        long magicNumber = cr.readNextU4Long();
        if (magicNumber != (0xCAFEBABE & 0x0FFFFFFFFL)) {
            throw new ClassFormatError("Magic Error!");
        }
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    //主版本和此版本的选择，仅符合java 8 的标准
    void checkVersion() {
        this.minorVersion = cr.readNextU2Int();
        this.majorVersion = cr.readNextU2Int();
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
            case 58:
            if (minorVersion == 0) {
                return;
            }
        }
        throw new UnsupportedClassVersionError("请使用正确版本！");
    }

    public int[] interfaceIndex(ClassReader classReader) {
        int size = classReader.readNextU2Int();
        int[] indexs = new int[size];
        for (int i = 0; i < size; i ++) {
            indexs[i] = classReader.readNextU2Int();
        }
        return indexs;
    }

    public FieldOrMethodInfo[] readFOrM(ClassReader reader) {
        int size = reader.readNextU2Int();
        FieldOrMethodInfo[] members = new FieldOrMethodInfo[size];
        for (int i = 0; i< size; i ++) {
            members[i] = new FieldOrMethodInfo(reader,constantPool);
        }
        return members;
    }

    public String[] getInterfaceName() {
        String[] s = new String[interfaceIndex.length];
        for (int i = 0; i < interfaceIndex.length; i ++) {
            s[i] = constantPool.getClass(interfaceIndex[i]);
        }
        return s;
    }


    public int accessFlag() {
        return accessFlag;
    }

    public String className() {
        return constantPool.getClass(classIndex);
    }

    public String fatherName() {
        if (this.fatherIndex <= 0) return "";
        return constantPool.getClass(fatherIndex);
    }

    public ConstantPool constant() {
        return constantPool;
    }

    public FieldOrMethodInfo[] fields() {
        return fields;
    }



    public FieldOrMethodInfo[] methods() {
        return methods;
    }

    public ConstantPool constantPool() {
        return constantPool;
    }

    public AttributeInfo[] getAttributeInfos() {
        return attributeInfos;
    }
}
