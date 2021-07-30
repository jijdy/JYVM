package com.jyvm.runtimeDate.heap.method;

import com.jyvm.classfile.ClassFile;
import com.jyvm.classfile.FieldOrMethodInfo;
import com.jyvm.runtimeDate.heap.AccessFlag;

public class ClassMember {

    int accessFlags;
    String name;
    String desc;
    public Class clazz; //指向字段所属的类，可以实现字段访问类的操

    public void copyInfo(FieldOrMethodInfo info) {
        this.accessFlags = info.getAccessFlag();
        this.name = info.getName();
        this.desc = info.getDesc();
    }

    public boolean isPublic() {
        return 0 != (this.accessFlags & AccessFlag.ACC_PUBLIC);
    }

    public boolean isPrivate() {
        return 0 != (this.accessFlags & AccessFlag.ACC_PRIVATE);
    }

    public boolean isProtected() {
        return 0 != (this.accessFlags & AccessFlag.ACC_PROTECTED);
    }

    public boolean isStatic() {
        return 0 != (this.accessFlags & AccessFlag.ACC_STATIC);
    }

    public boolean isFinal() {
        return 0 != (this.accessFlags & AccessFlag.ACC_FINAL);
    }

    public boolean isSynthetic() {
        return 0 != (this.accessFlags & AccessFlag.ACC_SYNTHETIC);
    }

    public String desc() {
        return this.desc;
    }

    public String name() {
        return this.name;
    }

    public boolean isAccessibleTo(Class clazz) {
        if (this.isPublic()) {
            return true;
        }
        Class c = this.clazz;
        if (this.isProtected()) {
            return clazz == c || c.getPackageName().equals(clazz.getPackageName());
        }
        if (!this.isPrivate()) {
            return c.getPackageName().equals(clazz.getPackageName());
        }
        return c == clazz;
    }

}
