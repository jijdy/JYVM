package com.jyvm.runtimeDate.heap.constantpool;

import com.jyvm.classfile.constantpool.implement.ConstantFieldRef;
import com.jyvm.classfile.constantpool.implement.ConstantMethodRef;
import com.jyvm.runtimeDate.heap.method.Class;
import com.jyvm.runtimeDate.heap.method.Field;

/*
* 字段的引用类型，存储了字段信息的缓存设置，用于较为便捷的存取常量池中数据
* */
public class FieldRef extends SymRef{

    String name;
    String desc;
    Field field;

    public FieldRef(RuntimePool runtimePool, ConstantFieldRef fieldRef) {
        this.className = fieldRef.classValue();
        this.name = fieldRef.getNameAndDesc().get("name");
        this.desc = fieldRef.getNameAndDesc().get("desc");
        this.constantPool = runtimePool;
    }

    public Field getField() {
        if (null == this.field) {
            this.setField();
        }
        return this.field;
    }

    public void setField() {
        Class c = this.constantPool.getClazz();
        Class z = this.resolveClass();
        this.field = lookupField(z, this.name, this.desc);
        if (this.field == null) {
            try {
                throw new NoSuchFieldException("未找到相关属性");
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        if (!this.field.isAccessibleTo(c)) {
            try {
                throw new IllegalAccessException("无访问权限！");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    //在该类，其接口，父类中递归查找field字段属性
    public Field lookupField(Class clazz, String name, String desc) {
        for (Field field : clazz.fields) {
            if (field.name().equals(name) && field.desc().equals(desc)) {
                return field;
            }
        }

        for (Class c : clazz.interfaces) {
            Field field = lookupField(c, name, desc);
            if (null != field) return field;
        }

        if (clazz.superClass != null) {
            return lookupField(clazz.superClass, name, desc);
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
