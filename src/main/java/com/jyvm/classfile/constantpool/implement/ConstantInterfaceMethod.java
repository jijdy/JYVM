package com.jyvm.classfile.constantpool.implement;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.constantpool.ConstantInfo;
import com.jyvm.classfile.ConstantPool;

import java.util.HashMap;
import java.util.Map;

/*
* 表示接口方法，存储类名的索引及名称和描述符索引
* */
public class ConstantInterfaceMethod implements ConstantInfo {

    private int classIndex;
    private int nameIndex;
    private ConstantPool constantPool;

    public ConstantInterfaceMethod(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        classIndex = reader.readNextU2Int();
        nameIndex = reader.readNextU2Int();
    }

    @Override
    public int tag() {
        return ConstantInfo.CONSTANT_INTERFACE;
    }

    public String classValue() {
        return constantPool.getClass(classIndex);
    }

    public String nameValue() {
        return constantPool.getName(nameIndex);
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
