package com.jyvm.runtimeDate.heap.method;

import com.jyvm.classfile.ClassFile;
import com.jyvm.runtimeDate.Slot;
import com.jyvm.runtimeDate.heap.AccessFlag;
import com.jyvm.runtimeDate.heap.ClassLoader;
import com.jyvm.runtimeDate.heap.constantpool.RuntimePool;

public class Class {

    public int accessFlags;
    public String name;   //完全限定名，为java/lang/String这样的形式
    public String fatherClassName;
    public String[] interfaceNames;
    public RuntimePool runtimePool;
    public Field[] fields;
    public Method[] methods;
    public ClassLoader loader;
    public Class superClass;
    public Class[] interfaces;
    public int instanceSlotCount;
    public int staticSlotCount;
    public Slots staticVars;

    public Class(ClassFile classFile) {
        this.accessFlags = classFile.accessFlag();
        this.name = classFile.className();
        this.fatherClassName = classFile.fatherName();
        this.interfaceNames = classFile.getInterfaceName();
        this.runtimePool = new RuntimePool(this,classFile.constantPool());
        this.fields = Field.fields(this, classFile.fields());
        this.methods = Method.newMethod(this, classFile.methods());
    }

    public boolean isPublic() {
        return  0 != (this.accessFlags & AccessFlag.ACC_PUBLIC);
    }

    public boolean isFinal() {
        return 0 != (this.accessFlags & AccessFlag.ACC_FINAL);
    }

    public boolean isSuper() {
        return 0 != (this.accessFlags & AccessFlag.ACC_SUPER);
    }

    public boolean isInterface() {
        return 0 != (this.accessFlags & AccessFlag.ACC_INTERFACE);
    }

    public boolean isSynthetic() {
        return 0 != (this.accessFlags & AccessFlag.ACC_SYNTHETIC);
    }

    public boolean isAbstract() {
        return 0 != (this.accessFlags & AccessFlag.ACC_ABSTRACT);
    }

    public boolean isAnnotation() {
        return 0 != (this.accessFlags & AccessFlag.ACC_ANNOTATION);
    }

    public boolean isEnum() {
        return 0 != (this.accessFlags & AccessFlag.ACC_ENUM);
    }

    //判断是否为可访问的，为public或在同一个包下
    public boolean isAccessibleTo(Class clazz) {
        return this.isPublic() || this.getPackageName().equals(clazz.getPackageName());
    }

    public boolean isAssignableFrom(Class clazz) {
        if (this == clazz) {
            return true;
        }
        if (!clazz.isInterface()) {
            return this.isSubClassOf(clazz);
        } else {
            return this.isImplements(clazz);
        }
    }

    public boolean isSubClassOf(Class clazz) {
        for (Class c = this.superClass; c != null; c = c.superClass) {
            if (c == clazz) {
                return true;
            }
        }
        return false;
    }

    //查找所有的接口，包括父类接口
    public boolean isImplements(Class clazz) {
        for (Class c = this; c != null; c = c.superClass) {
            for (Class cl : c.interfaces) {
                if (cl == clazz || clazz.isSubInterfaceOf(clazz)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isSubInterfaceOf(Class clazz) {
        for (Class superInterface : this.interfaces) {
            if (superInterface == clazz || superInterface.isSubInterfaceOf(clazz)) {
                return true;
            }
        }
        return false;
    }

    public String getPackageName() {
        int i = this.name.lastIndexOf("/");
        if (i > 0) {
            return this.name;
        }
        return "";
    }

    public Method getMainMethod() {
        return this.getStaticMethod("main","([Ljava/lang/String;)V");
    }

    public Method getStaticMethod(String name, String desc) {
        for (Method method : this.methods) {
            if (method.name.equals(name) && method.desc.equals(desc))
                return method;
        }
        return null;
    }

    public Object object() {
        return new Object(this);
    }
}
