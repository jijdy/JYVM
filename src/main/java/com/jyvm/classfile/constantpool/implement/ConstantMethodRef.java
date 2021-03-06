package com.jyvm.classfile.constantpool.implement;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.constantpool.ConstantInfo;
import com.jyvm.classfile.ConstantPool;

import java.util.HashMap;
import java.util.Map;

/*
* 方法描述，存有类的索引及其名称和描述索引，
* 用于表示一个唯一的非接口方法信息
* */
public class ConstantMethodRef implements ConstantInfo {

    private int classIndex;
    private int nameIndex;
    private ConstantPool constantPool;

    public ConstantMethodRef(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        classIndex = reader.readNextU2Int();
        nameIndex = reader.readNextU2Int();
    }

    @Override
    public int tag() {
        return ConstantInfo.CONSTANT_METHODREF;
    }

    public String classValue() {
        return constantPool.getClass(classIndex);
    }

    public String nameValue() {
        return constantPool.getName(nameIndex);
    }

    @Override
    public String toString() {
        return classValue();
    }

    public Map<String , String> getNameAndDesc() {
        Map<String, String> map = new HashMap<>();
        //得到常量池数据
        ConstantNameAndType nameAndType = (ConstantNameAndType) this.constantPool.getConstantInfos()[this.nameIndex];
        map.put("name", nameAndType.nameValue());
        map.put("desc", nameAndType.descValue());
        return map;
    }

}
