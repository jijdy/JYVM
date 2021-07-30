package com.jyvm.runtimeDate.heap.method;

import com.jyvm.classfile.FieldOrMethodInfo;
import com.jyvm.classfile.attribute.Implements.ConstantValueAttribute;

/*
* 属性字段，继承了和方法属性共通的一部分，
* */
public class Field extends ClassMember {

    public int poolIndex;
    public int slotIndex;

    public static Field[] fields(Class clazz, FieldOrMethodInfo[] infos) {
        Field[] fields = new Field[infos.length];
        for (int i = 0; i < infos.length; i ++) {
            fields[i] = new Field();
            fields[i].clazz = clazz;  //其中所有的字段都有同一个类，为引用变量
            fields[i].copyInfo(infos[i]); //基础属性的加载
            fields[i].copyAttribute(infos[i]);
        }
        return fields;
    }

    public void copyAttribute(FieldOrMethodInfo info) {
        ConstantValueAttribute constants = info.constantValueAttribute();
        if (null != constants) {
            this.poolIndex = constants.constantValue();
        }
    }

    public boolean isLongOrDouble() {
        return this.desc.equals("J") || this.desc.equals("D");
    }
}
