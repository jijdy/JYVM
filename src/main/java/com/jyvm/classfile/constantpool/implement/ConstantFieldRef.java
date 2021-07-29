package com.jyvm.classfile.constantpool.implement;

import com.jyvm.classfile.ClassReader;
import com.jyvm.classfile.constantpool.ConstantInfo;
import com.jyvm.classfile.ConstantPool;

import java.util.HashMap;
import java.util.Map;

/*
* 表示字段符号引用，分别存有类的索引和名称和描述索引
* 用于唯一确定一个字段信息
* */
public class ConstantFieldRef implements ConstantInfo {

    private int classIndex;
    private int nameIndex;  //在常量池中的一个常量对象NameAndType类型
    private ConstantPool constantPool;

    public ConstantFieldRef(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    @Override
    public void readInfo(ClassReader reader) {
        classIndex = reader.readNextU2Int();
        nameIndex = reader.readNextU2Int();
    }

    @Override
    public int tag() {
        return ConstantInfo.CONSTANT_FIELDREF;
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
